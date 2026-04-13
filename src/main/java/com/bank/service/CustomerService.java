package com.bank.service;

import com.bank.enums.AccountStatus;
import com.bank.enums.BusinessType;
import com.bank.enums.MaritalStatus;
import com.bank.exceptions.AccountNotFoundException;
import com.bank.exceptions.CustomerNotFoundException;
import com.bank.model.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();
    private FileService fileService;
    private IdService idService;

    public CustomerService(FileService fileService, IdService idService) {
        this.fileService = fileService;
        this.idService = idService;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addIndividualCustomer(
            String firstName,
            String lastName,
            String birthDate,
            String email,
            String phone,
            String ssn,
            String occupation,
            MaritalStatus maritalStatus) {
        String customerId = idService.generateCustomerId();
        IndividualCustomer individualCustomer = new IndividualCustomer(
                firstName,
                lastName,
                birthDate,
                email,
                phone,
                customerId,
                ssn,
                occupation,
                maritalStatus
        );
        customers.add(individualCustomer);
        fileService.saveCustomers(customers);
    }

    public void addBusinessCustomer(
            String firstName,
            String lastName,
            String birthDate,
            String email,
            String phone,
            String companyName,
            String taxId,
            BusinessType businessType,
            String contactPerson) {
        String customerId = idService.generateCustomerId();
        BusinessCustomer businessCustomer = new BusinessCustomer(
                firstName,
                lastName,
                birthDate,
                email,
                phone,
                customerId,
                companyName,
                taxId,
                businessType,
                contactPerson
        );
        customers.add(businessCustomer);
        fileService.saveCustomers(customers);
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer not found");
    }

    public List<Customer> findCustomerByFullName(String firstName, String lastname) {
        List<Customer> found = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(firstName)
                    && customer.getLastName().equals(lastname)) {
                found.add(customer);
            }
        }
        return  found;
    }

    public void showCustomerByFullName(String firstName, String lastName) {
        List<Customer> found = findCustomerByFullName(firstName, lastName);
        if (found.isEmpty()) {
            throw new CustomerNotFoundException(
                    "Customer not found");
        }
        System.out.println(showHeader());
        found.forEach(System.out::println);
    }

    public void updateCustomerFirstName(String customerId, String newFirstName) {
        Customer customer = findCustomerById(customerId);
        customer.setFirstName(newFirstName);
        fileService.saveCustomers(customers);
    }

    public void updateCustomerLastName(String customerId, String newLastName) {
        Customer customer = findCustomerById(customerId);
        customer.setLastName(newLastName);
        fileService.saveCustomers(customers);
    }

    public void updateEmail(String customerId, String newEmail) {
        Customer customer = findCustomerById(customerId);
        customer.setEmail(newEmail);
        fileService.saveCustomers(customers);
    }

    public void updatePhone(String customerId, String newPhone) {
        Customer customer = findCustomerById(customerId);
        customer.setPhone(newPhone);
        fileService.saveCustomers(customers);
    }

    public void openSavingsAccount(
            String customerId,
            double balance,
            double transactionLimit,
            double interestRate,
            double minBalance) {
        String accountId = idService.generateAccountId();
        SavingsAccount savingsAccount = new SavingsAccount(
                accountId,
                balance,
                transactionLimit,
                interestRate,
                minBalance);
        Customer customer = findCustomerById(customerId);
        customer.addAccount(savingsAccount);
        fileService.saveAccounts(customers);
    }

    public void openCheckingAccount(
            String customerId,
            double balance,
            double transactionLimit,
            double overdraftLimit,
            double monthlyFee) {
        String accountId = idService.generateAccountId();
        CheckingAccount checkingAccount = new CheckingAccount(
                accountId,
                balance,
                transactionLimit,
                overdraftLimit,
                monthlyFee);
        Customer customer = findCustomerById(customerId);
        customer.addAccount(checkingAccount);
        fileService.saveAccounts(customers);
    }

    public void openFixedDepositAccount(
            String customerId,
            double balance,
            double transactionLimit,
            double interestRate,
            int lockPeriodMonths,
            double penaltyRate,
            String maturityDate) {
    String accountId = idService.generateAccountId();
    FixedDepositAccount fixedDepositAccount = new FixedDepositAccount(
            accountId,
            balance,
            transactionLimit,
            interestRate,
            lockPeriodMonths,
            penaltyRate,
            maturityDate);
    Customer customer = findCustomerById(customerId);
    customer.addAccount(fixedDepositAccount);
    fileService.saveAccounts(customers);
    }

    public void showAllCustomers() {
        System.out.println(showHeader());
        customers.forEach(System.out::println);
    }

    public void showCustomer(String customerId) {
        System.out.println(showHeader());
        Customer customer = findCustomerById(customerId);
        System.out.println(customer);
    }

    public void deactivateCustomer(String customerId) {
        Customer customer = findCustomerById(customerId);
        customer.setActive(false);
        fileService.saveCustomers(customers);
    }

    public void getCustomerInfo(String customerId) {
        Customer customer = findCustomerById(customerId);
        System.out.println(customer.getCustomerInfo());
    }

    public void closeAccount(String accountId) {
        Account account = findAccountByAccountId(accountId);
        account.closeAccount();
        fileService.saveAccounts(customers);
    }

    public Account findAccountByAccountId(String accountId) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountId().equals(accountId)) {
                    return account;
                }
            }
        }
        throw new AccountNotFoundException("Account not found");
    }

    public void showAccountById(String accountId) {
        System.out.println(showAccountHeader());
        System.out.println(findAccountByAccountId(accountId));
    }

    public void showAllAccounts() {
        System.out.println(showAccountHeader());
        for (Customer customer : customers) {
            customer.getAccounts().forEach(System.out::println);
        }
    }

    public void freezeAccount(String accountId) {
        Account account = findAccountByAccountId(accountId);
        account.setStatus(AccountStatus.FROZEN);
        fileService.saveAccounts(customers);
    }

    public void changeAccountStatus(String accountId, AccountStatus status) {
        Account account = findAccountByAccountId(accountId);
        account.setStatus(status);
        fileService.saveAccounts(customers);
    }

    public double getAccountBalance(String accountId) {
        Account account = findAccountByAccountId(accountId);
        return account.getBalance();
    }

    public void showAccountBalance(String accountId) {
        System.out.println(getAccountBalance(accountId));
    }

    public void showAllAccountTransactions(String accountId) {
        System.out.println(showTransactionHeader());
        Account account = findAccountByAccountId(accountId);
        account.getTransactions().forEach(System.out::println);
    }

    public void executeDeposit(String accountId, double amount ) {
        String transactionId = idService.generateTransactionId();
        Account account = findAccountByAccountId(accountId);
        account.deposit(transactionId, amount);
        fileService.saveAccounts(customers);
        fileService.saveTransactions(customers);
    }

    public void executeWithdraw(String accountId, double amount) {
        Account account = findAccountByAccountId(accountId);
        String transactionId =  idService.generateTransactionId();
        account.withdraw(transactionId,amount);
        fileService.saveAccounts(customers);
        fileService.saveTransactions(customers);
    }

    public void executeTransfer(String fromAccountId, String toAccountId, double amount) {
        Account fromAccount = findAccountByAccountId(fromAccountId);
        Account toAccount = findAccountByAccountId(toAccountId);
        String transactionId =  idService.generateTransactionId();
        fromAccount.transfer(transactionId, toAccount, amount);
        fileService.saveAccounts(customers);
        fileService.saveTransactions(customers);
    }

    public void saveCustomers() {
        fileService.saveCustomers(customers);
    }

    public void loadCustomers() {
        this.customers = fileService.readCustomers();
    }

    public void showAllCustomerAccounts(String customerId) {
        Customer customer = findCustomerById(customerId);
        System.out.println(showAccountHeader());
        customer.showAccounts();
        }

    public String showHeader() {
        return String.format("%-12s|%-20s|%-11s|%-20s|%-12s|%-11s|%-13s|%-13s|%-12s|%-14s|%-20s|%-11s|%-6s ",
                "CustomerID", "FullName", "DOB", "Email", "Phone", "RegDate","SSN",
                "ComName", "TaxID", "BusinessType", "ContactP", "EndDate", "IsActive") + "\n"
                + "_".repeat(190);
    }

    public String showAccountHeader() {
        return String.format("%-12s|%-12s|%-15s|%-10s|%-10s|%-10s|%-12s|%-12s|%-12s|%-12s|%-11s|%-11s|%-10s",
                "AccountID", "Balance", "TransLimit", "IntRate", "MBalance", "ODLimit",
                "MonthlyFee", "LockPeriod", "PenaltyRate", "MaturityDate", "OpenDate",
                "ClosureDate", "Status") + "\n" + "_".repeat(170);
    }

    public String showTransactionHeader() {
        return String.format("%-10s |%-12s|%-10s|%-15s|%-15s",
                "TransferId", "DType", "Amount", "BalanceAfter", "DateTime")
                + "\n" + "_".repeat(100);
    }

}





