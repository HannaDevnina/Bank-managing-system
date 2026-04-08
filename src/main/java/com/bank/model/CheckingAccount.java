package com.bank.model;

import com.bank.enums.AccountStatus;
import com.bank.enums.TransactionType;
import com.bank.exceptions.AccountClosedException;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.TransactionLimitExceededException;

public class CheckingAccount extends Account {
    private double overdraftLimit;
    private double monthlyFee;

    public CheckingAccount(String accountId,
                           double balance,
                           double transactionLimit,
                           double overdraftLimit,
                           double monthlyFee) {
        super(accountId, balance, transactionLimit);
        setOverdraftLimit(overdraftLimit);
        setMonthlyFee(monthlyFee);
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException ("Overdraft shouldn't be negative number!");
        }
        this.overdraftLimit = overdraftLimit;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        if (monthlyFee < 0) {
            throw new IllegalArgumentException("Monthly fee shouldn't be negative number!");
        }
        this.monthlyFee = monthlyFee;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "overdraftLimit=" + overdraftLimit +
                ", monthlyFee=" + monthlyFee +
                "} " + super.toString();
    }

    @Override
    public double calculateInterest() {
        return  0;
    }

    @Override
    public double getAvailableBalance() {
        return getBalance() + overdraftLimit;
    }

    @Override
    public void withdraw(String transactionId, double amount) {
        if (getStatus() != AccountStatus.ACTIVE) {
            throw new AccountClosedException("Account is not active!");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount for withdrawal should be positive number!");
        }

        if (amount > getTransactionLimit()) {
            throw new IllegalArgumentException("Amount exceeds transaction limit! Transaction limit is " + getTransactionLimit());
        }

        if (getBalance() + overdraftLimit < amount) {
            throw new InsufficientFundsException("Insufficient funds. Available: " + getAvailableBalance());
        }

        setBalance(getBalance() - amount);

        Transaction transaction = new Transaction(
                transactionId,
                amount,
                getBalance(),
                TransactionType.WITHDRAWAL,
                getAccountId()
        );

        addTransaction(transaction);
    }

    @Override
    public void transfer(String transactionId, Account destination, double amount) {
        if (getStatus() != AccountStatus.ACTIVE) {
            throw new AccountClosedException("Account is not active!");
        }

        if (destination.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountClosedException("Destination account is not active!");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount for transaction should be positive number!");
        }

        if (amount > getTransactionLimit()) {
            throw new TransactionLimitExceededException("Amount exceeds transaction limit! Transaction limit is " + getTransactionLimit());
        }

        if (getBalance() + overdraftLimit < amount) {
            throw new InsufficientFundsException("Insufficient funds. Available balance: " + getAvailableBalance());
        }

        setBalance(getBalance() - amount);
        destination.setBalance(destination.getBalance() + amount);

        Transaction transaction = new Transaction(
                transactionId,
                amount,
                getBalance(),
                TransactionType.TRANSFER,
                getAccountId()
        );
        addTransaction(transaction);

        destination.addTransaction(new Transaction(
                transactionId,
                amount,
                destination.getBalance(),
                TransactionType.DEPOSIT, //I don't know since money put on the account
                destination.getAccountId()
        ));
    }
}

