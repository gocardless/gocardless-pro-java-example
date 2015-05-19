package com.enterprisesolutions.views;

import com.enterprisesolutions.api.Product;
import io.dropwizard.views.View;

public class SuccessView extends View {
    private final Product product;
    private final String firstPaymentDate;
    private final String mandateId;

    public SuccessView(Product product, String firstPaymentDate, String mandateId) {
        super("success.ftl");

        this.product = product;
        this.firstPaymentDate = firstPaymentDate;
        this.mandateId = mandateId;
    }

    public Product getProduct() {
        return product;
    }

    public String getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public String getMandateId() {
        return mandateId;
    }
}
