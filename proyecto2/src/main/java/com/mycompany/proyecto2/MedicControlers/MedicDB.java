/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.MedicControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
