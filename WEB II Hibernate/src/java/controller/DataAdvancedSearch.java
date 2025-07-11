/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Brand;
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
import org.hibernate.criterion.Order;
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

        Criteria c1 = session.createCriteria(Product.class);
        Criteria c2 = session.createCriteria(Brand.class);
        

        /**
         * Restrictions Restrictions are used to filter results based on
         * specific criteria. They can be used to create complex queries with
         * conditions like equal, not equal, greater than, less than, etc.
         */
        
        // 1st way: equal
        // Criterion crt1 = Restrictions.eq("qty", 40);
        
        // 2nd way: less than
        // Criterion crt2 = Restrictions.lt("qty", 20);
        // Order ord1 = Order.asc("name"); // Order is not restriction, but used to sort results
        
        // 3rd way: greater than
        // Criterion crt3 = Restrictions.gt("qty", 20);
        // Order ord2 = Order.desc("name");
        
        // 4th way: not equal
        // Criterion crt4 = Restrictions.ne("qty", 20);
        
        // 5th way: like (case-insensitive)
        // Criterion crt5 = Restrictions.like("name", "a", MatchMode.ANYWHERE);
        
        // 6th way: like (case-sensitive)
        // Criterion crt6 = Restrictions.ilike("name", "a", MatchMode.ANYWHERE);

        // 7th way: equal to a specific brand name
        Criterion crt7 = Restrictions.eq("name", "Apple");        
        Brand brandNameSelect = (Brand) c2.list().get(0); 

        // Brand brandNameSelect = (Brand)c2.uniqueResult();     // Assuming you want to select the first brand from the list
        // Brand brandNameSelect = (Brand) session.load(Brand.class, 1); // Load a specific brand by ID
        
        // 8th way: equal to a specific brand name (using the brandNameSelect object)
        Criterion crt8 = Restrictions.eq("brand", brandNameSelect);
        
        // 9th way: less than or equal to a specific price
        Criterion crt9 = Restrictions.le("price", 100000.0);
        Order ord3 = Order.desc("price");

        // c.add(crt1);
        // c.add(crt2);
        // c.addOrder(ord1);
        // c.add(crt3);
        // c.addOrder(ord2);
        // c.add(crt4);
        // c.add(crt5);
        // c.add(crt6); 
        
        c2.add(crt7);   
        c1.add(crt8);
        c1.add(crt9);
        c1.addOrder(ord3);

        List<Product> productList = c1.list();
        for (Product product : productList) {
            System.out.println(product.getName());
        }

    }
}
