package com.lobov;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return id + "\t" + firstName + "\t\t" + lastName;
    }
}
