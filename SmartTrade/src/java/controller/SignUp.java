package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.HibernateUtil;
import hibernate.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mail;
import model.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        JsonObject user = gson.fromJson(request.getReader(), JsonObject.class);

        String firstName = user.get("firstName").getAsString();
        String lastName = user.get("lastName").getAsString();
        final String email = user.get("email").getAsString();
        String password = user.get("password").getAsString();

        JsonObject resJsonObject = new JsonObject();
        resJsonObject.addProperty("status", false);
        if (firstName.isEmpty()) {
            resJsonObject.addProperty("message", "First Name can not be empty");
        } else if (lastName.isEmpty()) {
            resJsonObject.addProperty("message", "Last Name can not be empty");
        } else if (email.isEmpty()) {
            resJsonObject.addProperty("message", "Email can not be empty");
        } else if (!Util.isEmailValid(email)) {
            resJsonObject.addProperty("message", "Please enter a valid email!");
        } else if (password.isEmpty()) {
            resJsonObject.addProperty("message", "Password can not be empty");
        } else if (!Util.isPasswordValid(password)) {
            resJsonObject.addProperty("message", "The password must constaint at least uppercase, lowercase,"
                    + "number,special characters and to be minimum eight charaters long!");
        } else {

            //hibernate save
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));

            if (!criteria.list().isEmpty()) {
                resJsonObject.addProperty("message", "User with this Email already exists");
            } else {
                User u = new User();
                u.setFirst_name(firstName);
                u.setLast_name(lastName);
                u.setEmail(email);
                u.setPassword(password);

                //generate verification code
                final String verificationCode = Util.generateCode();
                u.setVerification(verificationCode);
                u.setCreated_at(new Date());

                session.save(u);
                session.beginTransaction().commit();

                //hibernate save
                //send email
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Mail.sendMail(email, "SmartTrade - Verification", "<h1>" + verificationCode + "</h1>");
                    }
                }).start();
                //send email 
                
                 //Session management
                 HttpSession ses = request.getSession();
                 ses.setAttribute("email", email);
                  //Session management end

                resJsonObject.addProperty("status", true);
                resJsonObject.addProperty("message", "Registration success!.Please check your email for the verification code");

                //send email
            }
            session.close();
        }
        String responseText = gson.toJson(resJsonObject);
        response.setContentType("application/json");
        response.getWriter().write(responseText);
    }

}
