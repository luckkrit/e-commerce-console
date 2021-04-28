package com.k9.ecommerce.app;

import com.k9.ecommerce.menu.MainMenu;
import com.k9.ecommerce.menu.MenuNavigator;

import javax.inject.Inject;

public class AppStore {

    public AppStore() {
    }

    MenuNavigator menuNavigator;

    @Inject
    public void setMenuNavigator(MenuNavigator menuNavigator) {
        this.menuNavigator = menuNavigator;
    }

    public void showMenu() {
        if (this.menuNavigator.getMenu() == null) {
            this.menuNavigator.setMenu(new MainMenu());
        }
        this.menuNavigator.showMenu();
    }
}
