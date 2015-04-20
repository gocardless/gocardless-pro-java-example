package com.enterprisesolutions.views;

import com.enterprisesolutions.api.Product;
import io.dropwizard.views.View;

public class SuccessView extends View {
    private final Product product;

    public SuccessView(Product product) {
        super("success.ftl");

        this.product = product;
    }
}
