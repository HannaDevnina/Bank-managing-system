package com.bank.model;

import com.bank.exceptions.AccountNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Customer extends Person {
    private String customerId;
    private String registrationDate;
    private String closureDate;
    private List<Account> accounts = new ArrayList<>();


    protected Customer(String firstName, String lastName, String birthDate,
                    String email, String phone, String customerId) {
        super(firstName, lastName, birthDate, email, phone);
        this.customerId = customerId;
        this.registrationDate = LocalDate.now().toString();
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setClosureDate(String closureDate) {
        this.closureDate = closureDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getClosureDate() {
        return closureDate;
    }

    public void setClosureDate() {
        this.closureDate = LocalDate.now().toString();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", closureDate='" + closureDate + '\'' +
                ", accounts=" + accounts +
                "} " + super.toString();
    }

    abstract String getCustomerInfo();

    public Account findAccount(String accountId) {
        for (Account account: accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account not found: " + accountId);
    }

    public void showAccounts() {
        accounts.forEach(account -> System.out.println(account.toString()));
    }

    public void deactivateCustomer() {
        setActive(false);
        setClosureDate();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

}
