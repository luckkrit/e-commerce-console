package com.k9.ecommerce.main;

import com.k9.ecommerce.app.AppStore;
import com.k9.ecommerce.component.AppStoreComponent;
import com.k9.ecommerce.component.DaggerAppStoreComponent;

public class Main {
    public static void main(String[] args) {
        AppStore appStore = new AppStore();
        AppStoreComponent appStoreComponent = DaggerAppStoreComponent.create();
        appStoreComponent.inject(appStore);
        appStore.showMenu();
    }
}
