package com.k9.ecommerce.module;

import com.k9.ecommerce.cart.CheckoutService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CheckoutServiceModule {

    static CheckoutService checkoutService;

    @Provides
    @Singleton
    CheckoutService providesCheckoutService() {
        if (checkoutService == null) {
            checkoutService = new CheckoutService();
        }
        return checkoutService;
    }

}
