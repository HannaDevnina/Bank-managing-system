package com.bank.model;

import com.bank.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private double amount;
    private double balanceAfter; //
    private String dateTime;
    private TransactionType type; //
    private String accountId; //

    public Transaction(String transactionId,
                       double amount,
                       double balanceAfter,
                       TransactionType type,
                       String accountId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.dateTime = String.valueOf(LocalDateTime.now());
        this.type = type;
        this.accountId = accountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public String getDateTime() {
        return dateTime;
    }

    public TransactionType getType() {
        return type;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return String.format(
                "%-10s | %-12s | %10.2f | %10.2f | %s",
                transactionId,
                type,
                amount,
                balanceAfter,
                dateTime
        );
    }


}
