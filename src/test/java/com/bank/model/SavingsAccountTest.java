package com.bank.model;

import com.bank.enums.AccountStatus;
import com.bank.exceptions.AccountClosedException;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.TransactionLimitExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {
    SavingsAccount account;

    @BeforeEach
    void setUp() {
        account = new SavingsAccount("ACC-000003", 2500.0, 1000,
                0.12, 500);
    }

    @Test
    void shouldThrowWhenMinBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingsAccount("ACC-000003", 2500.0, 1000,
                        0.08, -500));
    }

    @Test
    void shouldNotThrowWhenMinBalanceIsZero() {
        assertDoesNotThrow(
                () -> new SavingsAccount("ACC-000003", 2500.0, 1000,
                        0.08, 0));

    }

    @Test
    void shouldThrowWhenInterestRateIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingsAccount("ACC-000003", 2500.0, 1000,
                        -0.08, 500));
    }

    @Test
    void shouldNotThrowWhenInterestRateIsZero() {
        assertDoesNotThrow(
                () -> new SavingsAccount("ACC-000003", 2500.0, 1000,
                        0.0, 500));
    }

    @Test
    void shouldCalculateInterestCorrectly() {
        double interest = account.calculateInterest();
        assertEquals(25, interest);
    }

    @Test
    void shouldCalculateAvailableBalanceCorrectly() {
        double availableBalance = account.getAvailableBalance();
        assertEquals(2000, availableBalance);
    }

    @Test
    void shouldDecreaseBalanceWhenWithdrawLessLimit() {
        account.withdraw("TRX-001", 500);
        assertEquals(2000, account.getBalance());
    }

    @Test
    void shouldThrowWhenWithdrawAboveLimit() {
        assertThrows(TransactionLimitExceededException.class,
                () -> account.withdraw("TRX-001", 1500));
    }

    @Test
    void shouldNotThrowWhenWithdrawEqualLimit() {
        assertDoesNotThrow(
                () -> account.withdraw("TRX-001", 1000));
    }

    @Test
    void shouldThrowWhenWithdrawZero() {
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw("TRX-001", 0));
    }

    @Test
    void shouldThrowWhenWithdrawFromClosed() {
        account.setStatus(AccountStatus.CLOSED);
        assertThrows(AccountClosedException.class,
                () -> account.withdraw("TRX-001", 500));
    }

    @Test
    void shouldThrowWhenWithdrawNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw("TRX-001", -500));
    }

    @Test
    void shouldThrowWhenRemainsBalanceLessMinBalance() {
        account = new SavingsAccount("ACC-000003", 1000.0, 1000,
                0.12, 500);
        assertThrows(InsufficientFundsException.class,
                () -> account.withdraw("TRX-001", 800));
    }

    @Test
    void shouldCreateTransactionWhenWithdraw() {
        account.withdraw("TRX-001", 500);
        assertEquals(1, account.getTransactions().size());
    }


    @Test
    void transfer() {
    }
}