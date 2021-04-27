package com.k9.ecommerce.menu;

import com.k9.ecommerce.app.AppStore;
import com.k9.ecommerce.product.Product;

import javax.inject.Inject;
import java.util.List;

public class UserMenu extends AbstractMenu {
    @Inject
    public UserMenu() {
    }

    @Override
    public void showMenu(AppStore appStore) {
        String productsTemplate = getStringFromFile("list_product.txt");
        List<Product> products = appStore.getProductService().getProducts();
        productsTemplate = productsTemplate.replace("{{total_products}}", Integer.toString(products.size()));
        productsTemplate = productsTemplate.replace("{{products}}", getProductsString(products));
        String userMenuTemplate = getStringFromFile("user_menu.txt");
        productsTemplate = productsTemplate + userMenuTemplate;
        printMenu(productsTemplate);
    }
}
