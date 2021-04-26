package com.k9.ecommerce.cart;

import javax.inject.Inject;

public class CartService {
    @Inject
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    private CartRepository cartRepository;

    public void addCart(long productId) {
        if (this.cartRepository.getCarts().containsKey(productId)) {
            int total = this.cartRepository.getCarts().get(productId) + 1;
            this.cartRepository.getCarts().put(productId, total);
        }
        this.cartRepository.getCarts().putIfAbsent(productId, 1);
    }

    public void removeCart(long productId) {
        if (this.cartRepository.getCarts().containsKey(productId)) {
            int total = this.cartRepository.getCarts().get(productId) - 1;
            if (total == 0) {
                this.cartRepository.getCarts().remove(productId);
            } else {
                this.cartRepository.getCarts().put(productId, total);
            }
        }
    }

    @Inject
    public CartService() {
    }
}
