package com.enterprisesolutions.api;

public enum Product {
    BEAN_PROVIDER("BeanProvider", 1000);

    private final String name;
    private final int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
