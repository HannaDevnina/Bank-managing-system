package com.bank.exceptions;

public class AccountClosedException extends RuntimeException {
    public AccountClosedException(String message) {
        super(message);
    }
}
