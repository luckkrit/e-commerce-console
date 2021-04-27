package com.k9.ecommerce.command;

import com.k9.ecommerce.app.AppStore;
import com.k9.ecommerce.menu.Menu;

public class MenuCommand implements Command {

    private Menu menu;
    private AppStore appStore;

    public MenuCommand(AppStore appStore, Menu menu) {
        this.menu = menu;
        this.appStore = appStore;
    }


    @Override
    public void execute() {
        this.appStore.setMenu(menu);
        this.appStore.showMenu();
    }
}
