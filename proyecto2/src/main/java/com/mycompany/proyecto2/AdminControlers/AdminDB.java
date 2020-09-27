/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.AdminControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user-ubunto
 */
public class AdminDB {
    
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs;               
    
    public AdminDB() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    public void insertMedic(){
        
    }
    
    public void insertLabWorker(){
        
    }
    
    public void insertTypeExam(String code, String name, boolean use_order, String description, double cost, String report_type){
        
        try {
            ps = connection.prepareStatement("INSERT INTO EXAM VALUES (?,?,?,?,?,?)");
            ps.setString(1, code);
            ps.setString(2, name);            
            ps.setBoolean(3, use_order);
            ps.setString(4, description);
            ps.setDouble(5, cost);
            ps.setString(6, report_type);
            
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            
        }
    }
    
}
