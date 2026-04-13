package com.bank.model;

import com.bank.enums.AccountStatus;
import com.bank.exceptions.AccountClosedException;
import com.bank.enums.TransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private String accountId;
    private String openDate;
    private String closureDate;
    private double balance;
    private AccountStatus status = AccountStatus.ACTIVE;
    private List<Transaction> transactions = new ArrayList<>();
    private double transactionLimit;


    protected Account(String accountId, double balance, double transactionLimit) {
        this.accountId = accountId;
        this.openDate = LocalDate.now().toString();
        if (balance < 0) {
            throw new IllegalArgumentException("Balance should be positive!");
        }
        this.balance = balance;
        setTransactionLimit(transactionLimit);
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public void setClosureDate(String closureDate){
        this.closureDate = closureDate;
    }

    public String getAccountId() {
        return accountId;
    }


    public String getOpenDate() {
        return openDate;
    }


    public String getClosureDate() {
         return closureDate;
    }

    public void setClosureDate() {
        this.closureDate = LocalDate.now().toString();
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(double transactionLimit) {
        if (transactionLimit <= 0) {
            throw new IllegalArgumentException("Limit should be positive number!");
        }
        this.transactionLimit = transactionLimit;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", openDate='" + openDate + '\'' +
                ", closureDate='" + closureDate + '\'' +
                ", balance=" + balance +
                ", status=" + status +
                ", transactions=" + transactions +
                ", transactionLimit=" + transactionLimit +
                '}';
    }

    public abstract double calculateInterest();

    public abstract double getAvailableBalance();

    public abstract void withdraw(String transactionId, double amount);

    public abstract void transfer(String transactionId, Account destination, double amount);

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void deposit(String transactionId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount for deposit should be positive number!");
        }
        if (status != AccountStatus.ACTIVE) {
            throw new AccountClosedException("Account is not active!");
        }

        balance += amount;

        Transaction transaction = new Transaction(
                transactionId,
                amount,
                balance,
                TransactionType.DEPOSIT,
                accountId
        );
        addTransaction(transaction);
    }

    public void printTransactions() {
        transactions.forEach(transaction -> System.out.println(transaction));
    }

    public void changeAccountStatus(AccountStatus status) {
        setStatus(status);
    }

    public void closeAccount() {
        setStatus(AccountStatus.CLOSED);
        setClosureDate();
    }
}
