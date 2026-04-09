package com.bank;

import com.bank.enums.BusinessType;
import com.bank.enums.MaritalStatus;
import com.bank.menu.MainMenu;
import com.bank.service.*;

import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FileService fileService = new FileService();
        IdService idService = new IdService();
        BankService bankService =
                new BankService(fileService);
        EmployeeService employeeService =
                new EmployeeService(idService, fileService);
        CustomerService customerService = new CustomerService(fileService, idService);
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(bankService, employeeService, customerService, scanner);
        fileService.loadAll(
                bankService, employeeService, customerService);

        mainMenu.show();
//        bankService.setupBank(
//                "Chase Bank",
//                "123 Main St Chicago",
//                "7795551234",
//                "www.chase.com");
//
//        System.out.println(bankService.getBank());
//
//
//
//        empService.addFullTimeEmployee(
//                "John", "Smith", "1980-01-15",
//                "john@bank.com", "7795551111",
//                "Manager", 5000.0);
//
//        empService.addContractor(
//                "Jane",
//                "Doe",
//                "1990-03-20",
//                "jane@bank.com",
//                "7795552222",
//                "Developer",
//                50.0,
//                "2026-12-31"
//        );
//        empService.showAllEmployees();
//
//
//
//        customerService.addBusinessCustomer("Amy", "Karlson", "2000-05-25", "amy@gmail.com",
//                "6673456213", "BRS Company", "22-6546321",
//                BusinessType.LIMITED_PARTNERSHIP, "Amy Karlson");
//
//        customerService.addIndividualCustomer(
//                "Hanna",
//                "Devnina",
//                "1990-05-15",
//                "hanna@gmail.com",
//                "7795553333",
//                "123-45-6789",
//                "Teacher",
//                MaritalStatus.SINGLE );

//

//    customerService.executeWithdraw("ACC-000002", 500.0);
//    customerService.executeTransfer("ACC-000002", "ACC-000001", 500.0);
//    customerService.showAllAccountTransactions("ACC-000002");



//        bankService.showBank();
//        empService.showAllEmployees();
//        customerService.showAllCustomers();
//        customerService.showAllAccounts();

    }


}