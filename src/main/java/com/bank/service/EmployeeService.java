package com.bank.service;

import com.bank.exceptions.EmployeeNotFoundException;
import com.bank.model.Contractor;
import com.bank.model.Employee;
import com.bank.model.FullTimeEmployee;


import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    private IdService idService;
    private FileService fileService;

    public EmployeeService(IdService idService, FileService fileService) {
        this.idService = idService;
        this.fileService = fileService;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addFullTimeEmployee(   String firstName,
                                       String lastName,
                                       String birthDate,
                                       String email,
                                       String phone,
                                       String position,
                                       double salary) {
        String employeeId = idService.generateEmployeeId();
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(
                firstName, lastName, birthDate, email, phone, employeeId,
                position, salary);
        employees.add(fullTimeEmployee);
        fileService.saveEmployees(employees);
    }

    public void addContractor(
            String firstName,
            String lastName,
            String birthDate,
            String email,
            String phone,
            String position,
            double hourlyRate,
            String contractEndDate) {
        String employeeId = idService.generateEmployeeId();
        Contractor contractor = new Contractor(
                firstName, lastName, birthDate, email, phone, employeeId,
                position, hourlyRate, contractEndDate);
        employees.add(contractor);
        fileService.saveEmployees(employees);
    }

    public Employee findEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Employee not found");
    }

    public List<Employee> findEmployeeByFullName(String firstName, String lastName) {
        List<Employee> found = new ArrayList<>();
        for (Employee employee : employees) {
            if(employee.getFirstName().equals(firstName)
            && employee.getLastName().equals(lastName)) {
                found.add(employee);
            }
        }
        return found;
    }

    public void showEmployeeByFullName(String firstName, String lastName) {
        List<Employee> found = findEmployeeByFullName(firstName, lastName);
        if (found.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        found.forEach(System.out::println);
    }

    public void updateEmployeeFirstName(String employeeId, String newFirstName) {
        Employee employee = findEmployeeById(employeeId);
        employee.setFirstName(newFirstName);
        fileService.saveEmployees(employees);
    }

    public void updateEmployeeLastName(String employeeId, String newLastName) {
        Employee employee = findEmployeeById(employeeId);
        employee.setLastName(newLastName);
        fileService.saveEmployees(employees);
    }

    public void updateBirthDate(String employeeId, String newBirthDate) {
        Employee employee = findEmployeeById(employeeId);
        employee.setBirthDate(newBirthDate);
        fileService.saveEmployees(employees);
    }

    public void updateEmail(String employeeId, String newEmail) {
        Employee employee = findEmployeeById(employeeId);
        employee.setEmail(newEmail);
        fileService.saveEmployees(employees);
    }

    public void updatePhone(String employeeId, String newPhone) {
        Employee employee = findEmployeeById(employeeId);
        employee.setPhone(newPhone);
        fileService.saveEmployees(employees);
    }

    public void showAllEmployees() {
        employees.forEach(System.out::println);
    }

    public void showEmployee(String employeeId) {
        Employee employee = findEmployeeById(employeeId);
        System.out.println(employee);
    }

    public void promoteEmployee(String employeeId, String newPosition) {
        Employee employee = findEmployeeById(employeeId);
        employee.setPosition(newPosition);
        fileService.saveEmployees(employees);
    }

    public void fireEmployee(String employeeId) {
        Employee employee = findEmployeeById(employeeId);
        employee.setEndDate();
        employee.setActive(false);
        fileService.saveEmployees(employees);
    }

    public void deactivateEmployee(String employeeId) {
        Employee employee = findEmployeeById(employeeId);
        employee.setActive(false);
        fileService.saveEmployees(employees);
    }

    public double calculateSalary(String employeeId) {
        Employee employee = findEmployeeById(employeeId);
        return employee.calculateSalary();
    }

    public void saveEmployees() {
        fileService.saveEmployees(employees);
    }

    public void loadEmployees() {
       this.employees = fileService.readEmployees();
    }
}


