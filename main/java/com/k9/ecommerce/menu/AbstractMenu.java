package com.k9.ecommerce.menu;

import com.k9.ecommerce.command.Command;
import com.k9.ecommerce.command.MenuCommand;
import com.k9.ecommerce.command.QuitCommand;
import com.k9.ecommerce.component.DaggerMenuComponent;
import com.k9.ecommerce.component.MenuComponent;

import javax.inject.Inject;
import java.util.HashMap;

public abstract class AbstractMenu implements Menu {

    protected HashMap<Choices, Command> menuCommands = new HashMap<>();
    @Inject
    ResourceManager resourceManager;
    @Inject
    KeyboardScanner keyboardScanner;

    public AbstractMenu() {
        MenuComponent menuComponent = DaggerMenuComponent.create();
        menuComponent.inject(this);
    }


    protected String getStringFromFile(String fileName) {
        return resourceManager.getStringFromFile(fileName);
    }

    protected void printMenu(String content) {

        String template = getStringFromFile("menu_template.txt");
        template = template.replace("{{content}}", content);
        System.out.println(template);
    }

    protected void addCommand(Choices choices, Command command) {
        this.menuCommands.put(choices, command);
    }

    protected void initCommand() {

    }

    protected void printMenu() {

    }

    @Override
    public void showMenu(MenuNavigator menuNavigator) {
        initCommand();
        printMenu();
        executeCommand();
    }

    protected void executeCommand() {

        Choices choices = null;
        Command command = null;
        do {
            System.out.print(" > ");
            String answer = keyboardScanner.getScanner().next();
            choices = Choices.getChoicesFromString(answer);
            if (menuCommands.containsKey(choices)) {
                command = menuCommands.get(choices);
                if (command instanceof MenuCommand || command instanceof QuitCommand) {
                    break;
                } else {
                    command.execute();
                    printMenu();
                }
            }
        } while (true);
        // if command is menu command or quit command
        command.execute();
    }
}
