package com.k9.ecommerce.menu;

import com.k9.ecommerce.component.AbstractMenuComponent;
import com.k9.ecommerce.component.DaggerAbstractMenuComponent;

import javax.inject.Inject;

public abstract class AbstractMenu implements Menu {

    @Inject
    ResourceManager resourceManager;

    public AbstractMenu() {
        this(DaggerAbstractMenuComponent.create());
    }

    public AbstractMenu(AbstractMenuComponent abstractMenuComponent) {
        abstractMenuComponent.inject(this);
    }


    protected String getStringFromFile(String fileName) {
        return resourceManager.getStringFromFile(fileName);
    }
}
