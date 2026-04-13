package com.bank.model;

import com.bank.enums.AccountStatus;
import com.bank.enums.TransactionType;
import com.bank.exceptions.AccountClosedException;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.TransactionLimitExceededException;

public class SavingsAccount extends Account{
    private double interestRate;
    private  double minBalance;

    public SavingsAccount(String accountId, double balance, double transactionLimit,
                          double interestRate, double minBalance) {
        super(accountId, balance, transactionLimit);
        setInterestRate(interestRate);
        setMinBalance(minBalance);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate shouldn't be negative!");
        }
        this.interestRate = interestRate;
    }


    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        if (minBalance < 0) {
            throw new IllegalArgumentException("Minimal balance should be positive number!");
        }
        this.minBalance = minBalance;
    }

    @Override
    public String toString() {
        return String.format("%-12s|%-12s|%-15s|%-10s|%-10s|%-10s|%-12s|%-12s|%-12s|%-12s|%-11s|%-11s|%-10s",
                getAccountId(), getBalance(), getTransactionLimit(), getInterestRate(), getMinBalance(), " ",
                " ", " ", " ", " ", getOpenDate(), getClosureDate(), getStatus());
    }

    @Override
    public double calculateInterest() {
        return getBalance() * (interestRate / 12);
    }

    @Override
    public double getAvailableBalance() {
        return getBalance() - minBalance;
    }

    @Override
    public void withdraw(String transactionId, double amount) {
        if (getStatus() != AccountStatus.ACTIVE) {
            throw new AccountClosedException("Account is not active!");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal should be positive number!");
        }

        if (amount > getTransactionLimit()) {
            throw new TransactionLimitExceededException("You exceeded transaction limit! " +
                    "Transaction limit is " + getTransactionLimit());
        }

        if (getBalance() - amount < minBalance) {
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
            throw new AccountClosedException(
                    "Destination account is not active!");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount for transaction should be positive number!");
        }

        if (amount > getTransactionLimit()) {
            throw new TransactionLimitExceededException("You exceeded transaction limit!");
        }

        if (getBalance() - amount < minBalance) {
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
                TransactionType.TRANSFER,
                destination.getAccountId()
        ));
    }
}



