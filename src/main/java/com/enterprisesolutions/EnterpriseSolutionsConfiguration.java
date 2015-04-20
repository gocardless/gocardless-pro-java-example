package com.enterprisesolutions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EnterpriseSolutionsConfiguration extends Configuration {
    @JsonProperty
    @NotNull
    @Valid
    private final GoCardlessFactory goCardless;

    @JsonCreator
    public EnterpriseSolutionsConfiguration(@JsonProperty("goCardless") GoCardlessFactory goCardless) {
        this.goCardless = goCardless;
    }

    public GoCardlessFactory getGoCardless() {
        return goCardless;
    }
}
