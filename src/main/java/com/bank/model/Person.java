package com.bank.model;


import java.time.LocalDate;
import java.time.Period;

public abstract class Person {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String phone;
    private boolean isActive = true;

    protected Person(String firstName, String lastName, String birthDate,
                  String email, String phone) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setEmail(email);
        setPhone(phone);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (!firstName.matches("^[A-Z][A-Za-z' -]{0,49}$")) {
            throw new IllegalArgumentException("Enter valid firstName format");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!lastName.matches("^[A-Z][A-Za-z' -]{0,49}$")) {
            throw new IllegalArgumentException("Enter valid lastName format");
        }
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if(!birthDate.matches(  "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {
            throw new IllegalArgumentException("Date should be in ISO-8601 format");
        }
        String[] dobArray = birthDate.split("-"); // create array of strings to pass them in to LocalDate
        LocalDate dob = LocalDate.of(Integer.parseInt(dobArray[0]),
                Integer.parseInt(dobArray[1]), Integer.parseInt(dobArray[2]));
        LocalDate today = LocalDate.now();
        if (dob.isAfter(today)) {
            throw new IllegalArgumentException("Birth date cannot be in the future!");
        }
        if ((Period.between(dob, today)).getYears() > 120) {
            throw new IllegalArgumentException("Age cannot exceed 120 years!");
        }
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Provide valid email format!");
        }
        this.email = email;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
