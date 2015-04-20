package com.enterprisesolutions;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EnterpriseSolutionsConfiguration extends Configuration {
    @JsonProperty
    @NotNull
    @Valid
    private GoCardlessFactory goCardless;

    public GoCardlessFactory getGoCardless() {
        return goCardless;
    }
}
