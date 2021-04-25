package menu.state.main;

import menu.MenuContext;
import menu.state.MenuState;
import menu.state.admin.AddProductMenu;
import menu.state.admin.RemoveProductMenu;

public class AdminMenu extends MenuState {
    @Override
    public void show(MenuContext menuContext) {
        clearScreen();
        String question = getMenuStringFromFile("menu/admin_menu.txt");
        System.out.println(question);
        String answer = "";
        boolean isAnswer = false;
        do {
            System.out.print("> ");
            answer = keyboardScanner.next().toLowerCase();
            switch (answer) {
                case BACK:
                    menuContext.setMenuState(new MainMenu());
                    isAnswer = true;
                    break;
                case QUIT:
                    System.exit(1);
                    break;
                case "a":
                    menuContext.setMenuState(new AddProductMenu());
                    isAnswer = true;
                    break;
                case "b":
                    menuContext.setMenuState(new RemoveProductMenu());
                    isAnswer = true;
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
