/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.DBControlers;

import com.mycompany.proyecto2.Utils.Appointment;
import com.mycompany.proyecto2.Utils.Exam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user-ubunto
 */
public class RegularConsultations {
    
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs;               
    
    public RegularConsultations() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    public Appointment getAppointmentByCode(String codeAppointment){
        
        Appointment appointment = null;
        try {            
            ps = connection.prepareStatement("SELECT * FROM APPOINTMENT WHERE code = ?");
            ps.setString(1, codeAppointment);
            ResultSet res = ps.executeQuery();            
            if (res.next()){
                String code = res.getString(1);
                String date = res.getString(2);
                String time = res.getString(3);
                String codePatient = res.getString(4);                
                String codeMedic = res.getString(5);                
                String codeSpecialty = res.getString(6);
                appointment = new Appointment(code,date,time,codePatient,codeMedic,codeSpecialty);
            }
            res.close();
        } catch (Exception e) {
            
        }
        return appointment;
    }
    
     public Exam getExamByCode(String codeExam){        
        Exam exam = null;
        try {            
            ps = connection.prepareStatement("SELECT * FROM EXAM WHERE code = ?");
            ps.setString(1, codeExam);
            ResultSet res = ps.executeQuery();            
            if (res.next()){
                String code = res.getString(1);
                String name = res.getString(2);
                Boolean useOrder = res.getBoolean(3);
                String description = res.getString(4);                
                Double cost = res.getDouble(5);                
                String reportType = res.getString(6);
                exam = new Exam(code,name,useOrder,description,cost,reportType);
            }
            res.close();
        } catch (Exception e) {
            
        }
        return exam;
    }
}
