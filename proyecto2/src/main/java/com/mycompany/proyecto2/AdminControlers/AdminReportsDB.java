/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.AdminControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import com.mycompany.proyecto2.Utils.Appointment;
import com.mycompany.proyecto2.Utils.DateQuantity;
import com.mycompany.proyecto2.Utils.Medic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author user-ubunto
 */
public class AdminReportsDB {
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs;               
    
    public AdminReportsDB() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    public ArrayList<DateQuantity> get10MedicsWithMoreInformsByDate(String date1, String date2){
        //The Date quantity is only used to transport the code medics and the quantity of their informs
        ArrayList<DateQuantity> medicsQuantity = new ArrayList<DateQuantity>();
        try {            
            ps = connection.prepareStatement("SELECT MEDIC_code,COUNT(MEDIC_code) AS quantity FROM INFORM WHERE date_Inform BETWEEN ? AND ? GROUP BY MEDIC_code ORDER BY quantity DESC LIMIT 10;");            
            ps.setString(1, date1);
            ps.setString(2, date2);
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String codeMedic = res.getString(1);
                String quantity = res.getString(2);
                DateQuantity tempMedQ = new DateQuantity(codeMedic,quantity);
                medicsQuantity.add(tempMedQ);
            }         
            res.close();
        } catch (Exception e) {
            
        }
        return medicsQuantity;
    }
    
    public ArrayList<DateQuantity> get5MedicsWithLowestInformsByDate(String date1, String date2){
        //The Date quantity is only used to transport the code medics and the quantity of their informs
        ArrayList<DateQuantity> medicsQuantity = new ArrayList<DateQuantity>();
        try {            
            ps = connection.prepareStatement("SELECT MEDIC_code,COUNT(MEDIC_code) AS quantity FROM APPOINTMENT WHERE date_Appointment BETWEEN ? AND ? GROUP BY MEDIC_code ORDER BY quantity ASC LIMIT 5");            
            ps.setString(1, date1);
            ps.setString(2, date2);
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String codeMedic = res.getString(1);
                String quantity = res.getString(2);
                DateQuantity tempMedQ = new DateQuantity(codeMedic,quantity);
                medicsQuantity.add(tempMedQ);
            }         
            res.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return medicsQuantity;
    }
        
    public ArrayList<DateQuantity> getMostDemandedExam(String date1, String date2){
        //The Date quantity is only used to transport the code medics and the quantity of their informs
        ArrayList<DateQuantity> examsQuantity = new ArrayList<DateQuantity>();
        try {
            ps = connection.prepareStatement("SELECT EXAM_code , COUNT(EXAM_code) AS quantity FROM RESULT WHERE date_Result BETWEEN ? AND ? GROUP BY EXAM_code ORDER BY quantity DESC;");            
            ps.setString(1, date1);
            ps.setString(2, date2);
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String codeMedic = res.getString(1);
                String quantity = res.getString(2);
                DateQuantity tempMedQ = new DateQuantity(codeMedic,quantity);
                examsQuantity.add(tempMedQ);
            }         
            res.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return examsQuantity;
    }
    
    public ArrayList<DateQuantity> getAllIncomesMedicByDate(String date1, String date2){
        ArrayList<DateQuantity> medicsTotal = new ArrayList<DateQuantity>();
        ArrayList<Medic> medics = getAllMedics();
        for (Medic medic : medics) {
            Double totalMoney = 0.0;
            String codeMedic = medic.getCode();
            ArrayList<Appointment> appointments= getAppointmentsMedicByDate(codeMedic,date1,date2);
            for (Appointment appointment : appointments) {
                String nameSpecialty = getNameSpecialtyByCode(appointment.getCodeSpecialty());
                Double costAppointment = getCostAppointment(nameSpecialty);    
                totalMoney+=costAppointment;
            }
            DateQuantity medicT = new DateQuantity(medic.getCode(),String.valueOf(totalMoney));
            medicsTotal.add(medicT);
        }
        return medicsTotal;
    }
    
    public ArrayList<DateQuantity> getAllIncomesPatientsByDate(String date1, String date2){
        ArrayList<DateQuantity> medicsTotal = new ArrayList<DateQuantity>();
        ArrayList<String> codesPatients = getAllCodesPatients();
        for (String codePatient : codesPatients) {
            Double totalMoney = 0.0;
            ArrayList<Appointment> appointments= getAppointmentsPatientByDate(codePatient,date1,date2);
            for (Appointment appointment : appointments) {
                String nameSpecialty = getNameSpecialtyByCode(appointment.getCodeSpecialty());
                Double costAppointment = getCostAppointment(nameSpecialty);    
                totalMoney+=costAppointment;
            }
            DateQuantity medicT = new DateQuantity(codePatient,String.valueOf(totalMoney));
            medicsTotal.add(medicT);
        }
        return medicsTotal;
    }
    
    public Double getCostAppointment(String nameAppointment){
        Double costAppointment = 0.0;
        try {            
            ps = connection.prepareStatement("SELECT * FROM CONSULTATION WHERE type = ?");
            ps.setString(1, nameAppointment);
            ResultSet res = ps.executeQuery();            
            if (res.next()){
                costAppointment = res.getDouble(3);
            }
            res.close();
        } catch (Exception e) {
            
        }
        return costAppointment;
    }
    
    public ArrayList<Appointment> getAppointmentsMedicByDate(String codeMedic,String date1,String date2){
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        try {            
            ps = connection.prepareStatement("SELECT * FROM APPOINTMENT WHERE MEDIC_code = ? AND date_Appointment BETWEEN ? AND ?;");
            ps.setString(1, codeMedic);
            ps.setString(2, date1);
            ps.setString(3, date2);
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
    
    public ArrayList<Appointment> getAppointmentsPatientByDate(String codeMedic,String date1,String date2){
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        try {            
            ps = connection.prepareStatement("SELECT * FROM APPOINTMENT WHERE PATIENT_code = ? AND date_Appointment BETWEEN ? AND ?;");
            ps.setString(1, codeMedic);
            ps.setString(2, date1);
            ps.setString(3, date2);
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
   
   public String getNameSpecialtyByCode(String codeSpecialty){
        String nameSpecialty = "";
        try {            
            ps = connection.prepareStatement("SELECT * FROM SPECIALTY WHERE code = ?");
            ps.setString(1, codeSpecialty);
            ResultSet res = ps.executeQuery();            
            if (res.next()){
                nameSpecialty = res.getString(2);
            }
            res.close();
        } catch (Exception e) {
            
        }
        return nameSpecialty;
    }
   
   public ArrayList<String> getAllCodesPatients(){
       ArrayList<String> patientsCodes = new ArrayList<String>();
       try {            
            ps = connection.prepareStatement("SELECT code FROM PATIENT");
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String code = res.getString(1);
                patientsCodes.add(code);
            }            
        } catch (Exception e) {
            
        }       
       return patientsCodes;
   }
}
