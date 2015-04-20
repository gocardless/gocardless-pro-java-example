package com.enterprisesolutions.api;

import org.apache.commons.lang3.StringUtils;

public enum Product {
    BEAN_PROVIDER("BeanProvider", 1000);

    private final String description;
    private final int price;

    Product(String description, int price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return StringUtils.lowerCase(name());
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
