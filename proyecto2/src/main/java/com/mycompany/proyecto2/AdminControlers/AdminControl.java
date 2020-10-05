/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.AdminControlers;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author user-ubunto
 */
public class AdminControl {
    private AdminDB adminDB;

    public AdminControl() {
        this.adminDB = new AdminDB();
    }
    
    
    public ArrayList<String> getAllSpecialties(){
        return this.adminDB.getAllSpecialties();
    }
    
    public ArrayList<String> getAllExams(){
        return this.adminDB.getAllExams();
    }
    
    public String getCostConsultationByType(String type){
        String[] consultation = new String[3];
        consultation = adminDB.searchConsultationByType(type);        
        return consultation[2];
    }
    
    public String getCodeConsultationByType(String type){
        String[] consultation = new String[3];
        consultation = adminDB.searchConsultationByType(type);        
        return consultation[0];
    }
    
    /**
     *This method is used  to review the parameters before make the insert 
     * of the medicc in the database.
     * @param specialties
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
    public boolean addNewMedic(String[] specialties, String code, String name, String collegiate, String DPI, String phone,
            String email, String password, String initTime, String finalTime, String initWork){
        boolean addMedic = false;
        if (specialties == null || code.equals("") || name.equals("") ||
                collegiate.equals("") || DPI.equals("") || phone.equals("") || email.equals("") ||
                password.equals("") || initTime.equals("") || finalTime.equals("")  || initWork.equals("")) {            
                 addMedic = false;      
        }else{
            String codeSearch = adminDB.searchCodeMedicByCode(code);
            if (code.equals(codeSearch) == false) {
                if (phone.length()<=8) {
                    adminDB.insertMedic(code, name, collegiate, DPI, phone, email, password, initTime, finalTime, initWork);
                    for (int i = 0; i < specialties.length; i++) {
                        String specialty = specialties[i];
                        String codeSpecialty = adminDB.searchCodeSpecialtyByName(specialty);
                        adminDB.insertSpecialtyMedic(codeSpecialty, code);
                    }
                    addMedic = true;
                    JOptionPane.showMessageDialog(null,"Se agrego el medico con exito");
                }
            } 
        }
        return addMedic;
    }
    
    /**
     *This method is used  to review the parameters before make the insert 
     * of the labworker in the database
     * @param days
     * @param code
     * @param name
     * @param registry
     * @param DPI
     * @param phone
     * @param email
     * @param initWork
     * @param password
     * @param typeExam
     * @return
     */
    public boolean addNewLabWorker(String[] days, String code, String name, String registry, String DPI, String phone,
            String email, String initWork, String password, String typeExam){
        boolean addLab = false;
        if (days == null || code.equals("") || name.equals("") || registry.equals("") || 
                DPI.equals("") || phone.equals("") || email.equals("") ||
                password.equals("") || initWork.equals("")) {            
                 addLab = false;      
        }else{
            String codeSearch = adminDB.searchCodeLabWorkerByCode(code);
            if (code.equals(codeSearch) == false) {
                if (phone.length()<=8) {
                    String codeExam = adminDB.searchCodeExamByName(typeExam);
                    adminDB.insertLabWorker(code, name, registry, DPI, phone, email, initWork, password, codeExam);
                    for (int i = 0; i < days.length; i++) {
                        String day = days[i];
                        String codeDay = adminDB.searchCodeDayByName(day);
                        adminDB.insertDayLabWorker(code, codeDay);
                    }
                    addLab = true;
                    JOptionPane.showMessageDialog(null,"Se agrego el Laboratorista con exito");
                }
            } 
        }
        return addLab;
    }
    
    /**
     *This method is used  to review the parameters before make the insert 
     * of the exam in the database
     * @param code
     * @param name
     * @param useOrderChecked
     * @param description
     * @param costS
     * @param report_type
     * @return
     */
    public boolean addNewExam(String code, String name, String[] useOrderChecked, String description, String costS, String report_type){
        boolean addExam = false;
        if (code.equals("") || name.equals("") || description.equals("") || costS.equals("")|| report_type.equals("")) {            
                 addExam = false;      
        }else{
            String codeSearch = adminDB.searchCodeExamByCode(code);
            String nameSearch = adminDB.searchCodeExamByName(name);
            if (code.equals(codeSearch) == false && nameSearch.equals("") == true) {                                    
                try{
                    Double cost = Double.parseDouble(costS);
                    boolean use_order = false;
                    if (useOrderChecked != null) {
                        use_order = true;
                    }
                    adminDB.insertTypeExam(code, name, use_order, description, cost, report_type);
                    addExam = true;                
                    JOptionPane.showMessageDialog(null,"Se agrego el Examen con exito");
                }catch(Exception e){
                    
                }                                
            } 
        }
        return addExam;
    }
    
    public void updateConsultation(String code, String cost){        
        adminDB.updateConsultationByCode(code, cost);
        JOptionPane.showMessageDialog(null,"Se actualizo la consulta con exito");
    }
    
    public boolean updateMedic(String[] specialties, String code, String name, String collegiate, String DPI, String phone,
            String email, String initTime, String finalTime, String initWork){
        boolean addMedic = false;
        if (specialties == null || code.equals("") || name.equals("") ||
                collegiate.equals("") || DPI.equals("") || phone.equals("") || email.equals("")
                || initTime.equals("") || finalTime.equals("")  || initWork.equals("")) {            
                 addMedic = false;      
        }else{
                if (phone.length()<=8) {
                    adminDB.updateMedic(code, name, collegiate, DPI, phone, email, initTime, finalTime, initWork);
                    deleteAllSpecialtyMedic(code);
                    for (int i = 0; i < specialties.length; i++) {
                        String specialty = specialties[i];
                        String codeSpecialty = adminDB.searchCodeSpecialtyByName(specialty);
                        adminDB.insertSpecialtyMedic(codeSpecialty, code);
                    }
                    addMedic = true;
                    JOptionPane.showMessageDialog(null,"Se agrego el medico con exito");
                }            
        }
        return addMedic;
    }
    
    public void deleteAllSpecialtyMedic(String codeMedic){
        ArrayList<String> specialties = adminDB.getSpecialtiesByCodeMedic(codeMedic);
        for (String specialty : specialties) {
            String codeSpecialty = adminDB.searchCodeSpecialtyByName(specialty);
            String codeRelation = adminDB.getCodeRelationSpecialtyMedic(codeMedic, codeSpecialty);
            adminDB.deleteRelationSpecialtyMedis(codeRelation);
        }
    }
    
    public boolean updateLabWorker(String[] days, String code, String name, String registry, String DPI, String phone,
            String email, String initWork, String typeExam){
        boolean addLab = false;
        if (days == null || code.equals("") || name.equals("") || registry.equals("") || 
                DPI.equals("") || phone.equals("") || email.equals("") || initWork.equals("")) {            
                 addLab = false;      
        }else{
                if (phone.length()<=8) {
                    String codeExam = adminDB.searchCodeExamByName(typeExam);
                    adminDB.updateLabWorker(code, name, registry, DPI, phone, email, initWork, codeExam);
                    deleteRelationDayLabWorker(code);
                    for (int i = 0; i < days.length; i++) {
                        String day = days[i];
                        String codeDay = adminDB.searchCodeDayByName(day);
                        adminDB.insertDayLabWorker(code, codeDay);
                    }
                    addLab = true;
                    JOptionPane.showMessageDialog(null,"Se agrego el Laboratorista con exito");
                }
            
        }
        return addLab;
    }
    
    public void deleteRelationDayLabWorker(String codeLabWorker){
        ArrayList<String> codesRelation = adminDB.getCodesRelationDayLabWorker(codeLabWorker);
        for (String codeRelation : codesRelation) {                        
            adminDB.deleteRelationDayLabWorker(codeRelation);
        }
    }
    
}
