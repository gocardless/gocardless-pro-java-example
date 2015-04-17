package com.gocardless.example;

import com.gocardless.example.core.WebhookVerifier;
import com.gocardless.example.providers.InvalidWebhookExceptionMapper;
import com.gocardless.example.resources.ExampleResource;
import com.gocardless.example.resources.WebhookResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class GoCardlessExampleApplication extends Application<GoCardlessExampleConfiguration> {
    public static void main(String[] args) throws Exception {
        new GoCardlessExampleApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<GoCardlessExampleConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<>());
    }

    @Override
    public void run(GoCardlessExampleConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new ExampleResource());

        WebhookVerifier signatureVerifier = configuration.getGoCardless().buildSignatureVerifier();
        environment.jersey().register(new WebhookResource(signatureVerifier));

        environment.jersey().register(new InvalidWebhookExceptionMapper());
    }
}
