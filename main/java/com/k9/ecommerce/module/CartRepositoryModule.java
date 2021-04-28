package com.k9.ecommerce.module;

import com.k9.ecommerce.cart.CartRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CartRepositoryModule {
    static CartRepository cartRepository;

    @Singleton
    @Provides
    static CartRepository providesCartRepository() {
        if (cartRepository == null) {
            cartRepository = new CartRepository();
        }
        return cartRepository;
    }
}
