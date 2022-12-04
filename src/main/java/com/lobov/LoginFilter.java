package com.lobov;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class LoginFilter implements Filter {

    private final String ValidLogin = "admin";
    private final String ValidPassword = "admin";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession(false);
        String login = (String) session.getAttribute("login");
        if (login != null && session != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("error.html").forward(servletRequest, servletResponse);
        }
    }
}
