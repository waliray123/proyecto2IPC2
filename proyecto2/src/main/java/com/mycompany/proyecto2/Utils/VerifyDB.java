/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.Utils;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user-ubunto
 */
public class VerifyDB {
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs;               
    
    public VerifyDB() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    public String search1Admin(){
        String codeAdmin= "";                
        try {            
            ps = connection.prepareStatement("SELECT * FROM ADMINISTRATOR;");                
            rs = ps.executeQuery();            
            if (rs.next()){
                codeAdmin = rs.getString(1);
            }            
        } catch (Exception e) {
            
        }
        return codeAdmin;
    }
}
