package com.enterprisesolutions.resources;

import com.enterprisesolutions.api.Product;
import com.enterprisesolutions.views.HomeView;
import com.enterprisesolutions.views.SuccessView;
import com.gocardless.GoCardlessClient;
import com.gocardless.resources.Creditor;
import com.gocardless.resources.RedirectFlow;
import com.gocardless.resources.Subscription;
import io.dropwizard.jersey.sessions.Session;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

import static com.gocardless.services.SubscriptionService.SubscriptionCreateRequest.IntervalUnit.MONTHLY;

@Path("/")
public class RedirectFlowResource {
    private final GoCardlessClient goCardless;

    public RedirectFlowResource(GoCardlessClient goCardless) {
        this.goCardless = goCardless;
    }

    @GET
    public HomeView home() {
        return new HomeView();
    }

    @GET
    @Path("/subscribe")
    public Response startFlow(@Session HttpSession session,
                              @Context UriInfo uriInfo,
                              @QueryParam("product") Product product) {
        Creditor creditor = goCardless.creditors().list().execute().getItems().get(0);

        URI redirectUri = UriBuilder.fromUri(uriInfo.getRequestUri())
                .replacePath("/redirect")
                .replaceQueryParam("product", product)
                .build();

        RedirectFlow flow = goCardless.redirectFlows().create()
                .withDescription(String.format("%s (£%s per month)", product.getDescription(), product.getPrice()))
                .withSessionToken(session.getId())
                .withSuccessRedirectUrl(redirectUri.toString())
                .withLinksCreditor(creditor.getId())
                .execute();

        return Response.seeOther(URI.create(flow.getRedirectUrl())).build();
    }

    @GET
    @Path("/redirect")
    public Response completeFlow(@Session HttpSession session,
                                 @QueryParam("product") Product product,
                                 @QueryParam("redirect_flow_id") String redirectFlowId) {
        RedirectFlow redirectFlow = goCardless.redirectFlows().complete(redirectFlowId)
                .withSessionToken(session.getId())
                .execute();

        Subscription subscription = goCardless.subscriptions().create()
                .withAmount(product.getPrice() * 100)
                .withCurrency("GBP")
                .withName(product.getDescription())
                .withInterval(1)
                .withIntervalUnit(MONTHLY)
                .withLinksMandate(redirectFlow.getLinks().getMandate())
                .execute();

        URI successUri = UriBuilder.fromUri("/success")
                .replaceQueryParam("product", product)
                .replaceQueryParam("firstPaymentDate", subscription.getUpcomingPayments().get(0).getChargeDate())
                .build();

        return Response.seeOther(successUri).build();
    }

    @GET
    @Path("/success")
    public SuccessView success(@QueryParam("product") Product product,
                               @QueryParam("firstPaymentDate") String firstPaymentDate) {
        return new SuccessView(product, firstPaymentDate);
    }
}
