package com.k9.ecommerce.command;

public class QuitCommand implements Command {
    @Override
    public void execute() {
        System.exit(1);
    }
}
