package com.lobov.HW25;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@Data
public class JDBCFeedbacks {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/feedbacks_for_arso_db";
    private static String login;
    private static String password;
    public static final DecimalFormat df = new DecimalFormat("#.#");

    private static String formattedAverageRatingM100;
    private static String formattedAverageRatingM150;
    private static String formattedAverageRatingM200;
    private static String formattedAverageRatingM250;
    private static String formattedAverageRatingM300;
    private static String formattedAverageRatingM350;
    private static String formattedAverageRatingM400;
    private static String formattedAverageRatingM450;
    private static String formattedAverageRatingM500;
    private static String formattedAverageRatingF200;
    private static String formattedAverageRatingF250;
    private static int feedbackCountForProductM100;
    private static int feedbackCountForProductM150;
    private static int feedbackCountForProductM200;
    private static int feedbackCountForProductM250;
    private static int feedbackCountForProductM300;
    private static int feedbackCountForProductM350;
    private static int feedbackCountForProductM400;
    private static int feedbackCountForProductM450;
    private static int feedbackCountForProductM500;
    private static int feedbackCountForProductF200;
    private static int feedbackCountForProductF250;


    public static void connectForDb() {


        initParamForConnectionToSQL();

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }

        try (Connection connection = DriverManager.getConnection(URL, login, password);
             Statement statement = connection.createStatement()) {
            firstInitDatabase(statement);

            initParamForFeedbackForProductM100(statement);
            initParamForFeedbackForProductM150(statement);
            initParamForFeedbackForProductM200(statement);
            initParamForFeedbackForProductM250(statement);
            initParamForFeedbackForProductM300(statement);
            initParamForFeedbackForProductM350(statement);
            initParamForFeedbackForProductM400(statement);
            initParamForFeedbackForProductM450(statement);
            initParamForFeedbackForProductM500(statement);
            initParamForFeedbackForProductF200(statement);
            initParamForFeedbackForProductF250(statement);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }

    private static void initParamForConnectionToSQL() {
        try (FileReader fileReader = new FileReader("/Users/dmitrylobov/Documents/TeachMeSkills/Lesson_25 Bootstrap/File with login and pass for the database HW25/For a database HW25.txt")) {
            Scanner scanner = new Scanner(fileReader);
            List<String> paramForAccessDb = new ArrayList<>();
            while (scanner.hasNextLine()) {
                paramForAccessDb.add(scanner.nextLine());
            }
            login = paramForAccessDb.get(0);
            password = paramForAccessDb.get(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void firstInitDatabase(Statement statement) throws SQLException {
        statement.executeUpdate("create database if not exists feedbacks_for_arso_db");
        statement.executeUpdate("use feedbacks_for_arso_db");
        statement.executeUpdate("create table if not exists feedbacks (id integer primary key auto_increment not null, " +
                "name varchar(30) not null, feedback varchar(2000) not null, rating integer not null DEFAULT 5, " +
                "type_of_product varchar(30) not null, CONSTRAINT check_type_of_product CHECK (type_of_product " +
                "IN ('M100', 'M150', 'M200', 'M250', 'M300', 'M350', 'M400', 'M450', 'M500', 'F200', 'F250')), " +
                "CONSTRAINT check_rating CHECK (rating>0 and rating<6), CONSTRAINT cn_unique UNIQUE (name))");
        statement.executeQuery("select * from feedbacks");
    }


    public static List<Comment> displayComments(String typeOfProduct) {
        connectForDb();
        List<Comment> temporaryArrayForPrintComment = new ArrayList<>();
        try {
            Connection connectionForDisplayCommentsMethod = DriverManager.getConnection(URL, "root", "PassSQL1286");
            Statement statementForDisplayCommentsMethod = connectionForDisplayCommentsMethod.createStatement();
            String commandDisplayComment = "select * from feedbacks where type_of_product='" + typeOfProduct + "'";
            ResultSet resultSet = statementForDisplayCommentsMethod.executeQuery(commandDisplayComment);
            while (resultSet.next()) {
                temporaryArrayForPrintComment.add(new Comment(resultSet.getString("name"), typeOfProduct, resultSet.getString("feedback"), resultSet.getInt("rating")));
            }
            return temporaryArrayForPrintComment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addNewComment(String newName, String newFeedback, int newRating, String typeOfProduct) throws SQLException {
        connectForDb();
        try {
            Connection connectionForAddNewCommentMethod = DriverManager.getConnection(URL, "root", "PassSQL1286");
            Statement statementForAddNewCommentMethod = connectionForAddNewCommentMethod.createStatement();
            String commandAddNewComment = "insert into feedbacks (name, feedback, rating, type_of_product) values ('" + newName + "','"
                    + newFeedback + "'," + newRating + ",'" + typeOfProduct + "');";
            statementForAddNewCommentMethod.executeUpdate(commandAddNewComment);
            connectionForAddNewCommentMethod.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initParamForFeedbackForProductM100(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='M100'");
        resultSet.next();
        feedbackCountForProductM100 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='M100'");
        resultSetForAverageRating.next();
        formattedAverageRatingM100 = df.format(resultSetForAverageRating.getDouble("avg"));
    }

    private static void initParamForFeedbackForProductM150(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='M150'");
        resultSet.next();
        feedbackCountForProductM150 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='M150'");
        resultSetForAverageRating.next();
        formattedAverageRatingM150 = df.format(resultSetForAverageRating.getDouble("avg"));
    }

    private static void initParamForFeedbackForProductM200(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='M200'");
        resultSet.next();
        feedbackCountForProductM200 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='M200'");
        resultSetForAverageRating.next();
        formattedAverageRatingM200 = df.format(resultSetForAverageRating.getDouble("avg"));
    }

    private static void initParamForFeedbackForProductM250(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='M250'");
        resultSet.next();
        feedbackCountForProductM250 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='M250'");
        resultSetForAverageRating.next();
        formattedAverageRatingM250 = df.format(resultSetForAverageRating.getDouble("avg"));
    }

    private static void initParamForFeedbackForProductM300(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='M300'");
        resultSet.next();
        feedbackCountForProductM300 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='M300'");
        resultSetForAverageRating.next();
        formattedAverageRatingM300 = df.format(resultSetForAverageRating.getDouble("avg"));
    }

    private static void initParamForFeedbackForProductM350(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='M350'");
        resultSet.next();
        feedbackCountForProductM350 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='M350'");
        resultSetForAverageRating.next();
        formattedAverageRatingM350 = df.format(resultSetForAverageRating.getDouble("avg"));
    }

    private static void initParamForFeedbackForProductM400(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='M400'");
        resultSet.next();
        feedbackCountForProductM400 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='M400'");
        resultSetForAverageRating.next();
        formattedAverageRatingM400 = df.format(resultSetForAverageRating.getDouble("avg"));
    }

    private static void initParamForFeedbackForProductM450(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='M450'");
        resultSet.next();
        feedbackCountForProductM450 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='M450'");
        resultSetForAverageRating.next();
        formattedAverageRatingM450 = df.format(resultSetForAverageRating.getDouble("avg"));
    }

    private static void initParamForFeedbackForProductM500(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='M500'");
        resultSet.next();
        feedbackCountForProductM500 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='M500'");
        resultSetForAverageRating.next();
        formattedAverageRatingM500 = df.format(resultSetForAverageRating.getDouble("avg"));
    }

    private static void initParamForFeedbackForProductF200(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='F200'");
        resultSet.next();
        feedbackCountForProductF200 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='F200'");
        resultSetForAverageRating.next();
        formattedAverageRatingF200 = df.format(resultSetForAverageRating.getDouble("avg"));
    }

    private static void initParamForFeedbackForProductF250(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM feedbacks WHERE type_of_product='F250'");
        resultSet.next();
        feedbackCountForProductF250 = resultSet.getInt("count");
        ResultSet resultSetForAverageRating = statement.executeQuery("SELECT AVG(rating) as avg FROM feedbacks WHERE type_of_product='F250'");
        resultSetForAverageRating.next();
        formattedAverageRatingF250 = df.format(resultSetForAverageRating.getDouble("avg"));
    }


    public static String getFormattedAverageRatingM100() {
        return formattedAverageRatingM100;
    }

    public static int getFeedbackCountForProductM100() {
        return feedbackCountForProductM100;
    }

    public static int getFeedbackCountForProductM150() {
        return feedbackCountForProductM150;
    }

    public static String getFormattedAverageRatingM150() {
        return formattedAverageRatingM150;
    }

    public static String getFormattedAverageRatingM200() {
        return formattedAverageRatingM200;
    }

    public static String getFormattedAverageRatingM250() {
        return formattedAverageRatingM250;
    }

    public static String getFormattedAverageRatingM300() {
        return formattedAverageRatingM300;
    }

    public static String getFormattedAverageRatingM350() {
        return formattedAverageRatingM350;
    }

    public static String getFormattedAverageRatingM400() {
        return formattedAverageRatingM400;
    }

    public static String getFormattedAverageRatingM450() {
        return formattedAverageRatingM450;
    }

    public static String getFormattedAverageRatingM500() {
        return formattedAverageRatingM500;
    }

    public static String getFormattedAverageRatingF200() {
        return formattedAverageRatingF200;
    }

    public static String getFormattedAverageRatingF250() {
        return formattedAverageRatingF250;
    }

    public static int getFeedbackCountForProductM200() {
        return feedbackCountForProductM200;
    }

    public static int getFeedbackCountForProductM250() {
        return feedbackCountForProductM250;
    }

    public static int getFeedbackCountForProductM300() {
        return feedbackCountForProductM300;
    }

    public static int getFeedbackCountForProductM350() {
        return feedbackCountForProductM350;
    }

    public static int getFeedbackCountForProductM400() {
        return feedbackCountForProductM400;
    }

    public static int getFeedbackCountForProductM450() {
        return feedbackCountForProductM450;
    }

    public static int getFeedbackCountForProductM500() {
        return feedbackCountForProductM500;
    }

    public static int getFeedbackCountForProductF200() {
        return feedbackCountForProductF200;
    }

    public static int getFeedbackCountForProductF250() {
        return feedbackCountForProductF250;
    }

}
