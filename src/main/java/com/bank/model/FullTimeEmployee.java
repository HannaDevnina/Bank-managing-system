package com.bank.model;

public class FullTimeEmployee extends Employee {
    private double salary;

    public FullTimeEmployee(String firstName, String lastName, String birthDate, String email,
                            String phone, String employeeId, String position, double salary) {
        super(firstName, lastName, birthDate, email, phone, employeeId, position);
        setSalary(salary);
        setHasBenefits(true);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if(salary <= 0) {
            throw new IllegalArgumentException("Salary shouldn't be negative number!");
        }
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%-12s|%-20s|%-11s|%-26s|%-12s|%-20s|%-10s|%-6s|%-6s|%-11s|%-12s|%-11s|%-11s|%-6s",
                getEmployeeId(), getFullName(), getBirthDate(), getEmail(), getPhone(), getPosition(),
                getSalary(), " ", " ", getHireDate(), hasBenefits(), " ", getEndDate(), isActive());
    }

    @Override
    public double calculateSalary() {
        return salary;
    }
}
