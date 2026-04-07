package com.bank.model;

import java.time.LocalDate;

public abstract class Employee extends Person{
    private String employeeId;
    private String position;
    private String hireDate;
    private String endDate;
    private boolean hasBenefits;
//    private EmployeeStatus status; implement in next version


    public Employee(String firstName, String lastName, String birthDate,
                    String email, String phone, String employeeId,
                    String position) {
        super(firstName, lastName, birthDate, email, phone);
        this.employeeId = employeeId;
        setPosition(position);
        this.hireDate = LocalDate.now().toString();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (!position.matches("^[A-Z][A-Za-z ]{1,49}$")) {
            throw new IllegalArgumentException("Position should start form capital letter, no number allowed!");
        }
        this.position = position;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate() {
        this.endDate = LocalDate.now().toString();
    }

    public boolean hasBenefits() {
        return hasBenefits;
    }

    public void setHasBenefits(boolean hasBenefits) {
        this.hasBenefits = hasBenefits;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", position='" + position + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", hasBenefits=" + hasBenefits +
                "} " + super.toString();
    }

    public void fire() {
        setActive(false);
        setEndDate();
    }

    public void promoteEmployee(String newPosition) {
        setPosition(newPosition);
    }

    abstract public double calculateSalary();
}
