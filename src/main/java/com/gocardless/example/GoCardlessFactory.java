package com.gocardless.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gocardless.example.core.WebhookSignatureVerifier;
import org.hibernate.validator.constraints.NotEmpty;

public class GoCardlessFactory {
    @JsonProperty
    @NotEmpty
    private final String apiKey;

    @JsonProperty
    @NotEmpty
    private final String apiSecret;

    @JsonCreator
    public GoCardlessFactory(@JsonProperty("apiKey") String apiKey,
                             @JsonProperty("apiSecret") String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public WebhookSignatureVerifier buildSignatureVerifier() {
        return new WebhookSignatureVerifier(apiSecret);
    }
}
