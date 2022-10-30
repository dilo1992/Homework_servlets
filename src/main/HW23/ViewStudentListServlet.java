package main.HW23;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/StudentList")
public class ViewStudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        try {
            ArrayList<String> student = StudentListInArray.showStudentList();
            String s = student.get(0);
//            for (int i = 0; i < student.size(); i++) {
//                writer.println(student.get(i) + "<br/>");
//            }
            writer.println(s+"<br/>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
