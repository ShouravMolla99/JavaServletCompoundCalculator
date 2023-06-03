package com.example.calculatorproject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalculateServlet", urlPatterns = {"","/"})
public class CalculateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String principleAmount = request.getParameter("principleAmount");
        String interestPercentage = request.getParameter("interestPercentage");
        String years = request.getParameter("years");
        String compoundingPeriod = request.getParameter("compoundingPeriod");

        String error;

        if (principleAmount == null || principleAmount.isBlank() || interestPercentage == null || interestPercentage.isBlank()
               || years == null || years.isBlank() || compoundingPeriod == null || compoundingPeriod.isBlank()){
            error = "One or more input boxes were blank. Try again.";
            request.setAttribute("error", error);
        }else {
            double result = Utils.calculateCompoundInterest(Double.parseDouble(principleAmount),(Double.parseDouble(interestPercentage) / 100), Integer.parseInt(years), Integer.parseInt(compoundingPeriod));
            request.setAttribute("result", result);

        }
        request.setAttribute("principal",principleAmount);
        request.setAttribute("interest",interestPercentage);
        request.setAttribute("years",years);
        request.setAttribute("compoundingPeriod",compoundingPeriod);

        getServletContext().getRequestDispatcher("index.jsp").forward(request,response);
    }
}