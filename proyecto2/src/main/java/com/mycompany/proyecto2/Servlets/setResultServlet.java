/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.Servlets;

import com.mycompany.proyecto2.LabWorkerControlers.LabWorkerDB;
import com.mycompany.proyecto2.PatientControlers.PatientDB;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author user-ubunto
 */
@WebServlet(name = "setResultServlet", urlPatterns = {"/setResultServlet"})
public class setResultServlet extends HttpServlet {
    

    private static final String UPLOAD_DIRECTORY = "upload";
 
    
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
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
        if (!ServletFileUpload.isMultipartContent(request)) {
		    PrintWriter writer = response.getWriter();
		    writer.println("Error: enctype=multipart/form-data");
		    writer.flush();
		    return;
		}

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);         
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
       
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                File storeFile = null;
                String codeResult = "";
                String codeLabWorker = "";     
                String dateToday = getDateToday();
                String time = getTime();
                for (FileItem item : formItems) {     
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        storeFile = new File(filePath);
                        System.out.println(filePath);
                        item.write(storeFile);                     
                    }else{
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();
                        if (fieldName.equals("codeLabWorker")) {
                            codeLabWorker = fieldValue;
                        }else if (fieldName.equals("codeResult")) {
                            codeResult = fieldValue;
                        }
                    }                   
                }
                //Enviar a guardar
                LabWorkerDB labDB = new LabWorkerDB();
                labDB.updateResult(codeLabWorker, storeFile, dateToday, time, codeResult);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        getServletContext().getRequestDispatcher("/patientGUI/message.jsp").forward(request, response);
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

    
    private String getDateToday(){
        Date objDate = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String dateToday = objSDF.format(objDate);
        return dateToday;
    }
    
    private String getTime(){
        Calendar calendario = Calendar.getInstance();
        int hora =calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        String time = String.valueOf(hora)+":"+ String.valueOf(minutos) +":00";
        return time;
    }
}
