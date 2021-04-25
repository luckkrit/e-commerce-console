package menu.state.user;

import menu.MenuContext;
import menu.state.MenuState;
import menu.state.main.UserMenu;
import model.Product;

public class ViewCartMenu extends MenuState {
    @Override
    public void show(MenuContext menuContext) {
        clearScreen();
        String question = getMenuStringFromFile("menu/list_cart.txt");
        question = question.replace("{{total_cart_items}}", String.format("%d", menuContext.getCartRepository()
                .getCarts().size()));
        StringBuilder cartsBuilder = new StringBuilder();
        for (int productId : menuContext.getCartRepository().getCarts().keySet()) {
            Product product = menuContext.getProductRepository().getProduct(productId);
            cartsBuilder.append(String.format("-   id: %d\n    name: %s\n    quantity: %d\n", product.getId(),
                    product.getName(),
                    menuContext.getCartRepository().getCarts().get(productId)));
        }
        question = question.replace("{{carts}}", cartsBuilder);
        System.out.println(question);
        String answer;
        boolean isAnswer = false;
        do {
            System.out.print("> ");
            answer = keyboardScanner.next().toLowerCase();
            switch (answer) {
                case BACK:
                    menuContext.setMenuState(new UserMenu());
                    isAnswer = true;
                    break;
                case QUIT:
                    System.exit(1);
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
