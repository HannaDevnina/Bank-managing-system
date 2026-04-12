package com.bank.menu;

import com.bank.exceptions.EmployeeNotFoundException;
import com.bank.service.EmployeeService;

import java.util.Scanner;

public class EmployeeMenu {
    private EmployeeService employeeService;
    private Scanner scanner;

    public EmployeeMenu(EmployeeService employeeService, Scanner scanner) {
        this.employeeService = employeeService;
        this.scanner = scanner;
    }

    public void show() {
        while (true) {
            System.out.println("\n" + "=".repeat(30));
            System.out.println("  EMPLOYEE MENU");
            System.out.println("=".repeat(30));
            System.out.println("1. Add Full Time Employee");
            System.out.println("2. Add Contractor");
            System.out.println("3. Show All Employees");
            System.out.println("4. Search Employee By Id");
            System.out.println("5. Search Employee By Full Name" );
            System.out.println("6. Update Employee First Name" );
            System.out.println("7. Update Employee Last Name" );
            System.out.println("8. Update Employee Email" );
            System.out.println("9. Update Employee Phone Number" );
            System.out.println("10. Update Date Of Birth" );
            System.out.println("11. Promote Employee" );
            System.out.println("12. Fire Employee" );
            System.out.println("13. Calculate Salary" );
            System.out.println("14. Deactivate Employee" );
            System.out.println("0. Back to Main Menu");
            System.out.println("=".repeat(30));
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1" :
                   try {
                       System.out.print("Enter First Name: ");
                       String firstName = scanner.nextLine().strip();
                       System.out.print("Enter Last Name: ");
                       String lastName = scanner.nextLine().strip();
                       System.out.print("Enter Date Of Birth: ");
                       String birthDate = scanner.nextLine().strip();
                       System.out.print("Enter Email: ");
                       String email = scanner.nextLine().strip();
                       System.out.print("Enter Phone Number: ");
                       String phone = scanner.nextLine().strip();
                       System.out.print("Enter position: ");
                       String position = scanner.nextLine().strip();
                       System.out.print("Enter salary: ");
                       double salary = scanner.nextDouble();
                       scanner.nextLine();
                       employeeService.addFullTimeEmployee(firstName, lastName, birthDate, email, phone,
                               position, salary);
                       System.out.println("=".repeat(30));
                       System.out.println();
                       System.out.println("New Full Time Employee Created Successfully!");
                       employeeService.showEmployeeByFullName(firstName, lastName);
                   } catch (IllegalArgumentException e) {
                       System.out.println("Error: "
                               + e.getMessage());
                   }
                   break;
                case "2" :
                   try {
                       System.out.print("Enter First Name: ");
                       String firstName = scanner.nextLine().strip();
                       System.out.print("Enter Last Name: ");
                       String lastName = scanner.nextLine().strip();
                       System.out.print("Enter Date Of Birth (YYYY-MM-DD): ");
                       String birthDate = scanner.nextLine().strip();
                       System.out.print("Enter Email: ");
                       String email = scanner.nextLine().strip();
                       System.out.print("Enter Phone Number: ");
                       String phone = scanner.nextLine().strip();
                       System.out.print("Enter position: ");
                       String position = scanner.nextLine().strip();
                       System.out.print("Enter Hourly Rate: ");
                       double hourlyRate = scanner.nextDouble();
                       scanner.nextLine();
                       System.out.print("Enter Contract End Date: ");
                       String contractEndDate = scanner.nextLine();
                       employeeService.addContractor(firstName, lastName, birthDate, email, phone,
                              position, hourlyRate, contractEndDate);
                       System.out.println("New Contractor Created Successfully!");
                       System.out.println();
                       employeeService.showEmployeeByFullName(firstName, lastName);
                   } catch (IllegalArgumentException e) {
                       System.out.println("Error: "
                               + e.getMessage());
                   }
                    break;
                case "3" :
                    employeeService.showAllEmployees();
                    break;
                case "4" :
                    try {
                        System.out.print("Enter Employee Id: ");
                        String employeeId = scanner.nextLine().strip();
                        employeeService.showEmployee(employeeId);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "5" :
                    try {
                        System.out.print("Enter Employee First Name: ");
                        String firstname = scanner.nextLine().strip();
                        System.out.print("Enter Employee Last Name: ");
                        String lastName = scanner.nextLine().strip();
                        employeeService.showEmployeeByFullName(firstname, lastName);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "6" :
                    try {
                        System.out.print("Enter Employee Id: ");
                        String employeeId = scanner.nextLine().strip();
                        System.out.print("Enter Employee New First Name: ");
                        String newName = scanner.nextLine().strip();
                        employeeService.updateEmployeeFirstName(employeeId, newName);
                        System.out.println("Employee First Name Updated Successfully!");
                        employeeService.showEmployee(employeeId);
                    } catch (IllegalArgumentException | EmployeeNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "7" :
                    try {
                        System.out.print("Enter Employee Id: ");
                        String employeeId = scanner.nextLine().strip();
                        System.out.print("Enter Employee New Last Name: ");
                        String newLastName = scanner.nextLine().strip();
                        employeeService.updateEmployeeLastName(employeeId, newLastName);
                        System.out.println("Employee Last Name Updated Successfully!");
                        employeeService.showEmployee(employeeId);
                    } catch (IllegalArgumentException | EmployeeNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "8" :
                    try {
                        System.out.print("Enter Employee Id: ");
                        String employeeId = scanner.nextLine().strip();
                        System.out.print("Enter Employee New Email: ");
                        String newEmail = scanner.nextLine().strip();
                        employeeService.updateEmail(employeeId, newEmail);
                        System.out.println("Employee  Email Updated Successfully!");
                        employeeService.showEmployee(employeeId);
                    } catch (IllegalArgumentException | EmployeeNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "9" :
                    try {
                        System.out.print("Enter Employee Id: ");
                        String employeeId = scanner.nextLine().strip();
                        System.out.print("Enter Employee New Phone Number: ");
                        String newPhone = scanner.nextLine().strip();
                        employeeService.updatePhone(employeeId, newPhone);
                        System.out.println("Employee Phone Number Updated Successfully!");
                        employeeService.showEmployee(employeeId);
                    } catch (IllegalArgumentException | EmployeeNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "10" :
                    try {
                        System.out.print("Enter Employee Id: ");
                        String employeeId = scanner.nextLine().strip();
                        System.out.print("Enter Employee New Birth Of Date: ");
                        String newBirthDate = scanner.nextLine().strip();
                        employeeService.updateBirthDate(employeeId, newBirthDate);
                        System.out.println("Employee Date Of Birth Updated Successfully!");
                        employeeService.showEmployee(employeeId);
                    } catch (IllegalArgumentException | EmployeeNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "11" :
                    try {
                        System.out.print("Enter Employee Id: ");
                        String employeeId = scanner.nextLine().strip();
                        System.out.print("Enter New Employee Position: ");
                        String newPosition = scanner.nextLine().strip();
                        employeeService.promoteEmployee(employeeId, newPosition);
                        System.out.println("Employee Promoted Successfully!");
                        employeeService.showEmployee(employeeId);
                    } catch (IllegalArgumentException | EmployeeNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "12" :
                    try {
                        System.out.print("Enter Employee Id: ");
                        String employeeId = scanner.nextLine().strip();
                        employeeService.fireEmployee(employeeId);
                        System.out.println("Employee Fired Successfully!");
                        employeeService.showEmployee(employeeId);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "13" :
                    try {
                        System.out.print("Enter Employee Id: \n");
                        String employeeId = scanner.nextLine().strip();
                        double salary = employeeService.calculateSalary(employeeId);
                        System.out.println("Salary: $" + salary);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "14" :
                    try {
                        System.out.print("Enter Employee Id: ");
                        String employeeId = scanner.nextLine().strip();
                        employeeService.deactivateEmployee(employeeId);
                        System.out.println("Employee Deactivated Successfully!");
                        employeeService.showEmployee(employeeId);
                    }  catch (EmployeeNotFoundException e) {
                        System.out.println("Error: "
                                + e.getMessage());
                    }
                    break;
                case "0" : {
                    System.out.println("Returning to main menu...");
                    return;
                }
                default : {
                    System.out.println("Invalid choice!");
                }
            }
        }
    }
}
