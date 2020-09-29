/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.Servlets;

import com.mycompany.proyecto2.Utils.sessionUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user-ubunto
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {  

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = reviewUser(request);
        getServletContext().setAttribute("type", type);
        String pathGUI = "";
        if (type == "admin") {
            pathGUI = "/adminGUI/principalAdmin.jsp";
        } else if (type == "patient") {
            pathGUI = "/patientGUI/principalPatient.jsp";
        } else if (type == "medic") {
            pathGUI = "/medicGUI/principalMedic.jsp";
        } else if (type == "lab") {
            pathGUI = "/labWorkerGUI/principallabWorker.jsp";
        } else if (type == "nulo") {
            pathGUI = "/Login.html";
        }
        RequestDispatcher requestDis;
        requestDis = request.getRequestDispatcher(pathGUI);
        requestDis.forward(request, response);        
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
    
    private String reviewUser(HttpServletRequest request){        
        String code = request.getParameter("username");
        String password = request.getParameter("userPassword");
        sessionUser sesion = new sessionUser(code, password);
        String type = sesion.getTypeUser();
        return type;
    }
}