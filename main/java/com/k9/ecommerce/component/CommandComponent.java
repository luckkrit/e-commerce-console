package com.k9.ecommerce.component;

import com.k9.ecommerce.command.*;
import com.k9.ecommerce.module.CartRepositoryModule;
import com.k9.ecommerce.module.CartServiceModule;
import com.k9.ecommerce.module.ProductRepositoryModule;
import com.k9.ecommerce.module.ProductServiceModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ProductServiceModule.class, ProductRepositoryModule.class, CartServiceModule.class,
        CartRepositoryModule.class})
public interface CommandComponent {
    void inject(AddProductCommand addProductCommand);

    void inject(RemoveProductCommand removeProduct);

    void inject(AddCartCommand addCartCommand);

    void inject(RemoveCartCommand removeCartCommand);

    void inject(ViewCartCommand viewCartCommand);

    void inject(CheckoutCommand checkoutCommand);
}
