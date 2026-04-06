package com.bank.service;

import java.io.*;

public class IdService {
    private int employeeCounter;
    private int customerCounter;
    private int accountCounter;
    private int transactionCounter;
    private static final String EMPLOYEE_COUNT_FILE
            = "countEmployee.txt";
    private static final String CUSTOMER_COUNT_FILE
            = "countCustomer.txt";
    private static final String ACCOUNT_COUNT_FILE
            = "countAccount.txt";
    private static final String TRANSACTION_COUNT_FILE
            = "countTransaction.txt";

    public IdService() {
        loadCounters();
    }

    private void loadCounters() {
      loadAccountCounter();
      loadCustomerCounter();
      loadEmployeeCounter();
      loadTransactionCounter();
    }

    private void loadEmployeeCounter(){
        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(EMPLOYEE_COUNT_FILE))) {
            employeeCounter = Integer.parseInt(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            employeeCounter = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTransactionCounter() {
        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(TRANSACTION_COUNT_FILE))) {
            transactionCounter = Integer.parseInt(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            transactionCounter = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerCounter() {
        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(CUSTOMER_COUNT_FILE))) {
            customerCounter = Integer.parseInt(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            customerCounter = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAccountCounter() {
        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(ACCOUNT_COUNT_FILE))) {
           accountCounter = Integer.parseInt(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            accountCounter = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveEmployeeCounter() {
       try (BufferedWriter bufferedWriter = new BufferedWriter (new FileWriter(EMPLOYEE_COUNT_FILE))) {
          bufferedWriter.write(String.valueOf(employeeCounter));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    private void saveAccountCounter() {
        try (BufferedWriter bufferedWriter = new BufferedWriter (new FileWriter(ACCOUNT_COUNT_FILE))) {
            bufferedWriter.write(String.valueOf(accountCounter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveTransactionCounter() {
        try (BufferedWriter bufferedWriter = new BufferedWriter (new FileWriter(TRANSACTION_COUNT_FILE))) {
            bufferedWriter.write(String.valueOf(transactionCounter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveCustomerCounter() {
        try (BufferedWriter bufferedWriter = new BufferedWriter (new FileWriter(CUSTOMER_COUNT_FILE))) {
            bufferedWriter.write(String.valueOf(customerCounter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateEmployeeId() {
        employeeCounter++;
        saveEmployeeCounter();
        return String.format("EMP-%06d", employeeCounter);
    }

    public String generateCustomerId() {
        customerCounter++;
        saveCustomerCounter();
        return String.format("CUS-%06d", customerCounter);
    }

    public String generateAccountId() {
        accountCounter++;
        saveAccountCounter();
        return String.format("ACC-%06d", accountCounter);
    }

    public String generateTransactionId () {
        transactionCounter++;
        saveTransactionCounter();
        return String.format("TRX-%06d", transactionCounter);
    }
}
