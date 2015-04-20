package com.enterprisesolutions;

import com.enterprisesolutions.core.WebhookVerifier;
import com.enterprisesolutions.providers.InvalidWebhookExceptionMapper;
import com.enterprisesolutions.resources.ExampleResource;
import com.enterprisesolutions.resources.WebhookResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class EnterpriseSolutionsApplication extends Application<EnterpriseSolutionsConfiguration> {
    public static void main(String[] args) throws Exception {
        new EnterpriseSolutionsApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<EnterpriseSolutionsConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(EnterpriseSolutionsConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new ExampleResource());

        WebhookVerifier signatureVerifier = configuration.getGoCardless().buildSignatureVerifier();
        environment.jersey().register(new WebhookResource(signatureVerifier));

        environment.jersey().register(new InvalidWebhookExceptionMapper());
    }
}
