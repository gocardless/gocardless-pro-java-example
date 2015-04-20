package com.enterprisesolutions.resources;

import com.enterprisesolutions.core.WebhookVerifier;
import com.enterprisesolutions.exceptions.InvalidWebhookException;
import com.enterprisesolutions.providers.InvalidWebhookExceptionMapper;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class WebhookResourceTest {
    private static final WebhookVerifier verifier = mock(WebhookVerifier.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new WebhookResource(verifier))
            .addProvider(new InvalidWebhookExceptionMapper())
            .build();

    @Before
    public void setUp() {
        reset(verifier);
    }

    @Test
    public void shouldReturnOkIfSignatureValid() {
        Response response = resources.client().target("/webhooks").request()
                .header("Webhook-Key-Id", "key")
                .header("Webhook-Signature", "sig")
                .post(Entity.entity("body", APPLICATION_JSON_TYPE));

        assertThat(response.getStatus()).isEqualTo(204);

        verify(verifier).verify("body", "key", "sig");
    }

    @Test
    public void shouldReturnErrorIfSignatureInvalid() {
        doThrow(new InvalidWebhookException("oh noes!")).when(verifier).verify("body", "key", "sig");

        Response response = resources.client().target("/webhooks").request()
                .header("Webhook-Key-Id", "key")
                .header("Webhook-Signature", "sig")
                .post(Entity.entity("body", APPLICATION_JSON_TYPE));

        assertThat(response.getStatus()).isEqualTo(498);

        verify(verifier).verify("body", "key", "sig");
    }
}