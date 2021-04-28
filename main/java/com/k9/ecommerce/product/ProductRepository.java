package com.k9.ecommerce.product;

import lombok.Data;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Data
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    @Inject
    public ProductRepository() {
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
