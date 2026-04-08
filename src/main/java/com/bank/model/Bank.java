package com.bank.model;

public class Bank {
        private String bankId = "B-000001";
        private String name;
        private String address;
        private String phone;
        private String webUrl;

    public Bank(String name, String address, String phone, String webUrl) {
        setName(name);
        setAddress(address);
        setPhone(phone);
        setWebUrl(webUrl);
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Bank name cannot be empty!");
        }
        if (!name.matches(
                "^[A-Z][A-Za-z &.'-]{1,99}$")) {
            throw new IllegalArgumentException(
                    "Invalid bank name! Must start with " +
                            "capital letter, letters only!");
        }
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException(
                    "Address cannot be empty!");
        }
        if (!address.matches(
                "^[0-9A-Za-z][0-9A-Za-z .,#-]{1,99}$")) {
            throw new IllegalArgumentException(
                    "Invalid address format!");
        }
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!phone.matches("^(\\+1[\\s-]?)?\\(?[2-9][0-9]{2}\\)?[\\s-]?" +
                "[2-9][0-9]{2}[\\s-]?[0-9]{4}$")) {
            throw new IllegalArgumentException("Provide valid phone format!");
        }
        this.phone = phone.replaceAll("\\D", "");
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        if (webUrl == null || webUrl.isBlank()) {
            throw new IllegalArgumentException(
                    "Web URL cannot be empty!");
        }
        if (!webUrl.matches(
                "^(https?://)?(www\\.)?[A-Za-z0-9-]+" +
                        "\\.[A-Za-z]{2,}(/.*)?$")) {
            throw new IllegalArgumentException(
                    "Invalid URL format!");
        }
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankId='" + bankId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}
