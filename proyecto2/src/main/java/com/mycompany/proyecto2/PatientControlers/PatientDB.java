/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.PatientControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import com.mycompany.proyecto2.Utils.Appointment;
import com.mycompany.proyecto2.Utils.Medic;
import com.mycompany.proyecto2.Utils.Result;
import com.mycompany.proyecto2.Utils.encryptPassword;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author user-ubunto
 */
public class PatientDB {
    
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs;               
    
    public PatientDB() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }


    public void insertAppointment(String code, String dateAppointment, String timeAppointment,
            String codePatient, String codeMedic, String codeSpecialty){
        try {
            ps = connection.prepareStatement("INSERT INTO APPOINTMENT VALUES (?,?,?,?,?,?)");
            ps.setString(1, code);
            ps.setString(2, dateAppointment);
            ps.setString(3, timeAppointment);
            ps.setString(4, codePatient);
            ps.setString(5, codeMedic);
            ps.setString(6, codeSpecialty);
            
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error appointment", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   public ArrayList<Medic> getAllMedics(){
       ArrayList<Medic> medics = new ArrayList<Medic>();
       try {            
            ps = connection.prepareStatement("SELECT * FROM MEDIC ORDER BY code");
            rs = ps.executeQuery();
            
            while(rs.next()){                
                String code = rs.getString(1);
                String name= rs.getString(2);
                String collegiate= rs.getString(3);
                String DPI= rs.getString(4);
                String phone= rs.getString(5);
                String mail= rs.getString(6);
                String initTime= rs.getString(8);
                String finalTime= rs.getString(9);
                String initWork= rs.getString(10);
                Medic temporalMedic = new Medic(code,name,collegiate,DPI,phone,mail,initTime,finalTime,initWork);
                ArrayList<String> specialties = getAllSpecialtiesByCodeMedic(code);
                temporalMedic.setSpecialties(specialties);
                medics.add(temporalMedic);                
            }            
        } catch (Exception e) {
            
        }
       return medics;
   }
    
   public ArrayList<String> getAllSpecialtiesByCodeMedic(String codeMedic){
       
       ArrayList<String> specialties = new ArrayList<String>();
       try {            
            ps = connection.prepareStatement("SELECT s.code,s.name, m.name FROM (MEDIC AS m,SPECIALTY AS s,SPECIALTY_MEDIC AS sm) WHERE m.code = ? AND sm.SPECIALTY_code = s.code AND sm.MEDIC_code = m.code;");
            ps.setString(1, codeMedic);
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String name = res.getString(2);
                specialties.add(name);
            }            
        } catch (Exception e) {
            
        }
       return specialties;
   }
   
    public ArrayList<Appointment> getAppointmentsMedicByDate(String codeMedic,String dateAppointment){
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        try {            
            ps = connection.prepareStatement("SELECT * FROM APPOINTMENT WHERE MEDIC_code = ? AND date_Appointment = ?");
            ps.setString(1, codeMedic);
            ps.setString(2, dateAppointment);
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String code = res.getString(1);
                String date = res.getString(2);
                String time = res.getString(3);
                String codePatient = res.getString(4);                
                String codeSpecialty = res.getString(6);
                Appointment appointment = new Appointment(code,date,time,codePatient,codeMedic,codeSpecialty);
                appointments.add(appointment);
            }
            res.close();
        } catch (Exception e) {
            
        }
        return appointments;
    }
    
    public boolean verifyIsMedicFreeByTime(Time time, String codeMedic){        
        boolean isFree = false;
        try {            
            ps = connection.prepareStatement("SELECT * FROM MEDIC AS m WHERE ? between m.initTime and m.finalTime AND m.code = ?");
            ps.setTime(1, time);
            ps.setString(2, codeMedic);
            ResultSet res = ps.executeQuery();            
            if (res.next()){
                isFree = true;
            }            
            res.close();
        } catch (Exception e) {
            
        }
        return isFree;
    }
    
    public String getLastCodeAppointment(){
        String lastCode = "";
        try {            
            ps = connection.prepareStatement("SELECT code FROM APPOINTMENT ORDER BY code DESC;");
            ResultSet res = ps.executeQuery();            
            if (res.next()){
                lastCode = res.getString(1);                
            }         
            res.close();
        } catch (Exception e) {
            
        }
        return lastCode;
    }
    
    public ArrayList<Appointment> getAppointmentsByCodePatientBeforeDate(String date,String codePatient){
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        try {            
            ps = connection.prepareStatement("SELECT * FROM APPOINTMENT WHERE date_Appointment >= ? AND PATIENT_code = ?");
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
    
    public ArrayList<Result> getResultPendingByCodePatient(String codePatient){
         ArrayList<Result> results = new  ArrayList<Result>();
         try {            
            ps = connection.prepareStatement("SELECT * FROM RESULT WHERE PATIENT_code = ? AND LAB_WORKER_code = null;");
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
}
