package com.bank.service;

import com.bank.enums.AccountStatus;
import com.bank.enums.BusinessType;
import com.bank.enums.MaritalStatus;
import com.bank.enums.TransactionType;
import com.bank.model.*;
import com.bank.model.Customer;
import com.bank.model.IndividualCustomer;
import com.bank.model.BusinessCustomer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static final String BANK_FILE = "bank.txt";
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String EMPLOYEE_FILE = "employees.txt";
    private static final String ACCOUNT_FILE = "accounts.txt";
    private static final String TRANSACTION_FILE = "transactions.txt";

    public void saveBank(Bank bank) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(BANK_FILE))) {
           bufferedWriter.write(String.format("%s,%s,%s,%s,%s", bank.getBankId(), bank.getName(),
                   bank.getAddress(), bank.getPhone(), bank.getWebUrl()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveEmployees(List<Employee> employees) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
            for (Employee employee : employees) {
                String common = String.format(
                        "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        employee.getClass().getSimpleName(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getBirthDate(),
                        employee.getEmail(),
                        employee.getPhone(),
                        employee.getEmployeeId(),
                        employee.getPosition(),
                        employee.getHireDate(),
                        employee.getEndDate());

                if (employee instanceof FullTimeEmployee fte) {
                    bufferedWriter.write(common + "," +
                            fte.getSalary());
                } else if (employee instanceof Contractor c) {
                    bufferedWriter.write(common + "," +
                            c.getHourlyRate() + "," +
                            c.getContractEndDate() + "," +
                            c.getHoursWorked());
                }
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveCustomers(List<Customer> customers) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer customer : customers) {
                String common = String.format(
                        "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        customer.getClass().getSimpleName(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getBirthDate(),
                        customer.getEmail(),
                        customer.getPhone(),
                        customer.getCustomerId(),
                        customer.getRegistrationDate(),
                        customer.getClosureDate(),
                        customer.isActive());
                if (customer instanceof IndividualCustomer ic) {
                    bufferedWriter.write(common + "," +  ic.getSsn() + "," + ic.getOccupation()
                            + "," + ic.getMaritalStatus().toString());
                    bufferedWriter.newLine();
                } else if (customer instanceof BusinessCustomer bc) {
                      bufferedWriter.write(common + "," + bc.getCompanyName() + "," + bc.getTaxId()
                      + "," + bc.getBusinessType().toString() + "," + bc.getContactPerson());
                      bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAccounts(List<Customer> customers) {
       try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) {
           for (Customer customer : customers) {
               for (Account account : customer.getAccounts()) {
                   String common = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                           account.getClass().getSimpleName(),
                           customer.getCustomerId(),
                           account.getAccountId(),
                           account.getOpenDate(),
                           account.getClosureDate(),
                           account.getBalance(),
                           account.getStatus().toString(),
                           account.getTransactionLimit()
                   );
                   if (account instanceof CheckingAccount ca) {
                      bufferedWriter.write(
                              common + "," + ca.getOverdraftLimit() + ","
                              + ca.getMonthlyFee()
                      );
                      bufferedWriter.newLine();
                   } else if (account instanceof SavingsAccount sa) {
                       bufferedWriter.write(
                               common + "," + sa.getInterestRate() + ","
                               + sa.getMinBalance()
                       );
                       bufferedWriter.newLine();
                   } else if (account instanceof FixedDepositAccount fda) {
                       bufferedWriter.write(
                               common + "," + fda.getInterestRate() + ","
                                       + fda.getLockPeriodMonths() + ","
                               + fda.getPenaltyRate() + "," + fda.getMaturityDate()
                       );
                       bufferedWriter.newLine();
                   }
               }
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    public void saveTransactions(
            List<Customer> customers) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(
                             new FileWriter(TRANSACTION_FILE))) {
            for (Customer customer : customers) {
                for (Account account :
                        customer.getAccounts()) {
                    for (Transaction transaction :
                            account.getTransactions()) {
                        bufferedWriter.write(
                                String.format("%s,%s,%s,%s,%s,%s",
                                        transaction.getTransactionId(),
                                        transaction.getAccountId(),
                                        transaction.getType(),
                                        transaction.getAmount(),
                                        transaction.getBalanceAfter(),
                                        transaction.getDateTime()));
                        bufferedWriter.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Bank readBank() {
       try (BufferedReader bufferedReader = new BufferedReader(new FileReader(BANK_FILE))) {
           String line = bufferedReader.readLine();
           String[] parts = line.split(",");
           return new Bank(parts[0], parts[1],
                   parts[2], parts[3], parts[4]);
       } catch (FileNotFoundException e) {
           return null;
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    public List<Employee> readEmployees() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
           String line;
           List<Employee> employees = new ArrayList<>();
           while((line = bufferedReader.readLine()) != null) {
               String[] parts = line.split(",");
               if (parts[0].equals("FullTimeEmployee")) {
                   FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(
                           parts[1], parts[2], parts[3], parts[4], parts[5],
                           parts[6], parts[7], Double.parseDouble(parts[10]));
                   fullTimeEmployee.setHireDate(parts[8]);
                   fullTimeEmployee.setEndDate(parts[9]);
                  employees.add(fullTimeEmployee);
               } else if (parts[0].equals("Contractor")) {
                   Contractor contractor = new Contractor(
                           parts[1], parts[2], parts[3], parts[4], parts[5],
                           parts[6], parts[7], Double.parseDouble(parts[10]), parts[11]);
                   contractor.setHireDate(parts[8]);
                   contractor.setEndDate(parts[9]);
                   contractor.setHoursWorked(Double.parseDouble(parts[12]));
                   employees.add(contractor);
               }
           }
          return employees;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> readCustomers() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            List<Customer> customers = new ArrayList<>();
            while((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("IndividualCustomer")) {
                    IndividualCustomer individualCustomer = new IndividualCustomer(
                            parts[1], parts[2], parts[3], parts[4], parts[5],
                            parts[6], parts[9], parts[10], MaritalStatus.valueOf(parts[11])
                    );
                    individualCustomer.setRegistrationDate(parts[7]);
                    if (!parts[8].equals("null")) {
                        individualCustomer.setClosureDate(parts[8]);
                    }
                    individualCustomer.setActive(Boolean.parseBoolean(parts[9]));
                    customers.add(individualCustomer);
                } else if (parts[0].equals("BusinessCustomer")) {
                    BusinessCustomer businessCustomer = new BusinessCustomer(
                            parts[1], parts[2], parts[3], parts[4], parts[5],
                            parts[6], parts[9], parts[10], BusinessType.valueOf(parts[11]),
                            parts[12]
                    );
                    businessCustomer.setRegistrationDate(parts[7]);
                    if (!parts[8].equals("null")) {
                        businessCustomer.setClosureDate(parts[8]);
                    }
                    businessCustomer.setActive(Boolean.parseBoolean(parts[9]));
                    customers.add(businessCustomer);
                }
            }
            return customers;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readAccounts(List<Customer> customers) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                for (Customer customer : customers) {
                    if (customer.getCustomerId().equals(parts[1])) {
                        if (parts[0].equals("CheckingAccount")) {
                            CheckingAccount checkingAccount = new CheckingAccount(
                                    parts[2], Double.parseDouble(parts[5]), Double.parseDouble(parts[7]),
                                    Double.parseDouble(parts[8]), Double.parseDouble(parts[9]));
                            checkingAccount.setOpenDate(parts[3]);
                            if (!parts[4].equals("null")) {
                                checkingAccount.setClosureDate(parts[4]);
                            }
                            checkingAccount.setStatus(AccountStatus.valueOf(parts[6]));
                            customer.addAccount(checkingAccount);
                        } else if (parts[0].equals("SavingsAccount")) {
                            SavingsAccount savingsAccount = new SavingsAccount(
                                    parts[2], Double.parseDouble(parts[5]), Double.parseDouble(parts[7]),
                                    Double.parseDouble(parts[8]), Double.parseDouble(parts[9]));
                            savingsAccount.setOpenDate(parts[3]);
                            if (!parts[4].equals("null")) {
                                savingsAccount.setClosureDate(parts[4]);
                            }
                            savingsAccount.setStatus(AccountStatus.valueOf(parts[6]));
                            customer.addAccount(savingsAccount);
                        } else if (parts[0].equals("FixedDepositAccount")) {
                            FixedDepositAccount fixedDepositAccount = new FixedDepositAccount(
                                    parts[2], Double.parseDouble(parts[5]), Double.parseDouble(parts[7]),
                                    Double.parseDouble(parts[8]), Integer.parseInt(parts[9]),
                                    Double.parseDouble(parts[10]), parts[11] );
                            fixedDepositAccount.setOpenDate(parts[3]);
                            if (!parts[4].equals("null")) {
                                fixedDepositAccount.setClosureDate(parts[4]);
                            }
                            fixedDepositAccount.setStatus(AccountStatus.valueOf(parts[6]));
                            customer.addAccount(fixedDepositAccount);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readTransactions(List<Customer> customers) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                for (Customer customer : customers) {
                    for (Account account : customer.getAccounts()) {
                        if(account.getAccountId().equals(parts[1])) {
                            Transaction transaction = new Transaction(
                                    parts[0], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]),
                                    TransactionType.valueOf(parts[2]), parts[1], parts[5]);
                            account.addTransaction(transaction);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAll(
            BankService bankService,
            EmployeeService employeeService,
            CustomerService customerService) {


        Bank bank = readBank();
        if (bank != null) {
            bankService.setBank(bank);
        }


        List<Employee> employees = readEmployees();
        employeeService.setEmployees(employees);


        List<Customer> customers = readCustomers();
        customerService.setCustomers(customers);


        readAccounts(customers);


        readTransactions(customers);
    }
}


