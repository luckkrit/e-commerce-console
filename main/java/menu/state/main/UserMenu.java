package menu.state.main;

import menu.MenuContext;
import menu.state.AnswerType;
import menu.state.MenuState;
import menu.state.user.CheckoutMenu;
import menu.state.user.RemoveCartMenu;
import menu.state.user.ViewCartMenu;
import menu.state.user.ViewProductMenu;

public class UserMenu extends MenuState {
    @Override
    public void show(MenuContext menuContext) {
        clearScreen();
        String question = getMenuStringFromFile("menu/user_menu.txt");
        System.out.println(question);
        String answer = "";
        boolean isAnswer = false;
        do {
            System.out.print("> ");
            answer = keyboardScanner.next().toLowerCase();
            AnswerType answerType = getAnswerType(answer);
            switch (answerType) {
                case back:
                    menuContext.setMenuState(new MainMenu());
                    isAnswer = true;
                    break;
                case quit:
                    System.exit(1);
                    break;
                case a:
                    menuContext.setMenuState(new ViewProductMenu());
                    isAnswer = true;
                    break;
                case b:
                    menuContext.setMenuState(new ViewCartMenu());
                    isAnswer = true;
                    break;
                case c:
                    menuContext.setMenuState(new RemoveCartMenu());
                    isAnswer = true;
                    break;
                case d:
                    menuContext.setMenuState(new CheckoutMenu());
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
