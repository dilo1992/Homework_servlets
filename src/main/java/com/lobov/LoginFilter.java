package com.lobov;

import jakarta.servlet.*;

import java.io.IOException;


public class LoginFilter implements Filter {

    private final String ValidLogin = "admin";
    private final String ValidPassword = "admin";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String login = (String) servletRequest.getServletContext().getAttribute("login");
        if (login != null) {
            servletRequest.getRequestDispatcher("success.html").forward(servletRequest, servletResponse);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("error.html").forward(servletRequest, servletResponse);
        }
    }
}
