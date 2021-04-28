package com.k9.ecommerce.menu;

import com.k9.ecommerce.command.MenuCommand;
import com.k9.ecommerce.command.QuitCommand;

public class MainMenu extends AbstractMenu {


    @Override
    public void initCommand() {

        addCommand(Choices.A, new MenuCommand(new UserMenu()));
        addCommand(Choices.B, new MenuCommand(new AdminMenu()));
        addCommand(Choices.BACK, new MenuCommand(new MainMenu()));
        addCommand(Choices.QUIT, new QuitCommand());
    }

    @Override
    public void printMenu() {
        String mainMenuTemplate = getStringFromFile("main_menu.txt");
        printMenu(mainMenuTemplate);
    }

}
