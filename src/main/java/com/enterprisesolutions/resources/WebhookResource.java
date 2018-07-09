package com.enterprisesolutions.resources;

import com.gocardless.Webhook;
import com.gocardless.resources.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/webhooks")
public class WebhookResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookResource.class);

    private final String webhookSecret;

    public WebhookResource(String webhookSecret) {
        this.webhookSecret = webhookSecret;
    }

    @POST
    public void handleWebhook(@HeaderParam("Webhook-Signature") String signature,
                              String body) {
        List<Event> events = Webhook.parse(body, signature, webhookSecret);

        for (Event event : events) {
            LOGGER.info(
                "Received webhook: resource [{}], action [{}]",
                event.getResourceType(),
                event.getAction()
            );
        }
    }
}
