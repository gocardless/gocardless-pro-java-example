package com.enterprisesolutions.views;

import com.gocardless.errors.ApiError;
import com.gocardless.errors.ErrorType;
import com.gocardless.errors.GoCardlessApiException;
import io.dropwizard.views.View;

import java.util.List;

public class ErrorView extends View {
    private final GoCardlessApiException exception;

    public ErrorView(GoCardlessApiException exception) {
        super("error.ftl");

        this.exception = exception;
    }

    public ErrorType getType() {
        return exception.getType();
    }

    public String getDocumentationUrl() {
        return exception.getDocumentationUrl();
    }

    public String getRequestId() {
        return exception.getRequestId();
    }

    public int getCode() {
        return exception.getCode();
    }

    public List<ApiError> getErrors() {
        return exception.getErrors();
    }

    public String getMessage() {
        return exception.getMessage();
    }
}
