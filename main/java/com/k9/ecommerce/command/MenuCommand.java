package com.k9.ecommerce.command;

import com.k9.ecommerce.component.DaggerMenuNavigatorComponent;
import com.k9.ecommerce.component.MenuNavigatorComponent;
import com.k9.ecommerce.menu.Menu;
import com.k9.ecommerce.menu.MenuNavigator;

import javax.inject.Inject;

public class MenuCommand implements Command {

    private Menu menu;

    @Inject
    public void setMenuNavigator(MenuNavigator menuNavigator) {
        this.menuNavigator = menuNavigator;
    }

    private MenuNavigator menuNavigator;

    public MenuCommand() {
        MenuNavigatorComponent menuNavigatorComponent = DaggerMenuNavigatorComponent.create();
        menuNavigatorComponent.inject(this);
    }

    public MenuCommand(Menu menu) {
        this();
        this.menu = menu;
    }


    @Override
    public void execute() {
        this.menuNavigator.setMenu(menu);
        this.menuNavigator.showMenu();
    }
}
