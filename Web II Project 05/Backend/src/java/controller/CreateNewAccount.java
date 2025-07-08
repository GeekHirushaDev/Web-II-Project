/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "CreateNewAccount", urlPatterns = {"/CreateNewAccount"})
public class CreateNewAccount extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");

//        Json -> Java
        Gson gson = new Gson();
        User user = gson.fromJson(request.getReader(), User.class);

        // System.out.println(user.getMobile());
        // System.out.println(user.getFirstname());
        // System.out.println(user.getLastname());
        // System.out.println(user.getPassword());
        // System.out.println(user.getCountry());
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/web_5?useSSL=false", "root", "password");
            Statement s = c.createStatement();
            s.executeUpdate("INSERT INTO "
                    + "users (mobile, firstname, lastname, password, country) "
                    + "VALUES ('" + user.getMobile() + "', '" + user.getFirstname() + "', '" + user.getLastname() + "', '" + user.getPassword() + "', '" + user.getCountry() + "')");
            response.getWriter().write("Success");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }

    }
}
