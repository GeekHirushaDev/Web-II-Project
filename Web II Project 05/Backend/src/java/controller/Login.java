/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");

//        Json -> Java
        Gson gson = new Gson();
        User user = gson.fromJson(request.getReader(), User.class);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/web_5?useSSL=false", "root", "password");
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM users WHERE mobile = '" + user.getMobile() + "' && password = '" + user.getPassword() + "'");
            if (rs.next()) {
                User u = new User();
                u.setFirstname(rs.getString("firstname"));
                u.setLastname(rs.getString("lastname"));
                u.setMobile(rs.getString("mobile"));
                u.setCountry(rs.getString("country"));

                request.getSession().setAttribute("user", u);
                response.getWriter().write("Success");
                response.setContentType("application/json");
            } else {
                response.getWriter().write("Invalid mobile number or password");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }

    }
}



