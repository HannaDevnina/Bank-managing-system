package com.bank.menu;

import com.bank.service.BankService;
import com.bank.service.CustomerService;
import com.bank.service.EmployeeService;

import java.util.Scanner;

public class MainMenu {

    private BankService bankService;
    private EmployeeService employeeService;
    private CustomerService customerService;
    private Scanner scanner;

    public MainMenu(BankService bankService,
                    EmployeeService employeeService,
                    CustomerService customerService,
                    Scanner scanner) {
        this.bankService = bankService;
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.scanner = scanner;
    }

    public void show() {
        while (true) {
            System.out.println("\n" + "=".repeat(30));
            System.out.println("  BANK MANAGEMENT SYSTEM");
            System.out.println("=".repeat(30));
            System.out.println("1. Bank");
            System.out.println("2. Employees");
            System.out.println("3. Customers");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

////            switch (choice) {
////                case "1" -> new BankMenu(
////                        bankService, scanner).show();
////                case "2" -> new EmployeeMenu(
////                        employeeService, scanner).show();
////                case "3" -> new CustomerMenu(
////                        customerService, scanner).show();
////                case "0" -> {
////                    System.out.println("Goodbye!");
////                    return;
////                }
//                default -> System.out.println(
//                        "Invalid choice!");
//            }
        }
    }
}