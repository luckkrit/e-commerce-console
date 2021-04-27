package com.k9.ecommerce.menu;

public enum Choices {
    A,
    B,
    C,
    D,
    BACK,
    QUIT;

    static Choices getChoicesFromString(String name) {
        Choices choices = null;
        try {
            choices = Choices.valueOf(name);
        } catch (IllegalArgumentException ignored) {

        }
        return choices;
    }
}
