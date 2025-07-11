/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.Users;
import hibernate.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author User
 */
@WebServlet(name = "CreateNewAccount", urlPatterns = {"/CreateNewAccount"})
public class CreateNewAccount extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        Gson gson = new Gson();
        JsonObject users = gson.fromJson(request.getReader(), JsonObject.class);
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        String mobile = users.get("mobile").getAsString();
        String firstName = users.get("firstname").getAsString();
        String lastName = users.get("lastname").getAsString();
        String password = users.get("password").getAsString();
        String country = users.get("country").getAsString();
        
        Users user = new Users();
        user.setMobile(mobile);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setCountry(country);
        
        session.save(user);
        session.beginTransaction().commit();
        
        response.getWriter().write("Successfully Added");
    }

    
}
