package com.gocardless.example.resources;

import com.gocardless.example.core.WebhookSignatureVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.security.GeneralSecurityException;

@Path("/webhooks")
public class WebhookResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookResource.class);
    private final WebhookSignatureVerifier signatureVerifier;

    public WebhookResource(WebhookSignatureVerifier signatureVerifier) {
        this.signatureVerifier = signatureVerifier;
    }

    @POST
    public void handleWebhook(@HeaderParam("Webhook-Key-Id") String key,
                              @HeaderParam("Webhook-Signature") String signature,
                              String body) throws GeneralSecurityException {
        signatureVerifier.verify(body, signature);

        LOGGER.info("Received webhook: key [{}], signature [{}], body [{}]", key, signature, body);
    }
}
