package com.lobov.HW25;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddCommentsServlet", value = "/AddComment")
public class AddCommentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addNewComment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String reviewerName = req.getParameter("newName");
        String typeOfProduct = req.getParameter("newTypeOfProduct");
        String reviewerFeedback = req.getParameter("newFeedback");
        String forRatingOfProductParsing = req.getParameter("newRating");
        int ratingOfProduct = Integer.parseInt(forRatingOfProductParsing);

        if (reviewerName.equals("") || typeOfProduct.equals("null") || reviewerFeedback.equals("") || ratingOfProduct == 0) {
            //req.getSession().setAttribute("completeData", 0);
            req.getRequestDispatcher("/errorAddComment.jsp").forward(req, resp);
        } else {
            //req.getSession().setAttribute("completeData", 1);

            try {
                JDBCFeedbacks.addNewComment(reviewerName, reviewerFeedback, ratingOfProduct, typeOfProduct);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher("/successAddComment.jsp").forward(req, resp);
        }
    }
}
