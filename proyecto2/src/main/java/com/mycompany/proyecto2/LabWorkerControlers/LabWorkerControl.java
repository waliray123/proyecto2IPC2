/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.LabWorkerControlers;

import com.mycompany.proyecto2.AdminControlers.AdminDB;
import com.mycompany.proyecto2.Utils.LabWorker;
import com.mycompany.proyecto2.Utils.Result;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;


/**
 *
 * @author user-ubunto
 */
public class LabWorkerControl {
    private LabWorkerDB labWorkerDB;

    public LabWorkerControl() {
        this.labWorkerDB = new LabWorkerDB();
    }
    
    public ArrayList<Result> getPendingResultsByCodeLab(String codeLabWorker){
        ArrayList<Result> results = new ArrayList<Result>();
        boolean work = labWorkerWorkToday(codeLabWorker,getDayToday());
        String examCode = "";
        LabWorker labW = labWorkerDB.getLabWorkerByCode(codeLabWorker);
        examCode = labW.getExamCode();
        if (work) {
            results = labWorkerDB.getAllResultsByExamCode(examCode);
        }
        return results;
    }
    
    private String getDayToday(){
        String dayToday = "";        
        Calendar date = Calendar.getInstance();
        int dayNumber = date.get(Calendar.DAY_OF_WEEK);        
        switch (dayNumber){
        case 1: dayToday = "Domingo";
            break;
        case 2: dayToday = "Lunes";
            break;
        case 3: dayToday = "Martes";
            break;
        case 4: dayToday = "Miercoles";
            break;
        case 5: dayToday = "Jueves";
            break;
        case 6: dayToday = "Viernes";
            break;
        case 7: dayToday = "Sabado";
            break;
        }        
        return dayToday;
    }
    
    private boolean labWorkerWorkToday(String codeLabWorker,String nameDay){
        boolean work = false;
        AdminDB adminDB = new AdminDB();
        String codeDay = adminDB.searchCodeDayByName(nameDay);
        work = labWorkerDB.labWorkerWorkToday(codeLabWorker, codeDay);
        return work;
    }
    
    public String getDateToday(){
        Date objDate = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String dateToday = objSDF.format(objDate);
        return dateToday;
    }
    
    public String getTime(){
        Calendar calendario = Calendar.getInstance();
        int hora =calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        String time = String.valueOf(hora)+":"+ String.valueOf(minutos) +":00";
        return time;
    }
}

