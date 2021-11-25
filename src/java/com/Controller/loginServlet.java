/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controller;

import com.business.Account;
import com.data.AccountDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class loginServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
//        String url = "/login.jsp";
//        
//        String action = request.getParameter("action");
//        if(action == null){
//            action = "login";
//        }
//        if(action.equals("login")){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = AccountDB.selectAccountUserPass(username, password);
        if(account == null){
            request.setAttribute("mess", "Wrong username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", account);
            session.setMaxInactiveInterval(60*60*12);
            //request.getRequestDispatcher("home").forward(request, response);
            response.sendRedirect("home");
        }
//        }
//        else if(action.equals("signup")){
//            String fullname = request.getParameter("fullname");
//            String password = request.getParameter("password");
//            String confirmpassword = request.getParameter("confirmpassword");
//            if(!password.equals(confirmpassword)){
//                response.sendRedirect("login.jsp");
//            } else {
//                Boolean check = AccountDB.accountUserExists(fullname);
//                if(check == true){
//                    response.sendRedirect("login.jsp");
//                } else {
//                    Account acc = new Account();
//                    acc.setUser(fullname);
//                    acc.setPass(password);
//                    acc.setIsSell(0);
//                    acc.setIsAdmin(0);
//                    AccountDB.insert(acc);
//                    response.sendRedirect("home");
//                }
//            }
//        }
        
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
