package com.k9.ecommerce.cart;

import lombok.Data;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;

@Data
@Singleton
public class CartRepository {
    private HashMap<Long, Integer> carts = new HashMap<>();

    @Inject
    public CartRepository() {
    }
}
