package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "GetAllUsers", urlPatterns = {"/GetAllUsers"})
public class GetAllUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_5?useSSL=false", "root", "password");
            Statement smt = connection.createStatement();
            
            ResultSet rs = smt.executeQuery("SELECT * FROM users");
            
            ArrayList<User> users = new ArrayList<>();
            
            while (rs.next()) {
                User u = new User();
                u.setFirstname(rs.getString("firstname"));
                u.setLastname(rs.getString("lastname"));
                u.setMobile(rs.getString("mobile"));
                u.setCountry(rs.getString("country"));
                
                users.add(u);
            }
            
            Gson gson = new Gson();
            String toJson = gson.toJson(users);
            response.getWriter().print(toJson);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Data loading failed");
        }

    }

}