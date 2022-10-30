package main.HW23;

import java.sql.SQLException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < StudentListInArray.showStudentList().size(); i++) {
                System.out.println(StudentListInArray.showStudentList().get(i));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
