package com.swati;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DiscountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Handles POST request
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String pname = request.getParameter("pname");
        String priceStr = request.getParameter("price");
        String discountStr = request.getParameter("discount");

        // Validation
        if (pname == null || pname.isEmpty() ||
            priceStr == null || priceStr.isEmpty() ||
            discountStr == null || discountStr.isEmpty()) {

            out.println("<h3 style='color:red;'>All fields are required!</h3>");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            double discount = Double.parseDouble(discountStr);

            double discountAmount = (price * discount) / 100;
            double finalPrice = price - discountAmount;

            out.println("<html><body>");
            out.println("<h2>Discount Price Result</h2>");
            out.println("Product Name: " + pname + "<br><br>");
            out.println("Original Price: ₹" + price + "<br><br>");
            out.println("Discount: " + discount + "%<br><br>");
            out.println("Discount Amount: ₹" + discountAmount + "<br><br>");
            out.println("<b>Final Price: ₹" + finalPrice + "</b>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            out.println("<h3 style='color:red;'>Please enter valid numeric values!</h3>");
        }
    }

    // Handles GET request (optional)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}