/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.LabWorkerControlers;

import com.mycompany.proyecto2.DBControlers.ConnectionDB;
import com.mycompany.proyecto2.Utils.DateQuantity;
import com.mycompany.proyecto2.Utils.Result;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user-ubunto
 */
public class LabWorkerReportsDB {
    private Connection connection;
    private PreparedStatement ps; //for operations
    private ResultSet rs;               
    
    public LabWorkerReportsDB() {
        createConnectionToDB();
    }
    
    private void createConnectionToDB(){
        ConnectionDB connect = new ConnectionDB();
        this.connection = connect.getConnection();
    }
    
    public ArrayList<Result> getPendingsResultsPendings(){
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
    
    public ArrayList<Result> getResulAchievedToday(String codeLabWorker, String dateToday){
        ArrayList<Result> results = new ArrayList<Result>();
        try {            
            ps = connection.prepareStatement("SELECT * FROM (RESULT AS r) WHERE r.LAB_WORKER_code = ? AND date_Result = ?;");
            ps.setString(1, codeLabWorker);
            ps.setString(2, dateToday);
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String code = res.getString(1);
                Blob order_Result = res.getBlob(2);
                Blob inform = res.getBlob(3);
                String date = res.getString(4);
                String time = res.getString(5);
                String codeLabWorker1 = res.getString(6);
                String codePatient2 = res.getString(7);
                String codeMedic = res.getString(8);
                String codeExam = res.getString(9);
                Result tempResult = new Result(code,order_Result,inform,date,time,codeLabWorker1,codePatient2,codeMedic,codeExam);
                results.add(tempResult);               
            }         
            res.close();
        } catch (Exception e) {
            
        }    
        return results;
    }
    
    public ArrayList<DateQuantity> getDatesWithMoreExamsAchieved(){
        ArrayList<DateQuantity> datesQ = new ArrayList<DateQuantity>();
        try {            
            ps = connection.prepareStatement("SELECT date_Result,COUNT(date_Result) AS quantity FROM RESULT GROUP BY date_Result ORDER BY quantity DESC LIMIT 10;");
            ResultSet res = ps.executeQuery();            
            while (res.next()){
                String date = res.getString(1);
                String quantity = res.getString(2);
                if (date.equals("NULL") == false) {
                    DateQuantity tempDateQ = new DateQuantity(date,quantity);
                    datesQ.add(tempDateQ);               
                }
                
            }         
            res.close();
        } catch (Exception e) {
            
        }    
        return datesQ;
    }
}
