package menu.state.admin;

import menu.MenuContext;
import menu.state.MenuState;
import menu.state.main.AdminMenu;
import model.Product;

public class AddProductMenu extends MenuState {


    private Product product = new Product();

    @Override
    public void show(MenuContext menuContext) {
        clearScreen();
        String question = getMenuStringFromFile("menu/add_product.txt");
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
                    menuContext.setMenuState(new AdminMenu());
                    isAnswer = true;
                    break;
                case QUIT:
                    System.exit(1);
                    break;
                case "a":
                    System.out.print("Please fill product name > ");
                    String productName = keyboardScanner.next();
                    this.product.setName(productName);
                    String confirmAddProductString = getMenuStringFromFile("menu/confirm_add_product.txt");
                    System.out.println(confirmAddProductString.replace("{{product.name}}", productName));
                    break;
                case "y":
                    if (product.getName() == null) {
                        System.out.println("Product name is empty!");
                        System.out.println(question);
                        break;
                    }
                    menuContext.getProductRepository().addProduct(this.product);
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
