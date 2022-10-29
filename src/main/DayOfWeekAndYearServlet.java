package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/DayOfWeek")
public class DayOfWeekAndYearServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String enteredDate = req.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(enteredDate, formatter);
        PrintWriter writer = resp.getWriter();
        writer.println("You are enter date: " + date.format(formatter));
        writer.println("\nEntered date is a day of the year: " + date.getDayOfYear());
        writer.println("\nEntered date is a day of the week: " + date.getDayOfWeek());
        writer.close();
    }
}
