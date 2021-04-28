package com.k9.ecommerce.command;

import com.k9.ecommerce.cart.CartService;
import com.k9.ecommerce.component.CommandComponent;
import com.k9.ecommerce.component.DaggerCommandComponent;
import com.k9.ecommerce.menu.KeyboardScanner;
import com.k9.ecommerce.menu.ResourceManager;

import javax.inject.Inject;

public class ViewCartCommand implements Command {
    public ViewCartCommand() {
        CommandComponent commandComponent = DaggerCommandComponent.create();
        commandComponent.inject(this);
    }

    @Inject
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    private CartService cartService;

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

    @Override
    public void execute() {

        String questionTemplate = resourceManager.getStringFromFile("list_cart.txt")
                .replace("{{total_cart_items}}", String.valueOf(cartService.totalCarts()))
                .replace("{{carts}}", cartService.getCartsString());
        System.out.println(questionTemplate);
        System.out.println("Press any key to continue...");
        System.out.print(" > ");
        keyboardScanner.getScanner().nextLine();
    }
}
