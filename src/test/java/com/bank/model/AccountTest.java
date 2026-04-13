package com.bank.model;

import com.bank.enums.AccountStatus;
import com.bank.exceptions.AccountClosedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    SavingsAccount account;

    @BeforeEach
    void setUp() {
        account = new SavingsAccount("ACC-001", 1000.0,
                500.0, 0.05, 100.0);
    }

    @Test
    void shouldThrowWhenBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingsAccount("ACC-001", -1000.0,
                        500.0, 0.05, 100.0));
    }

    @Test
    void shouldHaveZeroBalance() {
        account = new SavingsAccount("ACC-001", 0.0,
                500.0, 0.05, 100.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void shouldThrowWhenTransactionLimitIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingsAccount("ACC-001", 0.0,
                        -500.0, 0.05, 100.0));
    }

    @Test
    void shouldThrowWhenTransactionLimitIsZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingsAccount("ACC-001", 0.0,
                        0.0, 0.05, 100.0));
    }

    @Test
    void shouldIncreasesBalanceWhenDeposit() {
        account.deposit("TRX-001", 500.0);
        assertEquals(1500.00, account.getBalance());
    }

    @Test
    void shouldThrowWhenDepositIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit("TRX-001", -300));
    }

    @Test
    void shouldThrowWhenDepositZero() {
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit("TRX-001", 0.0));
    }

    @Test
    void shouldThrowWhenDepositToClosedAccount() {
        account.setStatus(AccountStatus.CLOSED);
        assertThrows(AccountClosedException.class,
                () -> account.deposit("TRX-001", 500.0));
    }

    @Test
    void shouldCreateTransactionWhenDeposit() {
        account.deposit("TRX-001", 500);
        assertEquals(1, account.getTransactions().size());
    }

}