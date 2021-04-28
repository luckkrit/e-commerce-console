package com.k9.ecommerce.command;

import com.k9.ecommerce.cart.CartService;
import com.k9.ecommerce.cart.CheckoutService;
import com.k9.ecommerce.component.CommandComponent;
import com.k9.ecommerce.component.DaggerCommandComponent;
import com.k9.ecommerce.menu.KeyboardScanner;
import com.k9.ecommerce.menu.ResourceManager;

import javax.inject.Inject;
import java.io.IOException;

public class CheckoutCommand implements Command {
    public CheckoutCommand() {
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

    private CheckoutService checkoutService;

    @Inject
    public void setCheckoutService(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @Override
    public void execute() {
        if (cartService.totalCarts() == 0) {
            System.out.println("Empty cart, cannot checkout");
        } else {

            String questionTemplate = resourceManager.getStringFromFile("checkout_cart.txt")
                    .replace("{{total_cart_items}}", String.valueOf(cartService.totalCarts()))
                    .replace("{{carts}}", cartService.getCartsString());
            String confirmTemplate = resourceManager.getStringFromFile("confirm_address.txt");
            String checkoutFormTemplate = resourceManager.getStringFromFile("checkout_form.txt")
                    .replace("{{total_cart_items}}", String.valueOf(cartService.totalCarts()))
                    .replace("{{carts}}", cartService.getCartsString());
            String checkoutForm = "";
            do {
                System.out.println(questionTemplate);
                System.out.print(" > ");
                String address = keyboardScanner.getScanner().nextLine();
                while (address.equals("") || address.length() == 0) {
                    address = keyboardScanner.getScanner().nextLine();
                }
                System.out.println();
                String confirmTemplateTemp = confirmTemplate.replace("{{address}}", address);
                System.out.println(confirmTemplateTemp);
                System.out.print(" > ");
                String confirm = keyboardScanner.getScanner().next();
                if (confirm.equals("y")) {
                    checkoutForm = checkoutFormTemplate.replace("{{address}}", address);
                    cartService.checkout();
                    break;
                }
            } while (true);
            try {
                checkoutService.saveToFile(checkoutForm);
                System.out.println("Checkout success");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Checkout failed");
            }
        }
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
