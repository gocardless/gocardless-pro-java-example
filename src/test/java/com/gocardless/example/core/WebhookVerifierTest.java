package com.gocardless.example.core;

import com.gocardless.example.exceptions.InvalidWebhookException;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.base.Charsets.UTF_8;

public class WebhookVerifierTest {
    private WebhookVerifier verifier;

    @Before
    public void setUp() {
        verifier = new WebhookVerifier("key", "ElfJ-3tF9I_zutNVK2lBABQrw-BgAhkZKIlvmbgk");
    }

    @Test
    public void shouldVerifyWebhookWithCorrectSignature() throws Exception {
        String signature = "4d48a688e8bd6c313e3eecc78fa55b3e4ae23c65e70cf35038010f47742fb670";
        String body = Resources.toString(Resources.getResource("webhook.json"), UTF_8);

        verifier.verify(body, "key", signature);
    }

    @Test(expected = InvalidWebhookException.class)
    public void shouldThrowForWebhookWithIncorrectKey() throws Exception {
        String signature = "4d48a688e8bd6c313e3eecc78fa55b3e4ae23c65e70cf35038010f47742fb670";
        String body = Resources.toString(Resources.getResource("webhook.json"), UTF_8);

        verifier.verify(body, "otherKey", signature);
    }

    @Test(expected = InvalidWebhookException.class)
    public void shouldThrowForWebhookWithIncorrectSignature() throws Exception {
        String signature = "4d48a688e8bd6c313e3eecc78fa55b3e4ae23c65e70cf35038010f47742fb671";
        String body = Resources.toString(Resources.getResource("webhook.json"), UTF_8);

        verifier.verify(body, "key", signature);
    }
}
