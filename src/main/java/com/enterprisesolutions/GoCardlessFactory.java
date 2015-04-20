package com.enterprisesolutions;

import com.enterprisesolutions.core.WebhookVerifier;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gocardless.GoCardlessClient;
import com.gocardless.GoCardlessClient.Environment;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class GoCardlessFactory {
    @JsonProperty
    @NotEmpty
    private String apiKey;

    @JsonProperty
    @NotEmpty
    private String apiSecret;

    @JsonProperty
    @NotNull
    private Environment environment;

    public GoCardlessClient buildClient() {
        return GoCardlessClient.create(apiKey, apiSecret, environment);
    }

    public WebhookVerifier buildSignatureVerifier() {
        return new WebhookVerifier(apiKey, apiSecret);
    }
}
