package com.gocardless.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class GoCardlessExampleConfiguration extends Configuration {
    @JsonProperty
    @NotNull
    @Valid
    private final GoCardlessFactory goCardless;

    @JsonCreator
    public GoCardlessExampleConfiguration(@JsonProperty("goCardless") GoCardlessFactory goCardless) {
        this.goCardless = goCardless;
    }

    public GoCardlessFactory getGoCardless() {
        return goCardless;
    }
}
