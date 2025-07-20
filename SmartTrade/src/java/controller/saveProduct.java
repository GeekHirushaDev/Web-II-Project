package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Workplace
 */
@WebServlet(name = "saveProduct", urlPatterns = {"/saveProduct"})
public class saveProduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String brandId = request.getParameter("brandId");
        String modelId = request.getParameter("modelId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String storageId = request.getParameter("storageId");
        String colorId = request.getParameter("colorId");
        String conditionId = request.getParameter("conditionId");
        String price = request.getParameter("price");
        String qty = request.getParameter("qty");

        System.out.println(brandId);
        System.out.println(modelId);
        System.out.println(title);
        System.out.println(description);
        System.out.println(storageId);
        System.out.println(colorId);
        System.out.println(conditionId);
        System.out.println(price);
        System.out.println(qty);
    }

}
