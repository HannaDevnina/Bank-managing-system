package com.bank.menu;

import java.util.Scanner;

public abstract class BaseMenu {

    protected Scanner scanner;

    public BaseMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    protected void pause() {
        System.out.println(
                "\nPress Enter to continue...");
        scanner.nextLine();
    }

    public abstract void show();
}
