package com.k9.ecommerce.component;

import com.k9.ecommerce.command.MenuCommand;
import com.k9.ecommerce.module.MenuNavigatorModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {MenuNavigatorModule.class})
public interface MenuNavigatorComponent {
    void inject(MenuCommand menuCommand);
}
