/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.HibernateUtil;
import hibernate.User;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Mail;
import model.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author User
 */
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        JsonObject user = gson.fromJson(requset.getReader(), JsonObject.class);

        String firstName = user.get("firstName").getAsString();
        String lastName = user.get("lastName").getAsString();
        final String email = user.get("email").getAsString();
        String password = user.get("password").getAsString();

        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", false);

        if (firstName.isEmpty()) {
            responseObject.addProperty("message", "First Name can not be empty!");
        } else if (lastName.isEmpty()) {
            responseObject.addProperty("message", "last Name can not be empty!");
        } else if (email.isEmpty()) {
            responseObject.addProperty("message", "Email can not be empty!");
        } else if (!Util.isEmailValid(email)) {
            responseObject.addProperty("message", "Please enter a valid email!");
        } else if (!Util.isPasswordValid(password)) {
            responseObject.addProperty("message", "Password must contain at least one uppercase letter, "
                    + "one lowercase letter, "
                    + "one number, one special character, "
                    + "and be at least eight characters long.");
        } else {

            Session session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));

            if (!criteria.list().isEmpty()) {
                responseObject.addProperty("message", "User with this email already exists!");
            } else {

                User u = new User();
                u.setFirst_name(firstName);
                u.setLast_name(lastName);
                u.setEmail(email);
                u.setPassword(password);

                //generate verification code
                final String verificationCode = Util.generateCode();
                u.setVerification(verificationCode);
                //generate verification code

                u.setCreated_at(new Date());

                session.save(u);
                session.beginTransaction().commit();
                // hibernate save

                //send email
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Mail.sendMail(email, "SmartTrade Verification", "<h1>" + verificationCode + "</h1>");
                    }
                }).start();
                // send email
            }
        }
//        String responseText = gson.toJson(responseObject);
//        response.setContentType("application/json");
//        response.getWriter().write(responseText);
    }

}
