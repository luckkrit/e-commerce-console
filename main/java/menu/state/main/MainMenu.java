package menu.state.main;

import menu.MenuContext;
import menu.state.AnswerType;
import menu.state.MenuState;


public class MainMenu extends MenuState {

    @Override
    public void show(MenuContext menuContext) {
        clearScreen();
        String question = getMenuStringFromFile("menu/main_menu.txt");
        System.out.println(question);

        String answer = "";
        boolean isAnswer = false;
        do {
            System.out.print("> ");
            answer = keyboardScanner.next();
            AnswerType answerType = getAnswerType(answer);
            switch (answerType) {
                case back:
                    System.out.println("Cannot go back! This is a main menu");
                    break;
                case quit:
                    System.exit(1);
                    break;
                case a:
                    menuContext.setMenuState(new UserMenu());
                    isAnswer = true;
                    break;
                case b:
                    menuContext.setMenuState(new AdminMenu());
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
