package com.lobov;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final String ValidLogin = "admin";
    private final String ValidPassword = "admin";
//

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext();
        req.getSession();
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.equals(ValidLogin) && password.equals(ValidPassword)) {
            req.getSession().getServletContext().setAttribute("login", login);
        }
        resp.sendRedirect("/success.html");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
