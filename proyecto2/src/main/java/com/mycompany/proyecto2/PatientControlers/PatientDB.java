/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.PatientControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import com.mycompany.proyecto2.Objects.encryptPassword;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
}
