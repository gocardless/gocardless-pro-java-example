package com.gocardless.example.core;

import com.gocardless.example.exceptions.InvalidWebhookSignatureException;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.io.BaseEncoding.base16;

public class WebhookSignatureVerifier {
    private final SecretKeySpec keySpec;

    public WebhookSignatureVerifier(String apiSecret) {
        this.keySpec = new SecretKeySpec(apiSecret.getBytes(UTF_8), "HmacSHA256");
    }

    public void verify(String body, String expectedSignature) throws GeneralSecurityException {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(keySpec);

        String computedSignature = base16().lowerCase().encode(mac.doFinal(body.getBytes(UTF_8)));

        if (!StringUtils.equals(expectedSignature, computedSignature)) {
            throw new InvalidWebhookSignatureException(expectedSignature, computedSignature);
        }
    }
}
