package com.gocardless.example;

import com.gocardless.example.core.WebhookVerifier;
import com.gocardless.example.providers.InvalidWebhookExceptionMapper;
import com.gocardless.example.resources.HelloWorldResource;
import com.gocardless.example.resources.WebhookResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class GoCardlessExampleApplication extends Application<GoCardlessExampleConfiguration> {
    public static void main(String[] args) throws Exception {
        new GoCardlessExampleApplication().run(args);
    }

    @Override
    public void run(GoCardlessExampleConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new HelloWorldResource());

        WebhookVerifier signatureVerifier = configuration.getGoCardless().buildSignatureVerifier();
        environment.jersey().register(new WebhookResource(signatureVerifier));

        environment.jersey().register(new InvalidWebhookExceptionMapper());
    }
}