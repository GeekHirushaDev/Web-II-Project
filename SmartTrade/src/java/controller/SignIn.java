package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.HibernateUtil;
import hibernate.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dilhara
 */
@WebServlet(name = "SignIn", urlPatterns = {"/SignIn"})
public class SignIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        JsonObject user = gson.fromJson(request.getReader(), JsonObject.class);
        String email = user.get("email").getAsString();
        String password = user.get("password").getAsString();

        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", false);

        if (email.isEmpty()) {
            responseObject.addProperty("message", "Email can not be empty!");
        } else if (!Util.isEmailValid(email)) {
            responseObject.addProperty("message", "Please enter a valid email!");
        } else if (password.isEmpty()) {
            responseObject.addProperty("message", "Password can not be empty!");
        } else if (!Util.isPasswordValid(password)) {
            responseObject.addProperty("message", "The password must contains at least uppercase, lowecase,"
                    + " number, special character and to be minimum eight characters long!");
        } else {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session s = sessionFactory.openSession();

            Criteria c = s.createCriteria(User.class);

            Criterion ctr1 = Restrictions.eq("email", email);
            Criterion ctr2 = Restrictions.eq("password", password);
            c.add(ctr1);
            c.add(ctr2);

            if (c.list().isEmpty()) {
                responseObject.addProperty("message", "Invalid credentials!");
            } else {
                User u = (User) c.list().get(0);
                HttpSession ses = request.getSession();
                responseObject.addProperty("status", true);

                if (!u.getVerification().equals("Verified")) { //Not Verified
                    //session-management
                    ses.setAttribute("email", email);
                    //session-management-end

                    responseObject.addProperty("message", "1");
                } else { // Veriified
                    ses.setAttribute("user", u);
                    responseObject.addProperty("message", "2");
                }
            }
            s.close();
        }
        String toJson = gson.toJson(responseObject);
        response.setContentType("application/json");
        response.getWriter().write(toJson);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JsonObject responseObject = new JsonObject();

        if (request.getSession().getAttribute("user") != null) {
//            String email = request.getSession().getAttribute("email").toString();
//            SessionFactory sf = HibernateUtil.getSessionFactory();
//            Session s = sf.openSession();
//            Criteria c = s.createCriteria(User.class);
//            c.add(Restrictions.eq("email", email));
//            
//            if(!c.list().isEmpty()){
//             
//            }
//            responseObject.addProperty("status", true);
            responseObject.addProperty("message", "1");
        } else {
//            responseObject.addProperty("status", false);
            responseObject.addProperty("message", "2");
        }

        Gson gson = new Gson();
        String toJson = gson.toJson(responseObject);
        response.setContentType("application/json");
        response.getWriter().write(toJson);
    }

}
