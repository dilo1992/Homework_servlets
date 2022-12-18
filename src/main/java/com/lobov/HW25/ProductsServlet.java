package com.lobov.HW25;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ProductsServlet", value = "/Prod")
public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCFeedbacks.connectForDb();
        setAttributesForDisplayRating(request);

        request.getRequestDispatcher("products.jsp").forward(request, response);
    }

    private static void setAttributesForDisplayRating(HttpServletRequest request) {
        request.setAttribute("sizeOfArrM100", JDBCFeedbacks.getFeedbackCountForProductM100());
        request.setAttribute("averageRatingM100", JDBCFeedbacks.getFormattedAverageRatingM100());

        request.setAttribute("sizeOfArrM150", JDBCFeedbacks.getFeedbackCountForProductM150());
        request.setAttribute("averageRatingM150", JDBCFeedbacks.getFormattedAverageRatingM150());

        request.setAttribute("sizeOfArrM200", JDBCFeedbacks.getFeedbackCountForProductM200());
        request.setAttribute("averageRatingM200", JDBCFeedbacks.getFormattedAverageRatingM200());

        request.setAttribute("sizeOfArrM250", JDBCFeedbacks.getFeedbackCountForProductM250());
        request.setAttribute("averageRatingM250", JDBCFeedbacks.getFormattedAverageRatingM250());

        request.setAttribute("sizeOfArrM300", JDBCFeedbacks.getFeedbackCountForProductM300());
        request.setAttribute("averageRatingM300", JDBCFeedbacks.getFormattedAverageRatingM300());

        request.setAttribute("sizeOfArrM350", JDBCFeedbacks.getFeedbackCountForProductM350());
        request.setAttribute("averageRatingM350", JDBCFeedbacks.getFormattedAverageRatingM350());

        request.setAttribute("sizeOfArrM400", JDBCFeedbacks.getFeedbackCountForProductM400());
        request.setAttribute("averageRatingM400", JDBCFeedbacks.getFormattedAverageRatingM400());

        request.setAttribute("sizeOfArrM450", JDBCFeedbacks.getFeedbackCountForProductM450());
        request.setAttribute("averageRatingM450", JDBCFeedbacks.getFormattedAverageRatingM450());

        request.setAttribute("sizeOfArrM500", JDBCFeedbacks.getFeedbackCountForProductM500());
        request.setAttribute("averageRatingM500", JDBCFeedbacks.getFormattedAverageRatingM500());

        request.setAttribute("sizeOfArrF200", JDBCFeedbacks.getFeedbackCountForProductF200());
        request.setAttribute("averageRatingF200", JDBCFeedbacks.getFormattedAverageRatingF200());

        request.setAttribute("sizeOfArrF250", JDBCFeedbacks.getFeedbackCountForProductF250());
        request.setAttribute("averageRatingF250", JDBCFeedbacks.getFormattedAverageRatingF250());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
