package com.lobov;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewStudentListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //req.getRequestDispatcher("HW23.jsp").forward(req,resp);
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        try {
            ArrayList<String> student = StudentListInArray.showStudentList();

            for (int i = 0; i < student.size(); i++) {
                String s = student.get(i);
                writer.println(s + "</br>");
            }
//            writer.println(s+"<br/>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
