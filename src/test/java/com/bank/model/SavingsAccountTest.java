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
        account.withdraw("TRX-000001", 500);
        assertEquals(2000, account.getBalance());
    }

    @Test
    void shouldThrowWhenWithdrawAboveLimit() {
        assertThrows(TransactionLimitExceededException.class,
                () -> account.withdraw("TRX-000001", 1500));
    }

    @Test
    void shouldNotThrowWhenWithdrawEqualLimit() {
        assertDoesNotThrow(
                () -> account.withdraw("TRX-000001", 1000));
    }

    @Test
    void shouldThrowWhenWithdrawZero() {
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw("TRX-000001", 0));
    }

    @Test
    void shouldThrowWhenWithdrawFromClosed() {
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
    void shouldThrowWhenBalanceWouldGoBelowMinimum() {
        account = new SavingsAccount("ACC-000003", 1000.0, 1000,
                0.12, 500);
        assertThrows(InsufficientFundsException.class,
                () -> account.withdraw("TRX-000001", 800));
    }

    @Test
    void shouldCreateTransactionWhenWithdraw() {
        account.withdraw("TRX-000001", 500);
        assertEquals(1, account.getTransactions().size());
    }

    @Test
    void shouldTransferBetweenAccounts() {
        SavingsAccount destination = new SavingsAccount("ACC-000004", 500.0,
                1000.0, 0.05, 100.0);
        account.transfer("TRX-000001", destination, 500);
        assertEquals(2000, account.getBalance());
        assertEquals(1000, destination.getBalance());
    }

    @Test
    void shouldThrowWhenTransferFromClosedAccount() {
        SavingsAccount destination = new SavingsAccount("ACC-000004", 500.0,
                1000.0, 0.05, 100.0);
        account.setStatus(AccountStatus.CLOSED);
        assertThrows(AccountClosedException.class,
                () -> account.transfer("TRX-000001", destination, 500 ));
    }

    @Test
    void shouldThrowWhenTransferToClosedAccount() {
        SavingsAccount destination = new SavingsAccount("ACC-000004", 500.0,
                1000.0, 0.05, 100.0);
        destination.setStatus(AccountStatus.CLOSED);
        assertThrows(AccountClosedException.class,
                () -> account.transfer("TRX-000001", destination, 500));
    }

    @Test
    void shouldThrowWhenTransferNegativeAmount() {
        SavingsAccount destination = new SavingsAccount("ACC-000004", 500.0,
                1000.0, 0.05, 100.0);
        assertThrows(IllegalArgumentException.class,
                () -> account.transfer("TRX-000001", destination, -500));
    }

    @Test
    void shouldThrowWhenTransferZero() {
        SavingsAccount destination = new SavingsAccount("ACC-000004", 500.0,
                1000.0, 0.05, 100.0);
        assertThrows(IllegalArgumentException.class,
                () -> account.transfer("TRX-000001", destination, 0));
    }

    @Test
    void shouldThrowWhenTransactionLimitExceeded() {
        SavingsAccount destination = new SavingsAccount("ACC-000004", 500.0,
                1000.0, 0.05, 100.0);
        assertThrows(TransactionLimitExceededException.class,
                () -> account.transfer("TRX-000001", destination, 1500));
    }

    @Test
    void shouldThrowWhenMinBalanceViolated() {
        account = new SavingsAccount("ACC-000003", 1000.0, 1000,
                0.12, 500);
        SavingsAccount destination = new SavingsAccount("ACC-000004", 500.0,
                1000.0, 0.05, 100.0);
        assertThrows(InsufficientFundsException.class,
                () -> account.transfer("TRX-000001", destination, 800));
    }

    @Test
    void shouldAddNewTransactionToAccountList() {
        SavingsAccount destination = new SavingsAccount("ACC-000004", 500.0,
                1000.0, 0.05, 100.0);
        account.transfer("TRX-000001", destination, 500);
        assertEquals(1, account.getTransactions().size());
    }

    @Test
    void shouldAddNewTransactionToDestinationAccountList() {
        SavingsAccount destination = new SavingsAccount("ACC-000004", 500.0,
                1000.0, 0.05, 100.0);
        account.transfer("TRX-000001", destination, 500);
        assertEquals(1, destination.getTransactions().size());
    }
}