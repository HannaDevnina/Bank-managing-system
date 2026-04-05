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
        return "FullTimeEmployee{" +
                "salary=" + salary +
                "} " + super.toString();
    }

    @Override
    public double calculateSalary() {
        return salary;
    }
}
