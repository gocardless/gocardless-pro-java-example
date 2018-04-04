package com.enterprisesolutions;

import com.enterprisesolutions.core.WebhookVerifier;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gocardless.GoCardlessClient;
import com.gocardless.GoCardlessClient.Environment;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class GoCardlessFactory {
    @JsonProperty
    @NotEmpty
    private String accessToken;

    @JsonProperty
    private String webhookSecret;

    @JsonProperty
    @NotNull
    private Environment environment;

    public GoCardlessClient buildClient() {
        return GoCardlessClient.newBuilder(accessToken)
            .withEnvironment(environment)
            .build();
    }

    public WebhookVerifier buildSignatureVerifier() {
        if (StringUtils.isBlank(webhookSecret)) {
            return null;
        }

        return new WebhookVerifier(webhookSecret);
    }
}
