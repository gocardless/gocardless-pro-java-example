package com.enterprisesolutions.resources;

import com.enterprisesolutions.api.Product;
import com.enterprisesolutions.views.HomeView;
import com.gocardless.GoCardlessClient;
import com.gocardless.resources.Creditor;
import com.gocardless.resources.RedirectFlow;
import io.dropwizard.jersey.sessions.Session;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URI;

import static javax.ws.rs.core.MediaType.TEXT_HTML;

@Path("/")
public class RedirectFlowResource {
    private final GoCardlessClient goCardless;

    public RedirectFlowResource(GoCardlessClient goCardless) {
        this.goCardless = goCardless;
    }

    @GET
    @Produces(TEXT_HTML)
    public HomeView home() {
        return new HomeView();
    }

    @GET
    @Path("/purchase")
    public Response startFlow(@Session HttpSession session, @QueryParam("product") Product product) {
        Creditor creditor = goCardless.creditors().list().execute().getItems().get(0);

        RedirectFlow flow = goCardless.redirectFlows().create()
                .withDescription(product.getDescription())
                .withSessionToken(session.getId())
                .withSuccessRedirectUrl("http://localhost:5000/redirect?product=" + product.name())
                .withLinksCreditor(creditor.getId())
                .execute();

        return Response.seeOther(URI.create(flow.getRedirectUrl())).build();
    }
}
