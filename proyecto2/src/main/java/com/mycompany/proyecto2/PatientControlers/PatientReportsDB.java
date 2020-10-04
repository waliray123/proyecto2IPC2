/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.PatientControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import com.mycompany.proyecto2.Utils.Appointment;
import com.mycompany.proyecto2.Utils.Exam;
import com.mycompany.proyecto2.Utils.Result;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user-ubunto
 */
public class PatientReportsDB {
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs;               
    
    public PatientReportsDB() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    public ArrayList<Result> getMostRecentResults(String codePatient){
        ArrayList<Result> results = new ArrayList<Result>();
        try {            
            ps = connection.prepareStatement("SELECT * FROM (RESULT AS r) WHERE r.PATIENT_code =? AND r.LAB_WORKER_code IS NOT NULL ORDER BY r.date_Result DESC LIMIT 5;");
            ps.setString(1, codePatient);
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String code = res.getString(1);
                Blob order_Result = res.getBlob(2);
                Blob inform = res.getBlob(3);
                String date = res.getString(4);
                String time = res.getString(5);
                String codeLabWorker = res.getString(6);
                String codePatient2 = res.getString(7);
                String codeMedic = res.getString(8);
                String codeExam = res.getString(9);
                Result tempResult = new Result(code,order_Result,inform,date,time,codeLabWorker,codePatient2,codeMedic,codeExam);
                results.add(tempResult);               
            }         
            res.close();
        } catch (Exception e) {
            
        } 
        return results;      
    }
    
    public ArrayList<Result> getResultsByExamAndDate(String codePatient, String date1 ,String date2, String codeExam){
        ArrayList<Result> results = new ArrayList<Result>();
        try {            
            ps = connection.prepareStatement("SELECT * FROM (RESULT AS r) WHERE r.EXAM_code = ? AND r.PATIENT_code = ? AND r.date_Result BETWEEN ? AND ?");
            ps.setString(1, codeExam);
            ps.setString(2, codePatient);
            ps.setString(3, date1);
            ps.setString(4, date2);
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String code = res.getString(1);
                Blob order_Result = res.getBlob(2);
                Blob inform = res.getBlob(3);
                String date = res.getString(4);
                String time = res.getString(5);
                String codeLabWorker = res.getString(6);
                String codePatient2 = res.getString(7);
                String codeMedic = res.getString(8);
                String codeExam2 = res.getString(9);
                Result tempResult = new Result(code,order_Result,inform,date,time,codeLabWorker,codePatient2,codeMedic,codeExam2);
                results.add(tempResult);               
            }         
            res.close();
        } catch (Exception e) {
            
        } 
        return results;      
    }        
    
    public ArrayList<Appointment> getLast5AppointmentsByCodePatient(String date,String codePatient){
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        try {            
            ps = connection.prepareStatement("SELECT * FROM APPOINTMENT WHERE PATIENT_code = 118258 ORDER BY date_Appointment DESC LIMIT 5;");
            ps.setString(1, date);
            ps.setString(2, codePatient);
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String code = res.getString(1);
                String dateAppointment = res.getString(2);
                String time = res.getString(3);
                String codeMedic = res.getString(5);                
                String codeSpecialty = res.getString(6);
                Appointment appointment = new Appointment(code,dateAppointment,time,codePatient,codeMedic,codeSpecialty);
                appointments.add(appointment);               
            }         
            res.close();
        } catch (Exception e) {
            
        }
        return appointments;
    }
}
