/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import hibernate.HibernateUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author User
 */
@WebServlet(name = "DataAdvancedSearch", urlPatterns = {"/DataAdvancedSearch"})
public class DataAdvancedSearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        Criteria c = session.createCriteria(Product.class);
        
        /** Restrictions
            * Restrictions are used to filter results based on specific criteria.
            * They can be used to create complex queries with conditions like equal, not equal, greater than, less than, etc.
         */
        
        // 1st way: equal
        // Criterion crt1 = Restrictions.eq("qty", 40);
        // c.add(crt1);
        
        // 2nd way: less than
        // Criterion crt2 = Restrictions.lt("qty", 20);
        // c.add(crt2);
        
        Criterion crt3 = Restrictions.ilike("name", "a", MatchMode.ANYWHERE);
        c.add(crt3);
        
        List<Product> productList = c.list();
        for (Product product : productList) {
            System.out.println(product.getName());
        }
        
    }
}
