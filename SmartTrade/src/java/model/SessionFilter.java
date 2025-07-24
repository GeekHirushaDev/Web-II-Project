/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dilhara
 */
@WebFilter(urlPatterns = {"/sign-up.html", "/verify-account.html", "/sign-in.html"})
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession s = req.getSession(false);

//        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//        res.setHeader("Pragma", "no-cache");
//        res.setHeader("Expires", "0");
        
        if (s != null && s.getAttribute("user") != null) {
            res.sendRedirect("index.html");
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
    }

}
