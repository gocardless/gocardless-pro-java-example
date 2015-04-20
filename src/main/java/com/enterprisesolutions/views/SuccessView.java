package com.enterprisesolutions.views;

import com.enterprisesolutions.api.Product;
import io.dropwizard.views.View;

public class SuccessView extends View {
    private final Product product;
    private final String firstPaymentDate;

    public SuccessView(Product product, String firstPaymentDate) {
        super("success.ftl");

        this.product = product;
        this.firstPaymentDate = firstPaymentDate;
    }

    public Product getProduct() {
        return product;
    }

    public String getFirstPaymentDate() {
        return firstPaymentDate;
    }
}
