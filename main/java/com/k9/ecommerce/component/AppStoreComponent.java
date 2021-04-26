package com.k9.ecommerce.component;

import com.k9.ecommerce.app.AppStore;
import dagger.Component;

@Component
public interface AppStoreComponent {
    void inject(AppStore appStore);
}
