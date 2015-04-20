package com.enterprisesolutions.providers;

import com.enterprisesolutions.exceptions.InvalidWebhookException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidWebhookExceptionMapper implements ExceptionMapper<InvalidWebhookException> {
    @Override
    public Response toResponse(InvalidWebhookException exception) {
        return Response.status(498).entity(exception.getMessage()).build();
    }
}
