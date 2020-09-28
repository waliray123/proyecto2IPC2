/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.AdminControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import com.mycompany.proyecto2.Objects.encryptPassword;
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

    /**
     * This method is used for some administrator to register a doctor to the database.     
     * @param code
     * @param name
     * @param collegiate
     * @param DPI
     * @param phone
     * @param email
     * @param password
     * @param initTime
     * @param finalTime
     * @param initWork
     */
    public void insertMedic(String code, String name, String collegiate, String DPI, String phone,
            String email, String password, String initTime, String finalTime, String initWork){
        encryptPassword encrypt = new encryptPassword();
        password = encrypt.getMD5(password);
        try {
            ps = connection.prepareStatement("INSERT INTO MEDIC VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, code);
            ps.setString(2, name);            
            ps.setString(3, collegiate);
            ps.setString(4, DPI);
            ps.setString(5, phone);
            ps.setString(6, email);
            ps.setString(7, password);
            ps.setString(8, initTime);
            ps.setString(9, finalTime);
            ps.setString(10, initWork);
            
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            
        }
    }

    /**
     * This method is used for some administrator to register a Lab Worker to the database.     
     * @param code
     * @param name
     * @param registry
     * @param DPI
     * @param phone
     * @param email
     * @param initWork
     * @param password
     * @param typeExam
     */
    public void insertLabWorker(String code, String name, String registry, String DPI, String phone,
            String email, String initWork, String password, String typeExam){
        encryptPassword encrypt = new encryptPassword();
        password = encrypt.getMD5(password);
        try {
            ps = connection.prepareStatement("INSERT INTO LAB_WORKER VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, code);
            ps.setString(2, name);            
            ps.setString(3, registry);
            ps.setString(4, DPI);
            ps.setString(5, phone);
            ps.setString(6, email);
            ps.setString(7, initWork);
            ps.setString(8, password);
            ps.setString(9, typeExam);
                        
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            
        }
    }
    
    /**
     * This method is used for some administrator to register a doctor to the database.
     * @param code
     * @param name
     * @param use_order
     * @param description
     * @param cost
     * @param report_type
     */
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
    
    /**
     * This method is used for some administrator to register a doctor to the database.     
     * @param codeSpecialty
     * @param codeMedic
     */
    public void insertSpecialtyMedic(String codeSpecialty, String codeMedic){
        try {
            ps = connection.prepareStatement("INSERT INTO SPECIALTY_MEDIC (SPECIALTY_code, MEDIC_code) VALUES (?,?)");
            ps.setString(1, codeSpecialty);
            ps.setString(2, codeMedic);
            
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            
        }
    }
    
    public void insertDayLabWorker(String codeLabWorker, String codeDay){
        try {
            ps = connection.prepareStatement("INSERT INTO DAY_LABWORKER (LAB_WORKER_code , DAY_code) VALUES (?,?)");
            ps.setString(1, codeLabWorker);
            ps.setString(2, codeDay);
            
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            
        }
    }
    
    public void insertConsultation(String type, double cost){
        try {
            ps = connection.prepareStatement("INSERT INTO CONSULTATION (type,cost) VALUES (?,?)");
            ps.setString(1, type);
            ps.setDouble(2, cost);
            
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            
        }
    }
    
    public String searchCodeSpecialtyByName(String name){
        String codeSpecialty= "";                
        try {            
            ps = connection.prepareStatement("SELECT code FROM SPECIALTY WHERE name = ?");
            ps.setString(1, name);                       
            rs = ps.executeQuery();            
            if (rs.next()){
                codeSpecialty = rs.getString(1);
            }            
        } catch (Exception e) {
            
        }
        return codeSpecialty;
    }
    
    public String searchCodeDayByName(String name){
        String codeDay= "";                
        try {            
            ps = connection.prepareStatement("SELECT code FROM DAY WHERE name = ?");
            ps.setString(1, name);                       
            rs = ps.executeQuery();            
            if (rs.next()){
                codeDay = rs.getString(1);
            }            
        } catch (Exception e) {
            
        }
        return codeDay;
    }
    
    public String searchCodeExamByName(String name){
        String codeExam= "";                
        try {            
            ps = connection.prepareStatement("SELECT code FROM EXAM WHERE name = ?");
            ps.setString(1, name);                       
            rs = ps.executeQuery();            
            if (rs.next()){
                codeExam = rs.getString(1);
            }            
        } catch (Exception e) {
            
        }
        return codeExam;
    }
    
    
}
