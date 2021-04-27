package com.k9.ecommerce.component;

import com.k9.ecommerce.menu.AbstractMenu;
import dagger.Component;

@Component
public interface AbstractMenuComponent {
    void inject(AbstractMenu abstractMenu);
}
