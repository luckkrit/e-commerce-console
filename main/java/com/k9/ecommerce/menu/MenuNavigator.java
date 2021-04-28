package com.k9.ecommerce.menu;

public class MenuNavigator {
    public Menu getMenu() {
        return menu;
    }

    Menu menu;

    public MenuNavigator() {
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void showMenu() {
        this.menu.showMenu(this);
    }
}
