package com.k9.ecommerce.command;

import com.k9.ecommerce.component.CommandComponent;
import com.k9.ecommerce.component.DaggerCommandComponent;
import com.k9.ecommerce.menu.KeyboardScanner;
import com.k9.ecommerce.menu.ResourceManager;
import com.k9.ecommerce.product.Product;
import com.k9.ecommerce.product.ProductService;

import javax.inject.Inject;
import java.util.InputMismatchException;

public class RemoveProductCommand implements Command {
    public RemoveProductCommand() {

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
        String questionTemplate = resourceManager.getStringFromFile("remove_product.txt");
        String confirmTemplate = resourceManager.getStringFromFile("confirm_remove_product.txt");
        long productId = 0;
        do {
            System.out.print(questionTemplate);
            System.out.print(" > ");
            try {

                productId = keyboardScanner.getScanner().nextLong();
                Product product = productService.getProduct(productId);
                if (product == null) {
                    System.out.println("Product not found!");
                    continue;
                }
                String confirmTemplateTemp = confirmTemplate.replace("{{product.name}}", product.getName())
                        .replace("{{product.id}}", String.valueOf(product.getId()));
                System.out.println(confirmTemplateTemp);
                System.out.print(" > ");
                String confirm = keyboardScanner.getScanner().next();
                if (confirm.equals("y")) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }
        } while (true);
        productService.removeProduct(productId);
        System.out.println("Remove product success");
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
