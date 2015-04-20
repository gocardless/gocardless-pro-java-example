package com.enterprisesolutions.views;

import io.dropwizard.views.View;

import static com.google.common.base.Charsets.UTF_8;

public class HomeView extends View {
    public HomeView() {
        super("home.ftl", UTF_8);
    }
}
