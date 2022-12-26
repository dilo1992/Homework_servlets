package com.lobov.HW34;

import java.sql.*;

public class JDBCHW34 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sqlSelectAllPerson = "SELECT * FROM employees";
            String connectUrl = "jdbc:mysql://localhost:3306/testbase?serverTimezone=UTC";
            try (Connection conn = DriverManager.getConnection(connectUrl, "root", "PassSQL1286");
                 PreparedStatement ps = conn.prepareStatement(sqlSelectAllPerson)) {
                conn.setAutoCommit(false);
                System.out.println("-------EXERCISE 1-----------");
                ResultSet rsExerciseOne = ps.executeQuery("select * from employees where salary = (select min(salary) from employees);");
                while (rsExerciseOne.next()) {
                    System.out.println(rsExerciseOne.getString("name") + " " + rsExerciseOne.getInt("salary"));
                }
                System.out.println("-------EXERCISE 2-----------");
                ResultSet rsExerciseTwo = ps.executeQuery("select department_id, name, salary from employees a where salary = (select min(salary) from employees b where a.department_id = b.department_id);");
                while (rsExerciseTwo.next()) {
                    System.out.println(rsExerciseTwo.getInt("department_id") + " " + rsExerciseTwo.getString("name") + " " + rsExerciseTwo.getInt("salary"));
                }
                System.out.println("-------EXERCISE 3-----------");
                ResultSet rsExerciseThree = ps.executeQuery("select department_id, sum(salary) as sum_salary from employees where department_id = 3;");
                while (rsExerciseThree.next()) {
                    System.out.println(rsExerciseThree.getInt("department_id") + " " + rsExerciseThree.getInt("sum_salary"));
                }
                conn.commit();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
