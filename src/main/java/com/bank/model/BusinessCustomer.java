package com.bank.model;

import com.bank.enums.BusinessType;

public class BusinessCustomer extends Customer {
    private String companyName;
    private String taxId;
    private BusinessType businessType;
    private String contactPerson;

    public BusinessCustomer(String firstName, String lastName, String birthDate, String email,
                            String phone, String customerId, String companyName,
                            String taxId, BusinessType businessType, String contactPerson) {
        super(firstName, lastName, birthDate, email, phone, customerId);
        setCompanyName(companyName);
        setTaxId(taxId);
        setBusinessType(businessType);
        setContactPerson(contactPerson);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if (companyName == null
                || companyName.isBlank()) {
            throw new IllegalArgumentException(
                    "Company name cannot be empty!");
        }
        if (!companyName.matches(
                "^[A-Za-z0-9][A-Za-z0-9 &'.,-]{1,99}$")) {
            throw new IllegalArgumentException(
                    "Invalid company name format!");
        }
        this.companyName = companyName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        if (taxId == null || taxId.isBlank()) {
            throw new IllegalArgumentException(
                    "Tax ID cannot be empty!");
        }
        if (!taxId.matches(
                "^\\d{2}-\\d{7}$")) {
            throw new IllegalArgumentException(
                    "Invalid Tax ID format! Use: XX-XXXXXXX");
        }
        this.taxId = taxId;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(
            BusinessType businessType) {
        if (businessType == null) {
            throw new IllegalArgumentException(
                    "Business type cannot be null!");
        }
        this.businessType = businessType;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        if (contactPerson == null
                || contactPerson.isBlank()) {
            throw new IllegalArgumentException(
                    "Contact person cannot be empty!");
        }
        if (!contactPerson.matches(
                "^[A-Z][A-Za-z' -]{1,49}$")) {
            throw new IllegalArgumentException(
                    "Contact person must start with " +
                            "capital letter, letters only!");
        }
        this.contactPerson = contactPerson;
    }

    @Override
    public String toString() {
        return String.format("%-12s|%-20s|%-11s|%-20s|%-12s|%-11s|%-13s|%-13s|%-12s|%-14s|%-20s|%-11s|%-6s",
                getCustomerId(), getFullName(), getBirthDate(), getEmail(), getPhone(), getRegistrationDate(),
                " ", getCompanyName(), getTaxId(), getBusinessType(),
                getContactPerson(), getClosureDate(), isActive());
    }

    @Override
    public String getCustomerInfo() {
        String header = String.format(
                "| %-20s | %-15s | %-15s | %-20s |%n",
                "Company", "Tax ID",
                "Type", "Contact");
        String data = String.format(
                "| %-20s | %-15s | %-15s | %-20s |",
                companyName, taxId,
                businessType, contactPerson);
        return header + data;
    }
}
