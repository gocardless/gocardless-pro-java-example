package com.enterprisesolutions.api;

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
}
