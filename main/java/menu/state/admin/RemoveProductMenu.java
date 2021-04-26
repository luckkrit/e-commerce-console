package menu.state.admin;

import menu.MenuContext;
import menu.state.AnswerType;
import menu.state.MenuState;
import menu.state.main.AdminMenu;
import model.Product;

public class RemoveProductMenu extends MenuState {
    private Product product = new Product();

    @Override
    public void show(MenuContext menuContext) {
        clearScreen();
        String question = getMenuStringFromFile("menu/remove_product.txt");
        question = question.replace("{{total_products}}", String.format("%d", menuContext.getProductRepository().getProducts().size()));
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
                    if (findProduct == null) {
                        System.out.println("Product not found!");
                        System.out.println(question);
                        break;
                    }
                    String productName = findProduct.getName();
                    this.product.setName(productName);
                    this.product.setId(findProduct.getId());
                    String confirmAddProductString = getMenuStringFromFile("menu/confirm_remove_product.txt");
                    confirmAddProductString = confirmAddProductString.replace("{{product.name}}", productName);
                    confirmAddProductString = confirmAddProductString.replace("{{product.id}}", productId);
                    System.out.println(confirmAddProductString);
                    break;
                case y:
                    if (product.getId() == 0) {
                        System.out.println("Product not found!");
                        System.out.println(question);
                        break;
                    }
                    menuContext.getProductRepository().removeProduct(this.product.getId());
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
