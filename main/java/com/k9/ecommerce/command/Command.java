package com.k9.ecommerce.command;

public interface Command {
    int TIMEOUT = 2000;

    void execute();
}
