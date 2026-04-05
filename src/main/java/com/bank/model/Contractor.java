package com.bank.model;

import java.time.LocalDate;

public class Contractor extends Employee {
    private double hourlyRate;
    private String contractEndDate;
    private double hoursWorked;

    public Contractor(String firstName, String lastName, String birthDate, String email,
                      String phone, String employeeId, String position, double hourlyRate,
                      String contractEndDate) {
        super(firstName, lastName, birthDate, email, phone, employeeId, position);
        setHourlyRate(hourlyRate);
        setContractEndDate(contractEndDate);
        this.hoursWorked = 0;
        setHasBenefits(false);
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate <= 0) {
            throw new IllegalArgumentException("Hourly rate should be positive number!");
        }
        this.hourlyRate = hourlyRate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        if (!contractEndDate.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
            throw new IllegalArgumentException("Date should be in ISO-8601 format");
        }
        LocalDate today = LocalDate.now();
        if (isActive()) {
            if(today.isAfter(LocalDate.parse(contractEndDate))) {
                throw new IllegalArgumentException("Contract end date should be in the future!");
            }
        }
        this.contractEndDate = contractEndDate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked shouldn't be negative number");
        }
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString() {
        return "Contractor{" +
                "hourlyRate=" + hourlyRate +
                ", contractEndDate='" + contractEndDate + '\'' +
                ", hoursWorked=" + hoursWorked +
                "} " + super.toString();
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}
