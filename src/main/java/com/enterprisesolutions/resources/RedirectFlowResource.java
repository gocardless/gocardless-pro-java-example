package com.enterprisesolutions.resources;

import com.enterprisesolutions.views.HomeView;
import com.gocardless.GoCardlessClient;
import com.gocardless.resources.RedirectFlow;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public Response startFlow() {
        RedirectFlow flow = goCardless.redirectFlows().create().execute();
        return Response.seeOther(URI.create(flow.getRedirectUrl())).build();
    }
}
