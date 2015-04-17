package com.gocardless.example.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/webhooks")
public class WebhookResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookResource.class);

    @POST
    public void handleWebhook(@HeaderParam("Webhook-Key-Id") String key,
                              @HeaderParam("Webhook-Signature") String signature,
                              String body) {
        LOGGER.info("Received webhook: key [{}], signature [{}], body [{}]", key, signature, body);
    }
}
