package com.gocardless.example.providers;

import com.gocardless.example.exceptions.InvalidWebhookSignatureException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidWebhookSignatureExceptionMapper implements ExceptionMapper<InvalidWebhookSignatureException> {
    @Override
    public Response toResponse(InvalidWebhookSignatureException exception) {
        return Response.status(498).entity(exception.getMessage()).build();
    }
}
