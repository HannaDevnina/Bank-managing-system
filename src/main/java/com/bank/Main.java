package com.bank;
import com.bank.menu.MainMenu;
import com.bank.service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        IdService idService = new IdService();
        BankService bankService = new BankService(fileService);
        EmployeeService employeeService = new EmployeeService(idService, fileService);
        CustomerService customerService =  new CustomerService(fileService, idService);

        fileService.loadAll( bankService, employeeService, customerService);

        Scanner scanner = new Scanner(System.in);
        new MainMenu( bankService, employeeService,
                customerService, scanner).show();
    }
}