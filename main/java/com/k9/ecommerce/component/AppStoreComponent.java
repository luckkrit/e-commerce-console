package com.k9.ecommerce.component;

import com.k9.ecommerce.app.AppStore;
import com.k9.ecommerce.module.MenuNavigatorModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {MenuNavigatorModule.class})
public interface AppStoreComponent {
    void inject(AppStore appStore);
}
