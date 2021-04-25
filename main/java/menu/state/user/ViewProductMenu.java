package menu.state.user;

import menu.MenuContext;
import menu.state.MenuState;
import menu.state.main.UserMenu;
import model.Product;

public class ViewProductMenu extends MenuState {
    private Product product = new Product();

    @Override
    public void show(MenuContext menuContext) {
        clearScreen();
        String question = getMenuStringFromFile("menu/list_product.txt");
        question = question.replace("{{total_products}}", String.format("%d", menuContext.getProductRepository()
                .getProducts().size()));
        StringBuilder productsBuilder = new StringBuilder();
        for (Product product : menuContext.getProductRepository().getProducts()) {
            productsBuilder.append(String.format("-   id: %d\n    name: %s\n", product.getId(), product.getName()));
        }
        question = question.replace("{{products}}", productsBuilder);
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
                case "a":
                    System.out.print("Please fill product id > ");
                    String productId = keyboardScanner.next();
                    String confirmAddCartString = getMenuStringFromFile("menu/confirm_add_cart.txt");
                    try {
                        Integer.parseInt(productId);
                    } catch (NumberFormatException e) {

                        System.out.println("Invalid product id!");
                        System.out.println(question);
                        break;
                    }
                    Product findProduct = menuContext.getProductRepository().getProduct(Integer.parseInt(productId));
                    if (findProduct == null) {
                        System.out.println("Product not found!");
                        System.out.println(question);
                        break;
                    }
                    product.setId(findProduct.getId());
                    product.setName(findProduct.getName());
                    confirmAddCartString = confirmAddCartString.replace("{{product.id}}",
                            Integer.valueOf(product.getId()).toString());
                    confirmAddCartString = confirmAddCartString.replace("{{product.name}}", product.getName());
                    System.out.println(confirmAddCartString);
                    break;
                case "y":
                    if (product.getId() == 0) {
                        System.out.println("Please select product!");
                        System.out.println(question);
                        break;
                    }
                    menuContext.getCartRepository().addCart(product.getId());
                    this.product = new Product();
                    isAnswer = true;
                    break;
                case "n":
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
