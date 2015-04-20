package com.enterprisesolutions.api;

import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

public enum Product {
    BEAN_PROVIDER(10),
    BRIDGE_DECORATOR(50),
    TRANSPORT_FACTORY(100);

    private final int price;

    Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return UPPER_UNDERSCORE.to(UPPER_CAMEL, name());
    }

    @Override
    public String toString() {
        return UPPER_UNDERSCORE.to(LOWER_UNDERSCORE, name());
    }

    public static Product fromString(String value) {
        for (Product product : values()) {
            if (StringUtils.equals(value, product.toString())) {
                return product;
            }
        }

        return null;
    }
}
