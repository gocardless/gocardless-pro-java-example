package com.enterprisesolutions.resources;

import com.enterprisesolutions.providers.InvalidSignatureExceptionMapper;
import com.google.common.io.Resources;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static com.google.common.base.Charsets.UTF_8;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

public class WebhookResourceTest {
    private static final String WEBHOOK_SECRET = "ElfJ-3tF9I_zutNVK2lBABQrw-BgAhkZKIlvmbgk";

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new WebhookResource(WEBHOOK_SECRET))
            .addProvider(new InvalidSignatureExceptionMapper())
            .build();

    @Test
    public void shouldReturnOkIfSignatureValid() throws Exception {
        String signature = "4d48a688e8bd6c313e3eecc78fa55b3e4ae23c65e70cf35038010f47742fb670";
        String body = Resources.toString(Resources.getResource("webhook.json"), UTF_8);

        Response response = resources.client().target("/webhooks").request()
                .header("Webhook-Key-Id", "key")
                .header("Webhook-Signature", signature)
                .post(Entity.entity(body, APPLICATION_JSON_TYPE));

        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void shouldReturnErrorIfSignatureInvalid() throws Exception {
        String signature = "4d48a688e8bd6c313e3eecc78fa55b3e4ae23c65e70cf35038010f47742fb671";
        String body = Resources.toString(Resources.getResource("webhook.json"), UTF_8);

        Response response = resources.client().target("/webhooks").request()
                .header("Webhook-Key-Id", "key")
                .header("Webhook-Signature", signature)
                .post(Entity.entity(body, APPLICATION_JSON_TYPE));

        assertThat(response.getStatus()).isEqualTo(498);
    }
}