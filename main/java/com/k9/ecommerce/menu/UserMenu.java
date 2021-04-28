package com.k9.ecommerce.menu;

import com.k9.ecommerce.command.*;
import com.k9.ecommerce.component.DaggerMenuComponent;
import com.k9.ecommerce.component.MenuComponent;
import com.k9.ecommerce.product.Product;
import com.k9.ecommerce.product.ProductService;

import javax.inject.Inject;
import java.util.List;

public class UserMenu extends AbstractMenu {


    public UserMenu() {
        MenuComponent menuComponent = DaggerMenuComponent.create();
        menuComponent.inject(this);
    }

    @Inject
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    ProductService productService;

    @Override
    protected void initCommand() {

        addCommand(Choices.A, new AddCartCommand());
        addCommand(Choices.B, new ViewCartCommand());
        addCommand(Choices.C, new RemoveCartCommand());
        addCommand(Choices.D, new CheckoutCommand());
        addCommand(Choices.BACK, new MenuCommand(new MainMenu()));
        addCommand(Choices.QUIT, new QuitCommand());
    }

    @Override
    protected void printMenu() {
        String productsTemplate = getStringFromFile("list_product.txt");
        List<Product> products = productService.getProducts();
        productsTemplate = productsTemplate.replace("{{total_products}}", Integer.toString(products.size()));
        productsTemplate = productsTemplate.replace("{{products}}", productService.getProductsString());
        String userMenuTemplate = getStringFromFile("user_menu.txt");
        productsTemplate = productsTemplate + userMenuTemplate;
        printMenu(productsTemplate);
    }

}
