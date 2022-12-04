package com.lobov;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterServlet extends HttpServlet {
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession();
        counter.getAndIncrement();
        PrintWriter writer = resp.getWriter();
        writer.println("This page has been visited " + counter + " time(s)");
    }
}
