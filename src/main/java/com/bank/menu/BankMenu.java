package com.bank.menu;

import com.bank.service.BankService;
import java.util.Scanner;

public class BankMenu {
   private BankService bankService;
   private Scanner scanner;

    public BankMenu(BankService bankService, Scanner scanner) {
        this.bankService = bankService;
        this.scanner = scanner;
    }
    public void show() {
        while (true) {
            System.out.println("\n" + "=".repeat(30));
            System.out.println("  BANK MENU");
            System.out.println("=".repeat(30));
            System.out.println("1. Show Bank Details");
            System.out.println("2. Update Bank Name");
            System.out.println("3. Update Bank Address");
            System.out.println("4. Update Bank Phone");
            System.out.println("5. Update Bank Web URL" );
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" :
                    bankService.showBank();
                    break;
                case "2" :
                    System.out.print("Enter new bank name: ");
                    String name = scanner.nextLine().strip();
                    try {
                        bankService.updateName(name);
                        System.out.println("Updated! ✅");
                        bankService.showBank();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "3" :
                    System.out.print("Enter new bank address: ");
                    String address = scanner.nextLine().strip();
                    try {
                        bankService.updateAddress(address);
                        System.out.println("Updated! ✅");
                        bankService.showBank();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "4" :
                    System.out.print("Enter new bank phone number: ");
                    String phone = scanner.nextLine().strip();
                    try {
                        bankService.updatePhone(phone);
                        System.out.println("Updated! ✅");
                        bankService.showBank();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "5" :
                    System.out.print("Enter new bank web URL: ");
                    String webUrl = scanner.nextLine().strip();
                    try {
                        bankService.updateWebUrl(webUrl);
                        System.out.println("Updated! ✅");
                        bankService.showBank();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "0" :
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice!");

            }
        }
    }
}
