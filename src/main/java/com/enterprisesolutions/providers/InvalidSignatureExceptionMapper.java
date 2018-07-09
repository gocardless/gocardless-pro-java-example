package com.enterprisesolutions.providers;

import com.gocardless.errors.InvalidSignatureException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidSignatureExceptionMapper implements ExceptionMapper<InvalidSignatureException> {
    @Override
    public Response toResponse(InvalidSignatureException exception) {
        return Response.status(498).entity("Invalid webhook signature").build();
    }
}
