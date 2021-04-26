package com.k9.ecommerce.cart;

import lombok.Data;

import javax.inject.Inject;
import java.util.HashMap;

@Data
public class CartRepository {
    private HashMap<Long, Integer> carts = new HashMap<>();

    @Inject
    public CartRepository() {
    }
}
