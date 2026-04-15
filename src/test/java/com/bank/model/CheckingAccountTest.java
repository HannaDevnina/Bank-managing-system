package com.bank.model;

import com.bank.enums.AccountStatus;
import com.bank.exceptions.AccountClosedException;
import com.bank.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {
    CheckingAccount account;

    @BeforeEach
    void setUp() {
        account = new CheckingAccount("ACC-000020", 3000,
                1000, 500, 20);
    }

    @Test
    void shouldThrowWhenOverdraftLimitIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new CheckingAccount("ACC-000020", 3000,
                        1000, -500, 20));
    }


    @Test
    void shouldTrowWhenMonthlyFeeIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new CheckingAccount("ACC-000020", 3000,
                        1000, 500, -20));
    }

    @Test
    void shouldCalculateAvailableBalanceCorrectly() {
       assertEquals(3500, account.getAvailableBalance());
    }

    @Test
    void shouldDecreaseBalanceWhenWithdraw() {
    account.withdraw("TRX-000001", 500);
    assertEquals(2500, account.getBalance());
    }

    @Test
    void shouldThrowWhenWithdrawFromClosedAccount() {
        account.setStatus(AccountStatus.CLOSED);
        assertThrows(AccountClosedException.class,
                () -> account.withdraw("TRX-000001", 500));
    }

    @Test
    void shouldThrowWhenWithdrawNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw("TRX-000001", -500));
    }

    @Test
    void shouldThrowWhenWithdrawZero() {
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw("TRX-000001", 0));
    }

    @Test
    void shouldThrowWhenWithdrawExceedTransactionLimit() {
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw("TRX-000001", 2000));
    }

    @Test
    void shouldThrowWhenAvailableBalanceViolated() {
        account = new CheckingAccount("ACC-000020", 1000,
                2000, 500, 20);
        assertThrows(InsufficientFundsException.class,
                () -> account.withdraw("TRX-000001", 1800));
    }

    @Test
    void shouldCreateTransactionWhenWithdraw() {
        account.withdraw("TRX-000001", 800);
        assertEquals(1, account.getTransactions().size());
    }

    @Test
    void transfer() {
    }
}