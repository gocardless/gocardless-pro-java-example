package com.enterprisesolutions.views;

import com.enterprisesolutions.api.Product;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.dropwizard.views.View;

import java.util.List;

import static com.google.common.base.Charsets.UTF_8;

public class HomeView extends View {
    public HomeView() {
        super("home.ftl", UTF_8);
    }

    public List<Product> getProducts() {
        return Lists.newArrayList(Product.values());
    }
}
