package com.gocardless.example.core;

import com.gocardless.example.exceptions.InvalidWebhookSignatureException;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.base.Charsets.UTF_8;

public class WebhookSignatureVerifierTest {
    private WebhookSignatureVerifier verifier;

    @Before
    public void setUp() {
        verifier = new WebhookSignatureVerifier("ElfJ-3tF9I_zutNVK2lBABQrw-BgAhkZKIlvmbgk");
    }

    @Test
    public void shouldVerifyWebhookWithCorrectSignature() throws Exception {
        String signature = "4d48a688e8bd6c313e3eecc78fa55b3e4ae23c65e70cf35038010f47742fb670";
        String body = Resources.toString(Resources.getResource("webhook.json"), UTF_8);

        verifier.verify(body, signature);
    }

    @Test(expected = InvalidWebhookSignatureException.class)
    public void shouldThrowForWebhookWithIncorrectSignature() throws Exception {
        String signature = "4d48a688e8bd6c313e3eecc78fa55b3e4ae23c65e70cf35038010f47742fb671";
        String body = Resources.toString(Resources.getResource("webhook.json"), UTF_8);

        verifier.verify(body, signature);
    }
}
