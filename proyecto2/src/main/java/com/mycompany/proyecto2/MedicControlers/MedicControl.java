/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.MedicControlers;

import com.mycompany.proyecto2.Utils.Appointment;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;



/**
 *
 * @author user-ubunto
 */
public class MedicControl {
    private MedicDB medicDB;

    public MedicControl() {
        this.medicDB = new MedicDB();
    }
    
    public ArrayList<Appointment> getAppointmentsPendingByCodeMedicAndDate(String codeMedic, String date){
        Calendar calendario = Calendar.getInstance();
        int hora =calendario.get(Calendar.HOUR_OF_DAY) - 1;
        int minutos = calendario.get(Calendar.MINUTE);
        String time = hora+":"+minutos+":00";
        
        ArrayList<Appointment> appointments= medicDB.getAppointmentsPendingByCodeMedicAndDate(codeMedic,date,time);
        return appointments;
    }
    
    public String getLastCodeInform(){
        return medicDB.getLastInform();
    }
    
    public void insertInform(String code, String dateInform, String timeInform, String data, String codePatient, String codeMedic){
        if (code.equals("") || dateInform.equals("") || timeInform.equals("") ||
                data.equals("") || codePatient.equals("") || codeMedic.equals("")) {
            
        }else{
            medicDB.insertInform(code, dateInform, timeInform, data, codePatient, codeMedic);
            JOptionPane.showMessageDialog(null,"Se agrego el informe correctamente");
        }
    }
}
