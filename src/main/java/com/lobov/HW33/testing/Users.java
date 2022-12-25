package com.lobov.HW33.testing;

import java.time.LocalDate;

public class Users {
    public String name;
    public LocalDate dateOfBirth;

    public Users(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
