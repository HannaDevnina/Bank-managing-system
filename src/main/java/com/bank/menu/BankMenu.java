package com.bank.menu;

import com.bank.service.BankService;
import java.util.Scanner;

public class BankMenu extends BaseMenu{
   private BankService bankService;

    public BankMenu(BankService bankService, Scanner scanner) {
        super(scanner);
        this.bankService = bankService;
    }
    public void show() {
        while (true) {
            System.out.println("\n" + "=".repeat(30));
            System.out.println("  BANK MENU");
            System.out.println("=".repeat(30));
            System.out.println("1. Set Up Bank");
            System.out.println("2. Show Bank Details");
            System.out.println("3. Update Bank Name");
            System.out.println("4. Update Bank Address");
            System.out.println("5. Update Bank Phone");
            System.out.println("6. Update Bank Web URL" );
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1" :
                    try {
                        System.out.print("Enter Bank Name: ");
                        String name = scanner.nextLine().strip();
                        System.out.print("Enter Bank Address: ");
                        String address = scanner.nextLine().strip();
                        System.out.print("Enter Bank Phone Number: ");
                        String phone = scanner.nextLine().strip();
                        System.out.print("Enter Bank Web Url: ");
                        String webUrl = scanner.nextLine().strip();
                        bankService.setupBank(name, address, phone, webUrl);
                        System.out.println("Bank Created Successfully!");
                        bankService.showBank();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        pause();
                        break;

                case "2" :
                    bankService.showBank();
                    pause();
                    break;
                case "3" :
                    try {
                        System.out.print("Enter new bank name: ");
                        String name = scanner.nextLine().strip();
                        bankService.updateName(name);
                        System.out.println("Updated!");
                        bankService.showBank();
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "4" :
                    System.out.print("Enter new bank address: ");
                    String address = scanner.nextLine().strip();
                    try {
                        bankService.updateAddress(address);
                        System.out.println("Updated!");
                        bankService.showBank();
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "5" :
                    System.out.print("Enter new bank phone number: ");
                    String phone = scanner.nextLine().strip();
                    try {
                        bankService.updatePhone(phone);
                        System.out.println("Updated!");
                        bankService.showBank();
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "6" :
                    System.out.print("Enter new bank web URL: ");
                    String webUrl = scanner.nextLine().strip();
                    try {
                        bankService.updateWebUrl(webUrl);
                        System.out.println("Updated!");
                        bankService.showBank();
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
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
