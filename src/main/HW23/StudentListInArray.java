package main.HW23;

import java.sql.*;
import java.util.ArrayList;

public class StudentListInArray {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/studentList_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "PassSQL1286";


    public static ArrayList<String> showStudentList() throws SQLException, ClassNotFoundException {

        ArrayList<Student> studentArrayList=new ArrayList<>();
        ArrayList <String> strings = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            while (resultSet.next()) {
                studentArrayList.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("firstName"), resultSet.getString("lastName")));
            strings.add(resultSet.getInt("id")+"\t"+
                    resultSet.getString("firstName")+"\t\t"+ resultSet.getString("lastName"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //return studentArrayList;
        return strings;
    }
}

