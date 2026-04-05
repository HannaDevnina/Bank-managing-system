package com.bank.model;

import com.bank.enums.AccountStatus;
import com.bank.enums.TransactionType;
import com.bank.exceptions.AccountClosedException;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.TransactionLimitExceededException;

import java.time.LocalDate;

public class FixedDepositAccount extends Account{
    private double interestRate;
    private int lockPeriodMonths;
    private double penaltyRate;
    private String maturityDate;

    public FixedDepositAccount (String accountId, double balance, double transactionLimit, double interestRate,
                                int lockPeriodMonths, double penaltyRate, String maturityDate) {
        super(accountId, balance, transactionLimit);
        setInterestRate(interestRate);
        setLockPeriodMonths(lockPeriodMonths);
        setPenaltyRate(penaltyRate);
        setMaturityDate(maturityDate);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest shouldn't be negative number!");
        }
        this.interestRate = interestRate;
    }

    public int getLockPeriodMonths() {
        return lockPeriodMonths;
    }

    public void setLockPeriodMonths(int lockPeriodMonths) {
        if (lockPeriodMonths <= 0) {
            throw new IllegalArgumentException("Lock period should be positive number!");
        }
        this.lockPeriodMonths = lockPeriodMonths;
    }

    public double getPenaltyRate() {
        return penaltyRate;
    }

    public void setPenaltyRate(double penaltyRate) {
        if (penaltyRate < 0) {
            throw new IllegalArgumentException("Penalty shouldn't be negative number!");
        }
        this.penaltyRate = penaltyRate;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        if (!maturityDate.matches("^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
            throw new IllegalArgumentException("Date should be in ISO-8601 format");
        }
        LocalDate today = LocalDate.now();
        if (LocalDate.parse(maturityDate).isBefore(today)) {
            throw new IllegalArgumentException("Maturity date must be in the future!");
        }
        this.maturityDate = maturityDate;
    }

    @Override
    public String toString() {
        return "FixedDepositAccount{" +
                "interestRate=" + interestRate +
                ", lockPeriodMonths=" + lockPeriodMonths +
                ", penaltyRate=" + penaltyRate +
                ", maturityDate='" + maturityDate + '\'' +
                "} " + super.toString();
    }

    @Override
    public double calculateInterest() {
        if (isMature()) {
            return (getBalance() *
                    (interestRate / 12))
                    * lockPeriodMonths;
        } else {
            return ((getBalance() *
                    (interestRate / 12))
                    * lockPeriodMonths)
                    - calculatePenalty();
        }
    }

    @Override
    public double getAvailableBalance() {
        if (isMature()) {
            return getBalance();
        } else {
            return 0;
        }
    }

    @Override
    public void withdraw(double amount) {
        if (!isMature()) {
            throw new RuntimeException(
                    "Account locked until: " + maturityDate);
        }
        if (getStatus() != AccountStatus.ACTIVE) {
            throw new AccountClosedException("Account is not active!");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal should be positive number!");
        }

        if (amount > getTransactionLimit()) {
            throw new TransactionLimitExceededException("You exceeded transaction limit!");
        }
        if (getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds. Available: " + getAvailableBalance());
        }

        setBalance(getBalance() - amount);
        Transaction transaction = new Transaction(
                "TRX - TEMP",
                amount,
                getBalance(),
                TransactionType.WITHDRAWAL,
                getAccountId()
        );

        addTransaction(transaction);
    }

    @Override
    public void transfer(Account destination, double amount) {
        if (!isMature()) {
            throw new RuntimeException(
                    "Account locked until: " + maturityDate);
        } else {
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

            if (getBalance() < amount) {
                throw new InsufficientFundsException("Insufficient funds. Available balance: " + getAvailableBalance());
            }

            setBalance(getBalance() - amount);
            destination.setBalance(destination.getBalance() + amount);

            Transaction transaction = new Transaction(
                    "TRX - TEMP",
                    amount,
                    getBalance(),
                    TransactionType.TRANSFER,
                    getAccountId()
            );
            addTransaction(transaction);

            destination.addTransaction(new Transaction(
                    "TRX-TEMP",
                    amount,
                    destination.getBalance(),
                    TransactionType.TRANSFER,
                    destination.getAccountId()
            ));
        }
    }

    public boolean isMature() {
        return LocalDate.now()
                .isAfter(LocalDate.parse(maturityDate));
    }

    public double calculatePenalty() {
        if (isMature()) return 0;
        return getBalance() * penaltyRate;
    }
}

