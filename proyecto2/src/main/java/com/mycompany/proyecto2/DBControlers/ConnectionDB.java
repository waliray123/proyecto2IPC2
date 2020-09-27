/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.DBControlers;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author user-ubunto
 */
public class ConnectionDB {
    
    private static final String URL = "jdbc:mysql://localhost:3306/HOSPITAL";//auto reconnect
    private static final String USER = "root";//a test user, u can use a personal user
    private static final String PASSWORD = "password123";//my password for the user

    public Connection getConnection() {
        Connection connection = null;//initialize
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//declare a driver
            connection = DriverManager.getConnection(URL, USER, PASSWORD);//we connect with the info            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR, " + e.getMessage(), "X", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }
    
    
}
