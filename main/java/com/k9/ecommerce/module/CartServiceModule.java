package com.k9.ecommerce.module;

import com.k9.ecommerce.cart.CartRepository;
import com.k9.ecommerce.cart.CartService;
import com.k9.ecommerce.product.ProductRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CartServiceModule {
    static CartService cartService;

    @Singleton
    @Provides
    static CartService provideCartService(CartRepository cartRepository, ProductRepository productRepository) {
        if (cartService == null) {
            cartService = new CartService();
            cartService.setCartRepository(cartRepository);
            cartService.setProductRepository(productRepository);
        }
        return cartService;
    }
}
