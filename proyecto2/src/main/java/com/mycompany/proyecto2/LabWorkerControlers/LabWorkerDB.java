/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.LabWorkerControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import com.mycompany.proyecto2.Utils.Result;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author user-ubunto
 */
public class LabWorkerDB {
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs;               
    
    public LabWorkerDB() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    
    public void insertResult(String code, File orderResult, File informResult, String dateResult, String timeResult, 
            String codeLabWorker, String codePatient, String codeMedic, String codeExam){                
        try {
            ps = connection.prepareStatement("INSERT INTO RESULT VALUES (?,?,?,?,?,?,?,?,?)");
            FileInputStream order;
            if (orderResult == null) {
                order = null;
                 ps.setBinaryStream(2, null);
            }else{
                order = new FileInputStream(orderResult);                            
                ps.setBinaryStream(2, order, (int)orderResult.length());
            }
            if (codeMedic.equals("")) {
                codeMedic = null;
            }
            FileInputStream inform = new FileInputStream(informResult);
            
            ps.setString(1, code);
            ps.setBinaryStream(3, inform);       
            ps.setString(4, dateResult);
            ps.setString(5, timeResult);
            ps.setString(6, codeLabWorker);
            ps.setString(7, codePatient);
            ps.setString(8, codeMedic);
            ps.setString(9, codeExam);
                        
            ps.executeUpdate();//action done
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Result EN BASE DE DATOS", JOptionPane.ERROR_MESSAGE);
        }
    }            
    
    public boolean labWorkerWorkToday(String codeLabWorker, String codeDay){
        boolean work = false;
        try {            
            ps = connection.prepareStatement("SELECT l.code, l.name, dl.DAY_code FROM (LAB_WORKER as l, DAY_LABWORKER AS dl) WHERE l.code = ? AND l.code = dl.LAB_WORKER_code AND dl.DAY_code = ?");
            ps.setString(1, codeLabWorker);
            ps.setString(2, codeDay);
            ResultSet res = ps.executeQuery();            
            if (res.next()){
                work = true;
            }         
            res.close();
        } catch (Exception e) {
            
        }
        return work;
    }
    
    public ArrayList<Result> getAllResultsPendings(){
        ArrayList<Result> results = new ArrayList<Result>();
        try {            
            ps = connection.prepareStatement("SELECT * FROM (RESULT AS r) WHERE r.LAB_WORKER_code IS NULL;");
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String code = res.getString(1);
                Blob order_Result = res.getBlob(2);
                Blob inform = res.getBlob(3);
                String date = res.getString(4);
                String time = res.getString(5);
                String codeLabWorker = res.getString(6);
                String codePatient2 = res.getString(7);
                String codeMedic = res.getString(8);
                String codeExam = res.getString(9);
                Result tempResult = new Result(code,order_Result,inform,date,time,codeLabWorker,codePatient2,codeMedic,codeExam);
                results.add(tempResult);               
            }         
            res.close();
        } catch (Exception e) {
            
        }    
        return results;
    }
    
    public void updateResult(String codeLabWorker, File inform, String dateToday, String time ,String codeResult){
        try {
            FileInputStream inform2;
            inform2 = new FileInputStream(inform);                                        
            ps = connection.prepareStatement("UPDATE RESULT SET LAB_WORKER_code  = ?, time_Result = ?, date_Result = ?, inform = ? WHERE code = ?");
            ps.setString(1, codeLabWorker);
            ps.setString(2, time);
            ps.setString(3, dateToday);
            ps.setBinaryStream(4, inform2, (int)inform.length());
            ps.setString(5, codeResult);            
            ps.executeUpdate();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

