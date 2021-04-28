package com.k9.ecommerce.product;

import lombok.Data;

@Data
public class Product {
    private long id;

    @Override
    public String toString() {
        return String.format("- Id: %d\n  Name: %s\n", id, name);
    }

    private String name;
}
