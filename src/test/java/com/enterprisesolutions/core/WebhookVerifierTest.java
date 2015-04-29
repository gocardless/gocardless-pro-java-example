package com.enterprisesolutions.core;

import com.enterprisesolutions.exceptions.InvalidWebhookException;
import com.google.common.io.Resources;
import org.junit.Test;

import static com.google.common.base.Charsets.UTF_8;

public class WebhookVerifierTest {
    private static WebhookVerifier verifier = new WebhookVerifier("ElfJ-3tF9I_zutNVK2lBABQrw-BgAhkZKIlvmbgk");

    @Test
    public void shouldVerifyWebhookWithCorrectSignature() throws Exception {
        String signature = "4d48a688e8bd6c313e3eecc78fa55b3e4ae23c65e70cf35038010f47742fb670";
        String body = Resources.toString(Resources.getResource("webhook.json"), UTF_8);

        verifier.verify(body, signature);
    }

    @Test(expected = InvalidWebhookException.class)
    public void shouldThrowForWebhookWithIncorrectSignature() throws Exception {
        String signature = "4d48a688e8bd6c313e3eecc78fa55b3e4ae23c65e70cf35038010f47742fb671";
        String body = Resources.toString(Resources.getResource("webhook.json"), UTF_8);

        verifier.verify(body, signature);
    }
}
