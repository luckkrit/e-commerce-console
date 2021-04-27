package com.k9.ecommerce.menu;

import javax.inject.Inject;
import java.util.Scanner;

public class KeyboardScanner {
    public Scanner getScanner() {
        return scanner;
    }

    private final Scanner scanner;

    @Inject
    public KeyboardScanner() {
        scanner = new Scanner(System.in);
    }
}
