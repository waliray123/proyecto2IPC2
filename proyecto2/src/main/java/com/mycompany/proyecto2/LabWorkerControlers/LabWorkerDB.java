/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.LabWorkerControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author user-ubunto
 */
public class LabWorkerDB {
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs;               
    
    public LabWorkerDB() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    
    public void insertResult(String code, File orderResult, File informResult, String dateResult, String timeResult, 
            String codeLabWorker, String codePatient, String codeMedic, String codeExam){                
        try {
            ps = connection.prepareStatement("INSERT INTO RESULT VALUES (?,?,?,?,?,?,?,?,?)");
            FileInputStream order;
            if (orderResult == null) {
                order = null;
                 ps.setBinaryStream(2, null);
            }else{
                order = new FileInputStream(orderResult);                            
                ps.setBinaryStream(2, order, (int)orderResult.length());
            }
            if (codeMedic.equals("")) {
                codeMedic = null;
            }
            FileInputStream inform = new FileInputStream(informResult);
            
            ps.setString(1, code);
            ps.setBinaryStream(3, inform);       
            ps.setString(4, dateResult);
            ps.setString(5, timeResult);
            ps.setString(6, codeLabWorker);
            ps.setString(7, codePatient);
            ps.setString(8, codeMedic);
            ps.setString(9, codeExam);
                        
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Result EN BASE DE DATOS", JOptionPane.ERROR_MESSAGE);
        }
    }        
    
    public void seeImage(){
        
    }
}

