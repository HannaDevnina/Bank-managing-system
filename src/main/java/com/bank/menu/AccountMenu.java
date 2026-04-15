package com.bank.menu;

import com.bank.enums.AccountStatus;
import com.bank.exceptions.AccountClosedException;
import com.bank.exceptions.AccountNotFoundException;
import com.bank.exceptions.CustomerNotFoundException;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.service.CustomerService;

import java.util.Scanner;

public class AccountMenu extends BaseMenu{
    private CustomerService customerService;

    public AccountMenu(CustomerService customerService, Scanner scanner) {
        super(scanner);
        this.customerService = customerService;
    }

    public void show() {
        while (true) {

            System.out.println("\n" + "=".repeat(30));
            System.out.println("  ACCOUNTS MENU");
            System.out.println("=".repeat(30));
            System.out.println("1. Open Checking Account");
            System.out.println("2. Open Savings Account");
            System.out.println("3. Open Fixed Deposit Account");
            System.out.println("4. Show All Customer Accounts");
            System.out.println("5. Show All Accounts");
            System.out.println("6. Close Account");
            System.out.println("7. Freeze Account");
            System.out.println("8. Change Account Status");
            System.out.println("9. Show Account Balance");
            System.out.println("10. Show Account Transactions");
            System.out.println("11. Show All Transactions");
            System.out.println("12. Deposit Money");
            System.out.println("13. Withdraw Money");
            System.out.println("14. Transfer Money");
            System.out.println("0. Back to Customer Menu");
            System.out.println("=".repeat(30));
            System.out.print("Choose: ");

            String choice = scanner.nextLine().strip();
            System.out.println();

            switch (choice) {
                case "1":
                    try {
                        System.out.print("Enter Customer ID: ");
                        String customerId = scanner.nextLine().strip();
                        System.out.print("Enter Deposit Amount (Balance): ");
                        double balance = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Transaction Limit: ");
                        double transactionLimit = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Overdraft Limit: ");
                        double overdraftLimit = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Monthly Fee: ");
                        double monthlyFee = scanner.nextDouble();
                        scanner.nextLine();
                        customerService.openCheckingAccount(customerId, balance,
                                transactionLimit, overdraftLimit, monthlyFee);
                        System.out.println("Checking Account Created Successfully!");
                        System.out.println();
                        customerService.showAllCustomerAccounts(customerId);
                    } catch (IllegalArgumentException | CustomerNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "2":
                    try {
                        System.out.print("Enter Customer ID: ");
                        String customerId = scanner.nextLine().strip();
                        System.out.print("Enter Deposit Amount (Balance): ");
                        double balance = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Transaction Limit: ");
                        double transactionLimit = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Interest Rate: ");
                        double interestRate = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Min Balance: ");
                        double minBalance = scanner.nextDouble();
                        scanner.nextLine();
                        customerService.openSavingsAccount(customerId, balance,
                                transactionLimit, interestRate, minBalance);
                        System.out.println("Savings Account Created Successfully!");
                        System.out.println();
                        customerService.showAllCustomerAccounts(customerId);
                    } catch (IllegalArgumentException | CustomerNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "3":
                    try {
                        System.out.print("Enter Customer ID: ");
                        String customerId = scanner.nextLine().strip();
                        System.out.print("Enter Deposit Amount (Balance): ");
                        double balance = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Transaction Limit: ");
                        double transactionLimit = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Interest Rate: ");
                        double interestRate = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Lock Period (Months): ");
                        int lockPeriodMonths = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Penalty Rate: ");
                        double penaltyRate = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Maturity Date (YYYY-MM-DD): ");
                        String maturityDate = scanner.nextLine().strip();
                        customerService.openFixedDepositAccount(customerId, balance,
                                transactionLimit, interestRate, lockPeriodMonths,
                                penaltyRate, maturityDate);
                        System.out.println("Fixed Deposit Account Created Successfully!");
                        System.out.println();
                        customerService.showAllCustomerAccounts(customerId);
                    } catch (IllegalArgumentException | CustomerNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "4":
                    try {
                        System.out.print("Enter Customer ID: ");
                        String customerId = scanner.nextLine().strip();
                        customerService.showAllCustomerAccounts(customerId);
                    } catch (CustomerNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "5":
                    customerService.showAllAccounts();
                    pause();
                    break;
                case "6":
                    try {
                        System.out.print("Enter Account ID: ");
                        String accountId = scanner.nextLine().strip();
                        customerService.closeAccount(accountId);
                        System.out.println("Account Closed Successfully!");
                        System.out.println();
                        customerService.showAccountById(accountId);
                    } catch (AccountNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "7":
                    try {
                        System.out.print("Enter Account ID: ");
                        String accountId = scanner.nextLine().strip();
                        customerService.freezeAccount(accountId);
                        System.out.println("Account Frozen Successfully!");
                        System.out.println();
                        customerService.showAccountById(accountId);
                    } catch (AccountNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "8":
                    try {
                        System.out.print("Enter Account ID: ");
                        String accountId = scanner.nextLine().strip();
                        System.out.print("Enter Account New Status: ");
                        AccountStatus status = AccountStatus.valueOf(scanner.nextLine().strip());
                        customerService.changeAccountStatus(accountId, status);
                        System.out.println("Account Status Changed Successfully!");
                        System.out.println();
                        customerService.showAccountById(accountId);
                    } catch (AccountNotFoundException | IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "9":
                    try {
                        System.out.print("Enter Account ID: ");
                        String accountId = scanner.nextLine().strip();
                        customerService.showAccountBalance(accountId);
                    } catch (AccountNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "10":
                    try {
                        System.out.print("Enter Account ID: ");
                        String accountId = scanner.nextLine().strip();
                        customerService.showAllAccountTransactions(accountId);
                    } catch (AccountNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "11" :
                    customerService.showAllTransaction();
                    pause();
                    break;
                case "12":
                    try {
                        System.out.print("Enter Account ID: ");
                        String accountId = scanner.nextLine().strip();
                        System.out.print("Enter Amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        customerService.executeDeposit(accountId, amount);
                        System.out.println("Money Deposited Successfully!");
                        System.out.println();
                        customerService.showAllAccountTransactions(accountId);
                        System.out.println();
                        customerService.showAccountById(accountId);
                    } catch (AccountNotFoundException |
                             InsufficientFundsException |
                             AccountClosedException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "13":
                    try {
                        System.out.print("Enter Account ID: ");
                        String accountId = scanner.nextLine().strip();
                        System.out.print("Enter Amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        customerService.executeWithdraw(accountId, amount);
                        System.out.println("Money Withdraw Successfully!");
                        System.out.println();
                        customerService.showAllAccountTransactions(accountId);
                        System.out.println();
                        customerService.showAccountById(accountId);

                    } catch (AccountNotFoundException |
                             InsufficientFundsException |
                             AccountClosedException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "14":
                    try {
                        System.out.print("Enter Account ID Sent From: ");
                        String fromAccount = scanner.nextLine().strip();
                        System.out.print("Enter Account ID Sent To: ");
                        String toAccount = scanner.nextLine().strip();
                        System.out.print("Enter Amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        customerService.executeTransfer(fromAccount, toAccount, amount);
                        System.out.println("Money Transfer Successfully!");
                        System.out.println();
                        customerService.showAllAccountTransactions(fromAccount);
                        System.out.println();
                        customerService.showAllAccountTransactions(toAccount);
                    } catch (AccountNotFoundException |
                             InsufficientFundsException |
                             AccountClosedException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pause();
                    break;
                case "0": {
                    System.out.println("Returning to Customer Menu...");
                    return;
                }
                default: {
                    System.out.println("Invalid choice!");
                    break;
                }
            }
        }
    }
}