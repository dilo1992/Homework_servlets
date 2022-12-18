package com.lobov.HW25;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CommentsServlet", value = "/comments")
public class CommentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JDBCFeedbacks.connectForDb();
        String typeOfProductForDisplayComment = req.getParameter("typeOfProductForDisplayComment");

        List<Comment> commentsForProduct = JDBCFeedbacks.displayComments(typeOfProductForDisplayComment);
        req.setAttribute("typeOfProductForDisplayCommentAttribute", typeOfProductForDisplayComment);
        req.setAttribute("commentsForProduct", commentsForProduct);
        System.out.println(commentsForProduct);
        req.getRequestDispatcher("/comments.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
