package menu.state.user;

import menu.MenuContext;
import menu.state.AnswerType;
import menu.state.MenuState;
import menu.state.main.UserMenu;
import model.Product;

import java.io.IOException;

public class CheckoutMenu extends MenuState {
    private String address = "";
    private String checkoutForm = "";

    @Override
    public void show(MenuContext menuContext) {
        clearScreen();
        String question = getMenuStringFromFile("menu/checkout_cart.txt");
        checkoutForm = getStringFromFile("menu/checkout_form.txt");
        question = question.replace("{{total_cart_items}}", String.format("%d", menuContext.getProductRepository()
                .getProducts().size()));
        checkoutForm = checkoutForm.replace("{{total_cart_items}}", String.format("%d", menuContext.getProductRepository()
                .getProducts().size()));
        StringBuilder cartsBuilder = new StringBuilder();
        for (int productId : menuContext.getCartRepository().getCarts().keySet()) {
            Product product = menuContext.getProductRepository().getProduct(productId);
            cartsBuilder.append(String.format("-   id: %d\n    name: %s\n    quantity: %d\n", product.getId(),
                    product.getName(),
                    menuContext.getCartRepository().getCarts().get(productId)));
        }
        question = question.replace("{{carts}}", cartsBuilder);
        checkoutForm = checkoutForm.replace("{{carts}}", cartsBuilder);
        question = question.replace("{{address}}", address);
        System.out.println(question);
        String answer;
        boolean isAnswer = false;
        do {
            System.out.print("> ");
            answer = keyboardScanner.next().toLowerCase();
            AnswerType answerType = getAnswerType(answer);
            switch (answerType) {
                case back:
                    menuContext.setMenuState(new UserMenu());
                    isAnswer = true;
                    break;
                case quit:
                    System.exit(1);
                    break;
                case a:
                    keyboardScanner.nextLine();
                    System.out.print("Please fill address > ");
                    address = keyboardScanner.nextLine();
                    String confirmAddressString = getMenuStringFromFile("menu/confirm_address.txt");
                    System.out.println(confirmAddressString.replace("{{address}}", address));
                    break;
                case y:
                    if (address.length() == 0) {
                        System.out.println("Address is empty!");
                        System.out.println(question);
                        break;
                    }
                    if (menuContext.getCartRepository().getCarts().size() == 0) {
                        System.out.println("Empty cart!");
                        System.out.println(question);
                        break;
                    }
                    try {
                        checkoutForm = checkoutForm.replace("{{address}}", address);
                        menuContext.getCheckoutService().saveToFile(checkoutForm);
                    } catch (IOException e) {
                        System.out.println("Unable to save file. Checkout failed!");
                        System.out.println(question);
                        break;
                    }
                    this.address = "";
                    isAnswer = true;
                    break;
                case n:
                    System.out.println(question);
                    break;
                default:
                    System.out.println("Invalid answer");
                    System.out.println(question);
                    break;
            }
        } while (!isAnswer);
        menuContext.showMenu();
    }
}
