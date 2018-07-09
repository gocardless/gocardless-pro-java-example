package com.enterprisesolutions;

import com.enterprisesolutions.providers.GoCardlessApiExceptionMapper;
import com.enterprisesolutions.providers.InvalidSignatureExceptionMapper;
import com.enterprisesolutions.resources.RedirectFlowResource;
import com.enterprisesolutions.resources.WebhookResource;
import com.gocardless.GoCardlessClient;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.server.session.SessionHandler;

public class EnterpriseSolutionsApplication extends Application<EnterpriseSolutionsConfiguration> {
    public static void main(String[] args) throws Exception {
        new EnterpriseSolutionsApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<EnterpriseSolutionsConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.addBundle(new AssetsBundle());

        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(EnterpriseSolutionsConfiguration configuration, Environment environment) {
        GoCardlessClient goCardless = configuration.getGoCardless().buildClient();
        environment.jersey().register(new RedirectFlowResource(goCardless));
        environment.jersey().register(new WebhookResource(configuration.getGoCardless().getWebhookSecret()));

        environment.jersey().register(new GoCardlessApiExceptionMapper());
        environment.jersey().register(new InvalidSignatureExceptionMapper());

        environment.servlets().setSessionHandler(new SessionHandler());
    }
}
