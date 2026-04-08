package com.bank.model;

import com.bank.enums.MaritalStatus;

public class IndividualCustomer extends Customer{
    private String ssn;
    private String occupation;
    private MaritalStatus maritalStatus;

    public IndividualCustomer(String firstName, String lastName, String birthDate,
                              String email, String phone, String customerId, String ssn,
                              String occupation, MaritalStatus maritalStatus) {
        super(firstName, lastName, birthDate, email, phone, customerId);
        setSsn(ssn);
        setOccupation(occupation);
        setMaritalStatus(maritalStatus);
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        if (!ssn.matches("^(?!000|666|9\\d{2})\\d{3}-(?!00)\\d{2}-(?!0000)\\d{4}$")) {
            throw new IllegalArgumentException("Use valid SSN format!");
        }
        this.ssn = ssn;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        if (!occupation.matches("^[A-Za-z][A-Za-z' -]{0,49}$")) {
            throw new IllegalArgumentException("Occupation must contain just letters!");
        }
        this.occupation = occupation;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(
            MaritalStatus maritalStatus) {
        if (maritalStatus == null) {
            throw new IllegalArgumentException(
                    "Marital status cannot be null!");
        }
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String toString() {
        return "IndividualCustomer{" +
                "SSN='" + ssn + '\'' +
                ", occupation='" + occupation + '\'' +
                ", maritalStatus=" + maritalStatus +
                "} " + super.toString();
    }

    @Override
    public String getCustomerInfo() {
        String header = String.format(
                "| %-15s | %-20s | %-10s |%n",
                "SSN", "Occupation", "Marital");
        String data = String.format(
                "| %-15s | %-20s | %-10s |",
                ssn, occupation, maritalStatus);
        return header + data;
    }
}
