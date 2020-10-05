/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.DBControlers;

import com.mycompany.proyecto2.Utils.encryptPassword;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author user-ubunto
 */
public class PrivateInserts {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;               
    
    public PrivateInserts() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    public void insertSpecialty(String name){
        try {
            ps = connection.prepareStatement("INSERT INTO SPECIALTY (name) VALUES (?)");
            ps.setString(1, name);
            
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error insert Admin", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *This method is used to insert an administrator into the database
     * 
     * @param code
     * @param DPI
     * @param name
     * @param password
     */
    public void insertAdmin(String code,String DPI, String name, String password){
        encryptPassword encrypt = new encryptPassword();
        password = encrypt.getMD5(password);
        try {
            ps = connection.prepareStatement("INSERT INTO ADMINISTRATOR VALUES (?,?,?,?)");
            ps.setString(1, code);            
            ps.setString(2, DPI);
            ps.setString(3, name);
            ps.setString(4, password);
            
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error insert Admin", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     *This method is used to insert a patient into the database
     * 
     * @param code
     * @param name
     * @param gender
     * @param birth
     * @param DPI
     * @param phone
     * @param weight
     * @param typeBlood
     * @param mail
     * @param password
     */
    public void insertPatient(String code,String name,String gender, String birth, 
            String DPI,String phone,String weight, String typeBlood, String mail, String password){
        encryptPassword encrypt = new encryptPassword();
        password = encrypt.getMD5(password);
        try {
            ps = connection.prepareStatement("INSERT INTO PATIENT VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, code);            
            ps.setString(2, name);            
            ps.setString(3, gender);            
            ps.setString(4, birth);            
            ps.setString(5, DPI);            
            ps.setString(6, phone);
            ps.setString(7, weight);
            ps.setString(8, typeBlood);
            ps.setString(9, mail);            
            ps.setString(10, password);
            
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error insert Patient", JOptionPane.ERROR_MESSAGE);
        }
    }        
    
    public boolean registerPatient(String code,String name,String gender, String birth, 
            String DPI,String phone,String weight, String typeBlood, String mail, String password){
        boolean isRegistered = false;
        if (code.equals("") || name.equals("") || gender.equals("") || birth.equals("") ||
                DPI.equals("") || phone.equals("") || weight.equals("") || typeBlood.equals("")|| 
                mail.equals("") || password.equals("")) {
            isRegistered = false;
        }else{
            this.insertPatient(code, name, gender, birth, DPI, phone, weight, typeBlood, mail, password);
            isRegistered = true;
        }
        return isRegistered;
            
    }
}
