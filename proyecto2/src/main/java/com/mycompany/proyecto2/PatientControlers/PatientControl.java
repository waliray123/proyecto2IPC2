/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.PatientControlers;

import com.mycompany.proyecto2.AdminControlers.AdminDB;
import com.mycompany.proyecto2.Utils.Appointment;
import com.mycompany.proyecto2.Utils.Exam;
import com.mycompany.proyecto2.Utils.Medic;
import com.mycompany.proyecto2.Utils.Patient;
import com.mycompany.proyecto2.Utils.Result;
import java.io.File;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author user-ubunto
 */
public class PatientControl {
    private PatientDB patientDB;

    public PatientControl() {
        this.patientDB = new PatientDB();
    }
    
    public ArrayList<Medic> getAllMedics(){
        ArrayList<Medic> medics = patientDB.getAllMedics();
        return medics;
    }
    
    public boolean reviewIfMedicIsFree(String date, String time, String codeMedic){  
        boolean isFree = false;
        if (date.equals("") == false && time.equals("")== false && codeMedic.equals("")== false) {
            ArrayList specialties = patientDB.getAllSpecialtiesByCodeMedic(codeMedic);
            if (specialties.size()>0) {
                Time timeReview = Time.valueOf(time+":00");
                if (patientDB.verifyIsMedicFreeByTime(timeReview, codeMedic)) {
                    ArrayList<Appointment> appointments = patientDB.getAppointmentsMedicByDate(codeMedic, date);
                    isFree = true;
                    for (Appointment appointment : appointments) {                        
                        LocalTime ltimeReview = LocalTime.parse(time+":00");
                        LocalTime ltimeAppointment = LocalTime.parse(appointment.getTime());
                        if (ltimeReview == ltimeAppointment) {
                            isFree = false;
                            break;
                        }else{                            
                            if (ltimeReview.isAfter(ltimeAppointment)) {
                                long diff = ChronoUnit.MINUTES.between(ltimeAppointment,ltimeReview);
                                if (diff < 60) {
                                    isFree=false;
                                    break;
                                }
                            }                            
                        }
                    }
                }
            }                              
        }        
        return isFree;
    }       
    
    public boolean sheduleAppointment(String codePatient, String codeMedic,
            String dateAppointment, String timeAppointment, String specialty){
        boolean complete = false;
        if (codePatient.equals("") || codeMedic.equals("") || dateAppointment.equals("")||
                timeAppointment.equals("") || specialty.equals("")) {
            complete = false;
        }else{
            ArrayList specialties = patientDB.getAllSpecialtiesByCodeMedic(codeMedic);  
            if (specialties.contains(specialty)) {
                AdminDB adminDB = new AdminDB();
                String codeSpecialty = adminDB.searchCodeSpecialtyByName(specialty);
                boolean isFree = reviewIfMedicIsFree(dateAppointment,timeAppointment,codeMedic);
                if (isFree) {
                    String lastCodeAppointment = patientDB.getLastCodeAppointment();
                    int lastCode = Integer.valueOf(lastCodeAppointment) + 1;                     
                    patientDB.insertAppointment(String.valueOf(lastCode), dateAppointment, timeAppointment, codePatient, codeMedic, codeSpecialty);
                    JOptionPane.showMessageDialog(null,"Se agendo la cita con exito" + " con la codigo: " + String.valueOf(lastCode));
                }
            }
        }
        return complete;
    }
    
    public ArrayList<Appointment> getAppointmentsByCodePatientAndDate(String date, String codePatient){
        ArrayList<Appointment> appointments = patientDB.getAppointmentsByCodePatientAfterDate(date, codePatient);
        return appointments;
    }
    
    public ArrayList<Result> getResultsPendingByCodePatient(String codePatient){
        ArrayList<Result> results = patientDB.getResultPendingByCodePatient(codePatient);
        return results;
    }
    
    public Exam getExamByName(String typeExam){
        Exam exam = new Exam("","",false,"",0.0,"");
        ArrayList<Exam> exams = getAllExams();
        for (Exam exam1 : exams) {
            if (exam1.getName().equalsIgnoreCase(typeExam)) {
                exam = exam1;
                break;
            }
        }
        return exam;
    }
    
    public ArrayList<Exam> getAllExams(){
        ArrayList<Exam> exams = patientDB.getAllExams();
        return exams;
    }
    
    public String getLastResult(){
        String lastResult = patientDB.getLastResult();        
        return lastResult;
    }
    

    public Patient patientExist(String codePatient){
        Patient patient = patientDB.searchCodePatient(codePatient);
        return patient;
    }
    
    public ArrayList<Result> getResultsDeliveredByCodePatient(String codePatient){
        ArrayList<Result> results = patientDB.getResultsDeliveredByCodePatient(codePatient);        
        return results;
    }
    
    public ArrayList<Appointment> getAppointmentsBeforeByCodePatient(String codePatient){
        Date objDate = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String dateToday = objSDF.format(objDate);        
        ArrayList<Appointment> appointment = patientDB.getAppointmentsByCodePatientBeforeDate(dateToday,codePatient);        
        return appointment;
    }
    
    public ArrayList<Medic> getFilterMedics(String name, String specialty, String time){
        ArrayList<Medic> medics = patientDB.getFilterMedics(name, specialty, time);
        return medics;
    }
}
