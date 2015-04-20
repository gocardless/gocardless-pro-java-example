package com.enterprisesolutions.providers;

import com.enterprisesolutions.views.ErrorView;
import com.gocardless.errors.GoCardlessApiException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static javax.ws.rs.core.MediaType.TEXT_HTML;

public class GoCardlessApiExceptionMapper implements ExceptionMapper<GoCardlessApiException> {
    @Override
    public Response toResponse(GoCardlessApiException exception) {
        return Response.serverError()
                .type(TEXT_HTML)
                .entity(new ErrorView(exception))
                .build();
    }
}
