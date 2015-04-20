package com.enterprisesolutions.resources;

import com.enterprisesolutions.views.HomeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class ExampleResource {
    @GET
    public HomeView home() {
        return new HomeView();
    }
}
