/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.MedicControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import com.mycompany.proyecto2.Utils.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author user-ubunto
 */
public class MedicDB {
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs;               
    
    public MedicDB() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    /**
     *This method is used for some medic to register an inform to the database.
     * @param code
     * @param codePatient
     * @param codeMedic
     * @param data
     * @param dateInform
     * @param timeInform
     */
    public void insertInform(String code, String dateInform, String timeInform, String data, String codePatient, String codeMedic){
        try {
            ps = connection.prepareStatement("INSERT INTO INFORM VALUES (?,?,?,?,?,?)");
            ps.setString(1, code);
            ps.setString(2, dateInform);            
            ps.setString(3, timeInform);
            ps.setString(4, data);
            ps.setString(5, codePatient);
            ps.setString(6, codeMedic);
                        
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            
        }
    }
    
    public ArrayList<Appointment> getAppointmentsPendingByCodeMedicAndDate(String codeMedic, String dateAppointment, String timeInstant){        
        ArrayList<Appointment> appointments =new ArrayList<Appointment>();
        try {            
            ps = connection.prepareStatement("SELECT * FROM APPOINTMENT WHERE MEDIC_code = ? AND date_Appointment = ? AND time_Appointment >=?");
            ps.setString(1, codeMedic);
            ps.setString(2, dateAppointment);
            ps.setString(3, timeInstant);
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
    
    public String getLastInform(){
        String lastInform = "";
        ArrayList<Integer> codesInforms = new ArrayList<Integer>();
        try {            
                ps = connection.prepareStatement("SELECT code FROM INFORM;");
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                int code = Integer.parseInt(res.getString(1));
                codesInforms.add(code);
            }
            res.close();
        } catch (Exception e) {
            
        }
        Collections.sort(codesInforms);
        int size = codesInforms.size();        
        lastInform = String.valueOf(codesInforms.get(size-1)+1);
        return lastInform;
    }
}
