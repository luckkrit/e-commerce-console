package com.k9.ecommerce.menu;

import com.k9.ecommerce.app.AppStore;
import com.k9.ecommerce.command.Command;
import com.k9.ecommerce.command.MenuCommand;

import java.util.HashMap;

public class MainMenu extends AbstractMenu {
    private HashMap<Choices, Command> menuCommands = new HashMap<>();

    @Override
    public void showMenu(AppStore appStore) {
        menuCommands.put(Choices.A, new MenuCommand(appStore, new UserMenu()));
        menuCommands.put(Choices.B, new MenuCommand(appStore, new AdminMenu()));
        menuCommands.put(Choices.BACK, new MenuCommand(appStore, new MainMenu()));

        String mainMenuTemplate = getStringFromFile("main_menu.txt");
        printMenu(mainMenuTemplate);
        Choices choices = null;
        do {
            String answer = keyboardScanner.getScanner().next();
            choices = Choices.getChoicesFromString(answer);
            if (menuCommands.containsKey(choices)) {
                Command command = menuCommands.get(choices);
                command.execute();
            }
        } while (!menuCommands.containsKey(choices));
        appStore.showMenu();
    }

}
