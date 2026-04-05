package com.bank.model;

import com.bank.enums.AccountStatus;
import com.bank.exceptions.AccountClosedException;
import com.bank.enums.TransactionType;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.TransactionLimitExceededException;

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
        this.accountId = accountId; // then need to use Generator ID
        this.openDate = LocalDate.now().toString();
        setBalance(balance);
        setTransactionLimit(transactionLimit);
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

    //Do I need this method for setBalance it will be set once when we create it
    protected void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance should be positive!");// make sense when it creates new account
        }
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

    public abstract void withdraw(double amount);

    public abstract void transfer(Account destination, double amount);

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount for deposit should be positive number!");
        }
        if (status != AccountStatus.ACTIVE) {
            throw new AccountClosedException("Account is not active!");
        }
        if (amount > transactionLimit) {
            throw new IllegalArgumentException("Amount exceeds transaction limit!");
        }
        balance += amount;

        Transaction transaction = new Transaction(
                "TRX - TEMP",
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
