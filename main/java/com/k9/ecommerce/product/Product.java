package com.k9.ecommerce.product;

import lombok.Data;

@Data
public class Product {
    private long id;

    @Override
    public String toString() {
        return String.format("- id: %d\n  name: %s\n", id, name);
    }

    private String name;
}
