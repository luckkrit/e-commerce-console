package com.k9.ecommerce.menu;

import com.k9.ecommerce.component.AbstractMenuComponent;
import com.k9.ecommerce.component.DaggerAbstractMenuComponent;
import com.k9.ecommerce.product.Product;

import javax.inject.Inject;
import java.util.List;

public abstract class AbstractMenu implements Menu {

    @Inject
    ResourceManager resourceManager;
    @Inject
    KeyboardScanner keyboardScanner;

    public AbstractMenu() {
        this(DaggerAbstractMenuComponent.create());
    }

    public AbstractMenu(AbstractMenuComponent abstractMenuComponent) {
        abstractMenuComponent.inject(this);
    }


    protected String getStringFromFile(String fileName) {
        return resourceManager.getStringFromFile(fileName);
    }

    protected void printMenu(String content) {

        String template = getStringFromFile("menu_template.txt");
        template = template.replace("{{content}}", content);
        System.out.println(template);
    }

    protected String getProductsString(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        for (var product : products) {
            sb.append(product.toString());
        }
        return sb.toString();
    }
}
