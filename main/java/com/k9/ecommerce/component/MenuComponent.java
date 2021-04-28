package com.k9.ecommerce.component;

import com.k9.ecommerce.command.AddProductCommand;
import com.k9.ecommerce.menu.AbstractMenu;
import com.k9.ecommerce.menu.AdminMenu;
import com.k9.ecommerce.menu.UserMenu;
import com.k9.ecommerce.module.CartRepositoryModule;
import com.k9.ecommerce.module.CartServiceModule;
import com.k9.ecommerce.module.ProductRepositoryModule;
import com.k9.ecommerce.module.ProductServiceModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ProductServiceModule.class, ProductRepositoryModule.class, CartServiceModule.class,
        CartRepositoryModule.class})
//@Component(modules = {MenuComponentModule.class})
public interface MenuComponent {
    void inject(AbstractMenu abstractMenu);

    void inject(UserMenu userMenu);

    void inject(AdminMenu adminMenu);

    void inject(AddProductCommand addProductCommand);
}
