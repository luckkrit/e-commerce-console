package com.k9.ecommerce.menu;

import com.k9.ecommerce.command.AddProductCommand;
import com.k9.ecommerce.command.MenuCommand;
import com.k9.ecommerce.command.QuitCommand;
import com.k9.ecommerce.command.RemoveProductCommand;
import com.k9.ecommerce.component.DaggerMenuComponent;
import com.k9.ecommerce.component.MenuComponent;
import com.k9.ecommerce.product.Product;
import com.k9.ecommerce.product.ProductService;

import javax.inject.Inject;
import java.util.List;

public class AdminMenu extends AbstractMenu {

    @Inject
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    ProductService productService;

    public AdminMenu() {
        MenuComponent menuComponent = DaggerMenuComponent.create();
        menuComponent.inject(this);
    }

    @Override
    protected void initCommand() {

        addCommand(Choices.A, new AddProductCommand());
        addCommand(Choices.B, new RemoveProductCommand());
        addCommand(Choices.BACK, new MenuCommand(new MainMenu()));
        addCommand(Choices.QUIT, new QuitCommand());
    }

    @Override
    protected void printMenu() {

        String productsTemplate = getStringFromFile("list_product.txt");
        List<Product> products = productService.getProducts();
        productsTemplate = productsTemplate.replace("{{total_products}}", Integer.toString(products.size()));
        productsTemplate = productsTemplate.replace("{{products}}", productService.getProductsString());
        String userMenuTemplate = getStringFromFile("admin_menu.txt");
        productsTemplate = productsTemplate + userMenuTemplate;
        printMenu(productsTemplate);

    }
}
