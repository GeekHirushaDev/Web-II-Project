package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.Brand;
import hibernate.Color;
import hibernate.HibernateUtil;
import hibernate.Model;
import hibernate.Quality;
import hibernate.Status;
import hibernate.Storage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebServlet(name = "LoadProductData", urlPatterns = {"/LoadProductData"})
public class LoadProductData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", false);

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();

        //get brands
        Criteria c1 = s.createCriteria(Brand.class);
        List<Brand> brandList = c1.list();

        //get models
        Criteria c2 = s.createCriteria(Model.class);
        List<Model> modelList = c2.list();

        // get quality
        Criteria c3 = s.createCriteria(Quality.class);
        List<Quality> qualityList = c3.list();

        //get colors
        Criteria c4 = s.createCriteria(Color.class);
        List<Color> colorList = c4.list();

        //get storages
        Criteria c5 = s.createCriteria(Storage.class);
        List<Storage> storageList = c5.list();

        s.close();

        Gson gson = new Gson();

        responseObject.add("brandList", gson.toJsonTree(brandList));  // Java List to JSON Tree
        responseObject.add("modelList", gson.toJsonTree(modelList));
        responseObject.add("qualityList", gson.toJsonTree(qualityList));
        responseObject.add("colorList", gson.toJsonTree(colorList));
        responseObject.add("storageList", gson.toJsonTree(storageList));

        responseObject.addProperty("status", true);

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseObject));

    }

}








































///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import hibernate.Brand;
//import hibernate.Color;
//import hibernate.HibernateUtil;
//import hibernate.Model;
//import hibernate.Quality;
//import hibernate.Status;
//import hibernate.Storage;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import java.util.concurrent.locks.Condition;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
///**
// *
// * @author Workplace
// */
//@WebServlet(name = "LoadProductData", urlPatterns = {"/LoadProductData"})
//public class LoadProductData extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("ok");
//        JsonObject responseObject = new JsonObject();
//        responseObject.addProperty("status", false);
//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        Session s = sf.openSession();
//
//        //get brands
//        Criteria c1 = s.createCriteria(Brand.class);
//        List<Brand> brandList = c1.list();
//
////        for (Brand brand : brandList) {
////            System.out.println(brand.getId());
////            System.out.println(brand.getName());  
////
////        }
//        //get Models
//        Criteria c2 = s.createCriteria(Model.class);
//        List<Model> modelList = c2.list();
//
////        for (Model model : modelList) {
////            System.out.println(model.getId());
////            System.out.println(model.getName());//            
////
////        }
//        //get quality
//        Criteria c3 = s.createCriteria(Quality.class);
//        List<Quality> qualtyList = c3.list();
//
////        for (Quality quality : qualtyList) {
////            System.out.println(quality.getId());
////            System.out.println(quality.getValue());//            
////
////        }
//        //get colors
//        Criteria c4 = s.createCriteria(Color.class);
//        List<Color> colorList = c4.list();
//
////        for (Color color : colorList) {
////            System.out.println(color.getId());
////            System.out.println(color.getValue());
////
////        }
////get storage
//        Criteria c5 = s.createCriteria(Storage.class);
//        List<Storage> storageList = c5.list();
////
////           for (Storage storage : storageList) {
////            System.out.println(storage.getId());
////            System.out.println(storage.getValue());
////
////        }
//
//  
//        s.close();
//        Gson gson = new Gson();
//
//        responseObject.add("brandList", gson.toJsonTree(brandList));
//        responseObject.add("modelList", gson.toJsonTree(modelList));
//        responseObject.add("qualtyList", gson.toJsonTree(qualtyList));
//        responseObject.add("colorList", gson.toJsonTree(colorList));
//        responseObject.add("storageList", gson.toJsonTree(storageList));
//
//        responseObject.addProperty("status", true);
//        response.setContentType("application/json");
//        response.getWriter().write(gson.toJson(responseObject));
//
//    }
//
//}
