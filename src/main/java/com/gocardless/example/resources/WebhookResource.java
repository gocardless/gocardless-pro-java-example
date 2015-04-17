package com.gocardless.example.resources;

import com.gocardless.example.core.WebhookVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/webhooks")
public class WebhookResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookResource.class);
    private final WebhookVerifier signatureVerifier;

    public WebhookResource(WebhookVerifier signatureVerifier) {
        this.signatureVerifier = signatureVerifier;
    }

    @POST
    public void handleWebhook(@HeaderParam("Webhook-Key-Id") String key,
                              @HeaderParam("Webhook-Signature") String signature,
                              String body) {
        signatureVerifier.verify(body, key, signature);

        LOGGER.info("Received webhook: body [{}]", body);
    }
}
