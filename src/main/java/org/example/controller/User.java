package org.example.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


public class User extends HttpServlet {

        // Handles GET requests
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // Set response content type
            response.setContentType("text/html");

            // Get a PrintWriter object to write the response
            PrintWriter out = response.getWriter();

            // Output HTML for a simple form
            out.println("<html><body>");
            out.println("<h2>Hello Servlet</h2>");
            out.println("<form method='POST' action='/yourAppName/hello'>");
            out.println("Enter your name: <input type='text' name='name'><br>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");
            out.println("</body></html>");
        }

        // Handles POST requests
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // Set response content type
            response.setContentType("text/html");

            // Get the parameter from the form (the "name" field)
            String name = request.getParameter("name");

            // Get a PrintWriter object to write the response
            PrintWriter out = response.getWriter();

            // Output a response that includes the submitted name
            out.println("<html><body>");
            out.println("<h2>Hello, " + name + "!</h2>");
            out.println("</body></html>");
        }
    }


