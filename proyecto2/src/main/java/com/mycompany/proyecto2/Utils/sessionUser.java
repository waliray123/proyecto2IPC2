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
public class sessionUser {
    
    private String codeUser;
    private String typeUser;
    private String passwordUser;

    
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs; 
    
    public sessionUser(String codeUser, String passwordUser) {
        createConnectionToDB();
        this.codeUser = codeUser;
        this.passwordUser = passwordUser;   
        reviewUser();
    }

    public String getTypeUser() {
        return typeUser;
    }
        
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    private void reviewUser(){
        encryptPassword encrypt = new encryptPassword();
        this.passwordUser = encrypt.getMD5(this.passwordUser);
        Boolean exists= false;
        exists = reviewUserExistDB("SELECT code,password FROM PATIENT WHERE code = ? AND password = ?");
        if (exists == true) {
            this.typeUser = "patient"; 
        }else{
            exists = reviewUserExistDB("SELECT code,password FROM MEDIC WHERE code = ? AND password = ?");
            if (exists == true) {
                this.typeUser = "medic";
            }else{
                exists = reviewUserExistDB("SELECT code,password FROM LAB_WORKER WHERE code = ? AND password = ?");
                if (exists == true) {
                    this.typeUser = "lab";
                }else{
                    exists = reviewUserExistDB("SELECT code,password FROM ADMINISTRATOR WHERE code = ? AND password = ?");
                    if (exists == true) {
                        this.typeUser = "admin";
                    }else{
                        this.typeUser = "nulo";
                    }
                }
            }
        }
    }            
        
    private boolean reviewUserExistDB(String statement){        
        Boolean exists= false;                
        try {            
            ps = connection.prepareStatement(statement);
            ps.setString(1, this.codeUser);                       
            ps.setString(2, this.passwordUser);  
            rs = ps.executeQuery();            
            if (rs.next()){
                exists= true;
            }            
        } catch (Exception e) {
            
        }
        return exists;    
    }    
}
