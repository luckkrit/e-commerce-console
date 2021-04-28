package com.k9.ecommerce.command;

import com.k9.ecommerce.component.CommandComponent;
import com.k9.ecommerce.component.DaggerCommandComponent;
import com.k9.ecommerce.menu.KeyboardScanner;
import com.k9.ecommerce.menu.ResourceManager;
import com.k9.ecommerce.product.Product;
import com.k9.ecommerce.product.ProductService;

import javax.inject.Inject;

public class AddProductCommand implements Command {

    public AddProductCommand() {
        CommandComponent commandComponent = DaggerCommandComponent.create();
        commandComponent.inject(this);
    }

    @Inject
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    private ResourceManager resourceManager;

    @Inject
    public void setKeyboardScanner(KeyboardScanner keyboardScanner) {
        this.keyboardScanner = keyboardScanner;
    }

    private KeyboardScanner keyboardScanner;

    @Inject
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private ProductService productService;

    @Override
    public void execute() {
        String questionTemplate = resourceManager.getStringFromFile("add_product.txt");
        String confirmTemplate = resourceManager.getStringFromFile("confirm_add_product.txt");
        String productName = null;
        do {
            System.out.print(questionTemplate);
            System.out.print(" > ");
            productName = keyboardScanner.getScanner().next();
            String confirmTemplateTemp = confirmTemplate.replace("{{product.name}}", productName);
            System.out.println(confirmTemplateTemp);
            System.out.print(" > ");
            String confirm = keyboardScanner.getScanner().next();
            if (confirm.equals("y")) {
                break;
            }
        } while (true);
        Product product = new Product();
        product.setName(productName);
        productService.addProduct(product);
        System.out.println("Add product success");
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
