package com.k9.ecommerce.module;

import com.k9.ecommerce.menu.MenuNavigator;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class MenuNavigatorModule {
    @Provides
    @Singleton
    MenuNavigator providesMenuNavigator() {
        return new MenuNavigator();
    }

}
