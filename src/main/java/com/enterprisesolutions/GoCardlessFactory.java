package com.enterprisesolutions;

import com.enterprisesolutions.core.WebhookVerifier;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class GoCardlessFactory {
    @JsonProperty
    @NotEmpty
    private String apiKey;

    @JsonProperty
    @NotEmpty
    private String apiSecret;

    public WebhookVerifier buildSignatureVerifier() {
        return new WebhookVerifier(apiKey, apiSecret);
    }
}
