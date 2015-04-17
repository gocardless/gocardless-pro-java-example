package com.gocardless.example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HelloWorldResource {
    @GET
    public String sayHello() {
        return "Hello, world!";
    }
}
