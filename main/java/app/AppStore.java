package app;

import component.DaggerMenuContextComponent;
import component.MenuContextComponent;
import menu.MenuContext;

import javax.inject.Inject;

public final class AppStore {
    // static variable single_instance of type Singleton
    private static AppStore instance = null;

    @Inject
    MenuContext menuContext;

    public AppStore() {
        MenuContextComponent menuContextComponent = DaggerMenuContextComponent.create();
        menuContextComponent.inject(this);
    }

    // static method to create instance of Singleton class
    public static AppStore getInstance() {
        if (instance == null)
            instance = new AppStore();

        return instance;
    }

    public void showMenu() {
        this.menuContext.showMenu();
    }

}
