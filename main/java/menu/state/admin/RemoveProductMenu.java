package menu.state.admin;

import app.AppStore;
import menu.MenuContext;
import menu.state.MenuState;
import menu.state.main.AdminMenu;
import model.Product;

public class RemoveProductMenu extends MenuState {
    private Product product = new Product();

    @Override
    public void show(MenuContext menuContext) {
        clearScreen();
        String question = getMenuStringFromFile("menu/remove_product.txt");
        question = question.replace("{{total_products}}", String.format("%d", AppStore.getInstance()
                .getProductRepository().getProducts().size()));
        StringBuilder productsBuilder = new StringBuilder();
        for (Product product : AppStore.getInstance().getProductRepository().getProducts()) {
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
                    System.out.print("Please fill product id > ");
                    String productId = keyboardScanner.next();
                    Product findProduct = AppStore.getInstance().getProductRepository().getProduct(Integer.parseInt(productId));
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
                case "y":
                    if (product.getId() == 0) {
                        System.out.println("Product not found!");
                        System.out.println(question);
                        break;
                    }
                    AppStore.getInstance().getProductRepository().removeProduct(this.product.getId());
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
