package com.lobov.HW34;

import java.sql.*;

public class JDBCHW34 {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String SQL_SELECT_ALL_PERSON = "SELECT * FROM employees";
    private static final String CONNECT_URL = "jdbc:mysql://localhost:3306/testbase?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "PassSQL1286";

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(CONNECT_URL, USER, PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL_PERSON)) {
                conn.setAutoCommit(false);
                firstExercise(ps);
                secondExercise(ps);
                thirdExercise(ps);
                conn.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private static void thirdExercise(PreparedStatement ps) throws SQLException {
        System.out.println("-------EXERCISE 3-----------");
        ResultSet rsExerciseThree = ps.executeQuery("select department_id, sum(salary) as sum_salary from employees where department_id = 3;");
        while (rsExerciseThree.next()) {
            System.out.println(rsExerciseThree.getInt("department_id") + " " + rsExerciseThree.getInt("sum_salary"));
        }
    }

    private static void secondExercise(PreparedStatement ps) throws SQLException {
        System.out.println("-------EXERCISE 2-----------");
        ResultSet rsExerciseTwo = ps.executeQuery("select department_id, name, salary from employees a where salary = (select min(salary) from employees b where a.department_id = b.department_id);");
        while (rsExerciseTwo.next()) {
            System.out.println(rsExerciseTwo.getInt("department_id") + " " + rsExerciseTwo.getString("name") + " " + rsExerciseTwo.getInt("salary"));
        }
    }

    private static void firstExercise(PreparedStatement ps) throws SQLException {
        System.out.println("-------EXERCISE 1-----------");
        ResultSet rsExerciseOne = ps.executeQuery("select * from employees where salary = (select min(salary) from employees);");
        while (rsExerciseOne.next()) {
            System.out.println(rsExerciseOne.getString("name") + " " + rsExerciseOne.getInt("salary"));
        }
    }
}
