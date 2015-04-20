package com.enterprisesolutions.core;

import com.enterprisesolutions.exceptions.InvalidWebhookException;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.io.BaseEncoding.base16;

public class WebhookVerifier {
    private final String apiKey;
    private final SecretKeySpec keySpec;

    public WebhookVerifier(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.keySpec = new SecretKeySpec(apiSecret.getBytes(UTF_8), "HmacSHA256");
    }

    public void verify(String body, String key, String signature) {
        verifyKey(key);
        verifySignature(body, signature);
    }

    private void verifyKey(String key) {
        if (!StringUtils.equals(key, apiKey)) {
            throw new InvalidWebhookException(String.format("Invalid API key (expected %s, got %s)", apiKey, key));
        }
    }

    private void verifySignature(String body, String expectedSignature) {
        Mac mac = createMac();

        String computedSignature = base16().lowerCase().encode(mac.doFinal(body.getBytes(UTF_8)));

        if (!StringUtils.equals(expectedSignature, computedSignature)) {
            throw new InvalidWebhookException(String.format("Invalid webhook signature (expected %s, computed %s)", expectedSignature, computedSignature));
        }
    }

    private Mac createMac() {
        Mac mac;
        try {
            mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException(e);
        }

        return mac;
    }
}
