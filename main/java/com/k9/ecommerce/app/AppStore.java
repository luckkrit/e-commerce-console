package com.k9.ecommerce.app;

import com.k9.ecommerce.cart.CartService;
import com.k9.ecommerce.product.ProductService;

import javax.inject.Inject;

public class AppStore {
    @Inject
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public CartService getCartService() {
        return cartService;
    }

    private ProductService productService;

    @Inject
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    private CartService cartService;
}
