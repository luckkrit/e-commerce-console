package menu.state.user;

import menu.MenuContext;
import menu.state.AnswerType;
import menu.state.MenuState;
import menu.state.main.AdminMenu;
import model.Product;

public class RemoveCartMenu extends MenuState {
    private Product product = new Product();

    @Override
    public void show(MenuContext menuContext) {
        clearScreen();
        String question = getMenuStringFromFile("menu/remove_cart.txt");
        question = question.replace("{{total_cart_items}}", String.format("%d", menuContext.getCartRepository().getCarts().size()));
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
            AnswerType answerType = getAnswerType(answer);
            switch (answerType) {
                case back:
                    menuContext.setMenuState(new AdminMenu());
                    isAnswer = true;
                    break;
                case quit:
                    System.exit(1);
                    break;
                case a:
                    System.out.print("Please fill product id > ");
                    String productId = keyboardScanner.next();
                    try {
                        Integer.parseInt(productId);
                    } catch (NumberFormatException e) {

                        System.out.println("Invalid product id!");
                        System.out.println(question);
                        break;
                    }
                    Product findProduct = menuContext.getProductRepository().getProduct(Integer.parseInt(productId));

                    if (findProduct == null || !menuContext.getCartRepository().containsCart(Integer.valueOf(productId))) {
                        System.out.println("Cart not found!");
                        System.out.println(question);
                        break;
                    }
                    String productName = findProduct.getName();
                    this.product.setName(productName);
                    this.product.setId(findProduct.getId());
                    String confirmRemoveCartString = getMenuStringFromFile("menu/confirm_remove_cart.txt");
                    confirmRemoveCartString = confirmRemoveCartString.replace("{{product.name}}", productName);
                    confirmRemoveCartString = confirmRemoveCartString.replace("{{product.id}}", productId);
                    System.out.println(confirmRemoveCartString);
                    break;
                case y:
                    if (product.getId() == 0) {
                        System.out.println("Cart not found!");
                        System.out.println(question);
                        break;
                    }
                    menuContext.getCartRepository().removeCart(this.product.getId());
                    this.product = new Product();
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
