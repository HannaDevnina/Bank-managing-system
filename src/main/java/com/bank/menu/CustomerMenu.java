package com.bank.menu;

import com.bank.enums.BusinessType;
import com.bank.enums.MaritalStatus;
import com.bank.exceptions.CustomerNotFoundException;
import com.bank.service.CustomerService;

import java.util.Scanner;

public class CustomerMenu extends BaseMenu{
    private CustomerService customerService;

    public CustomerMenu(CustomerService customerService, Scanner scanner) {
        super(scanner);
        this.customerService = customerService;
    }

    public void show() {
        while (true) {
            System.out.println("\n" + "=".repeat(30));
            System.out.println("  CUSTOMER MENU");
            System.out.println("=".repeat(30));
            System.out.println("1. Add Individual Customer");
            System.out.println("2. Add Business Customer");
            System.out.println("3. Show All Customers");
            System.out.println("4. Search Customer by ID");
            System.out.println("5. Search Customer By Full Name" );
            System.out.println("6. Update Customer First Name" );
            System.out.println("7. Update Customer Last Name" );
            System.out.println("8. Update Customer Email" );
            System.out.println("9. Update Customer Phone Number" );
            System.out.println("10. Deactivate Customer" );
            System.out.println("11. Get Customer Info" );
            System.out.println("12. Manage Accounts" );
            System.out.println("0. Back to Main Menu");
            System.out.println("=".repeat(30));
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1" :
                    try {
                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine().strip();
                        System.out.print("Enter Last Name: ");
                        String lastName = scanner.nextLine().strip();
                        System.out.print("Enter Date Of Birth (YYYY-MM-DD): ");
                        String birthDate = scanner.nextLine().strip();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine().strip();
                        System.out.print("Enter Phone Number: ");
                        String phone = scanner.nextLine().strip();
                        System.out.print("Enter Customer SSN (XXX-XX-XXXX): ");
                        String ssn = scanner.nextLine().strip();
                        System.out.print("Enter Customer Occupation: ");
                        String occupation = scanner.nextLine().strip();
                        System.out.print("Enter Marital Status (SINGLE/MARRIED/DIVORCED/WIDOWED): ");
                        MaritalStatus maritalStatus = MaritalStatus.valueOf(scanner.nextLine().strip().toUpperCase());
                        customerService.addIndividualCustomer(firstName, lastName, birthDate, email,
                                phone, ssn, occupation, maritalStatus);
                        System.out.println();
                        System.out.println("Individual Customer Created Successfully!");
                        System.out.println();
                        customerService.showCustomerByFullName(firstName, lastName);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "2" :
                    try {
                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine().strip();
                        System.out.print("Enter Last Name: ");
                        String lastName = scanner.nextLine().strip();
                        System.out.print("Enter Date Of Birth (YYYY-MM-DD): ");
                        String birthDate = scanner.nextLine().strip();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine().strip();
                        System.out.print("Enter Phone Number: ");
                        String phone = scanner.nextLine().strip();
                        System.out.print("Enter Company Name: ");
                        String companyName = scanner.nextLine().strip();
                        System.out.print("Enter Tax ID (XX-XXXXXXX): ");
                        String taxId = scanner.nextLine().strip();
                        System.out.print("Enter Business Type: ");
                        BusinessType businessType = BusinessType.valueOf(scanner.nextLine().strip().toUpperCase());
                        System.out.print("Enter Contact Person: ");
                        String contactPerson = scanner.nextLine().strip();
                        customerService.addBusinessCustomer(firstName, lastName, birthDate, email,
                                phone, companyName, taxId, businessType, contactPerson);
                        System.out.println();
                        System.out.println("Business Customer Created Successfully!");
                        System.out.println();
                        customerService.showCustomerByFullName(firstName, lastName);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "3" :
                    customerService.showAllCustomers();
                    pause();
                    break;
                case "4" :
                    try {
                        System.out.print("Enter Customer ID: ");
                        String customerId = scanner.nextLine().strip();
                        customerService.showCustomer(customerId);
                    } catch (CustomerNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "5" :
                    try {
                        System.out.print("Enter Customer First Name: ");
                        String customerFirstName = scanner.nextLine().strip();
                        System.out.print("Enter Customer Last Name: ");
                        String customerLastName = scanner.nextLine().strip();
                        customerService.showCustomerByFullName(customerFirstName, customerLastName);
                    } catch (CustomerNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "6" :
                    try {
                        System.out.print("Enter Customer Id: ");
                        String customerId = scanner.nextLine().strip();
                        System.out.print("Enter Customer New First Name: ");
                        String newName = scanner.nextLine().strip();
                        customerService.updateCustomerFirstName(customerId, newName);
                        System.out.println();
                        System.out.println("Customer First Name Updated Successfully!");
                        System.out.println();
                        customerService.showCustomer(customerId);
                    } catch (IllegalArgumentException | CustomerNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "7" :
                    try {
                        System.out.print("Enter Customer Id: ");
                        String customerId = scanner.nextLine().strip();
                        System.out.print("Enter Customer New Last Name: ");
                        String newLastName = scanner.nextLine().strip();
                        customerService.updateCustomerLastName(customerId, newLastName);
                        System.out.println();
                        System.out.println("Customer Last Name Updated Successfully!");
                        System.out.println();
                        customerService.showCustomer(customerId);
                    } catch (IllegalArgumentException | CustomerNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "8" :
                    try {
                        System.out.print("Enter Customer Id: ");
                        String customerId = scanner.nextLine().strip();
                        System.out.print("Enter Customer New Email: ");
                        String newEmail = scanner.nextLine().strip();
                        customerService.updateEmail(customerId, newEmail);
                        System.out.println();
                        System.out.println("Customer Email Updated Successfully!");
                        System.out.println();
                        customerService.showCustomer(customerId);
                    } catch (IllegalArgumentException | CustomerNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "9" :
                    try {
                        System.out.print("Enter Customer Id: ");
                        String customerId = scanner.nextLine().strip();
                        System.out.print("Enter Customer New Phone Number: ");
                        String newPhone = scanner.nextLine().strip();
                        customerService.updatePhone(customerId, newPhone);
                        System.out.println();
                        System.out.println("Customer Phone Number Updated Successfully!");
                        System.out.println();
                        customerService.showCustomer(customerId);
                    } catch (IllegalArgumentException | CustomerNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "10" :
                    try {
                        System.out.print("Enter Customer Id: ");
                        String customerId = scanner.nextLine().strip();
                        customerService.deactivateCustomer(customerId);
                        System.out.println();
                        System.out.println("Customer Deactivated Successfully!");
                        System.out.println();
                        customerService.showCustomer(customerId);
                    } catch (CustomerNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "11" :
                    try {
                        System.out.print("Enter Customer Id: ");
                        String customerId = scanner.nextLine().strip();
                        customerService.getCustomerInfo(customerId);
                    } catch (CustomerNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    pause();
                    break;
                case "12" : {
                    AccountMenu accountMenu = new AccountMenu(customerService, scanner);
                    accountMenu.show();
                    break;
                }
                case "0" : {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default : {
                    System.out.println("Invalid choice!");
                }
            }
        }
    }
}
