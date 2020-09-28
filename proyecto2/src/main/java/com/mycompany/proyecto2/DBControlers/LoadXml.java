/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.DBControlers;

import com.mycompany.proyecto2.AdminControlers.AdminDB;
import com.mycompany.proyecto2.LabWorkerControlers.LabWorkerDB;
import com.mycompany.proyecto2.MedicControlers.MedicDB;
import com.mycompany.proyecto2.PatientControlers.PatientDB;
import java.awt.HeadlessException;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author user-ubunto
 */
public class LoadXml {
            
    ArrayList<String> nameSpecialties = new ArrayList<String>();
    /**
     *This method is used for the user to choose the path of the 
     * xml file to later be read and entered into the database.
     */
    public void chooseFileXML(){
        try {            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Folder", "xml"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == APPROVE_OPTION) {
                chooseFolder(fileChooser.getSelectedFile().getPath());                    
            } 
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void chooseFolder(String pathXML){
        try {            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == APPROVE_OPTION) {
                readXML(pathXML, fileChooser.getSelectedFile().getPath());                        
            } 
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     *This method is used to read the nodes of the xml file
     * then call the methods to insert the nodes in the database
     * 
     * @param path
     */
    public void readXML(String path, String pathFolder){
        try {            
            File fileXML = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            
            Document document = documentBuilder.parse(fileXML);
            
            document.getDocumentElement().normalize();           
            
            NodeList listExams = document.getElementsByTagName("examen");   
            NodeList listAdmins = document.getElementsByTagName("admin");   
            NodeList listPatients = document.getElementsByTagName("paciente");                                       
            NodeList listSpecialties = document.getElementsByTagName("ESPECIALIDAD");
            NodeList listMedics = document.getElementsByTagName("doctor");
            NodeList listLabWorkers = document.getElementsByTagName("laboratorista");
            NodeList listInforms = document.getElementsByTagName("reporte");
            NodeList listResults = document.getElementsByTagName("resultado");
            NodeList listAppointments = document.getElementsByTagName("cita");
            NodeList listConsultations = document.getElementsByTagName("consulta");
            
            
//            NodeList listLabWorkers = document.getElementsByTagName("laboratorista");
//            JOptionPane.showMessageDialog(null,String.valueOf("La cantidad de medicos"+listMedics.getLength()));
//            JOptionPane.showMessageDialog(null,String.valueOf("La cantidad de especialidades"+listSpecialitys.getLength()));
//            JOptionPane.showMessageDialog(null,String.valueOf("La cantidad de TITULOS"+listTITULOS.getLength()));
            insertExams(listExams);
            insertAdmins(listAdmins);
            insertPatients(listPatients);
            insertSpecialties(listSpecialties);
            insertMedics(listMedics);
            insertLabWorkers(listLabWorkers);
            insertInforms(listInforms);
            insertResults(listResults, pathFolder);
            insertAppointments(listAppointments);
            insertConsultations(listConsultations);
            
            
            
        } catch(Exception e) {
            
        }                
    }
    
    /**
     *This method is used to read and insert the specialties from the xml file
     * @param listSpecialties
     */
    public void insertSpecialties(NodeList listSpecialties){
        PrivateInserts privateInsert = new PrivateInserts();
        nameSpecialties = new ArrayList<String>();
        try{
            for(int i = 0 ; i < listSpecialties.getLength() ; i++) {                                
                Node nodo = listSpecialties.item(i);                                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {                    
                    Element element = (Element) nodo;                       
                    NodeList listTitles = element.getChildNodes();                    
                    for (int j = 0; j < listTitles.getLength(); j++) {
                        Node nodoTitle = listTitles.item(j);
                        if(nodoTitle.getNodeType() == Node.ELEMENT_NODE) {                            
                            String name = nodoTitle.getTextContent();  
                            if (nameSpecialties.contains(name) == false) {
                                nameSpecialties.add(name);
                                privateInsert.insertSpecialty(name);
                            }                            
                        }
                    }                                        
                }                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Especialidad", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }    
    
    /**
     *This method is used to read and insert the Medics and the Specialties from the xml file
     * @param listMedics
     */
    public void insertMedics(NodeList listMedics){
        PrivateInserts privateInsert = new PrivateInserts();
        AdminDB admin = new AdminDB();
        try{
            for(int i = 0 ; i < listMedics.getLength() ; i++) {
                Node nodo = listMedics.item(i);                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String code = element.getElementsByTagName("CODIGO").item(0).getTextContent();                    
                    String name = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String collegiate = element.getElementsByTagName("COLEGIADO").item(0).getTextContent();
                    String DPI = element.getElementsByTagName("DPI").item(0).getTextContent();
                    String phone = element.getElementsByTagName("TELEFONO").item(0).getTextContent();
                    String email = element.getElementsByTagName("CORREO").item(0).getTextContent();
                    String initTime = element.getElementsByTagName("INICIO").item(0).getTextContent();
                    String finalTime = element.getElementsByTagName("FIN").item(0).getTextContent();
                    String initWork = element.getElementsByTagName("TRABAJO").item(0).getTextContent();
                    String password = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    
                    admin.insertMedic(code, name, collegiate, DPI, phone, email, password, initTime, finalTime, initWork);
                                                                           
                    //HERE INSERT AND RELATIONATE IN DATABASE THE SPECIALTIES FROM MEDIC
                        NodeList listTitles = element.getElementsByTagName("TITULO"); 
                        
                        for (int j = 0; j < listTitles.getLength(); j++) {
                            Node nodoTitle = listTitles.item(j);
                            if(nodoTitle.getNodeType() == Node.ELEMENT_NODE) {                            
                                String nameTitle = nodoTitle.getTextContent();  
                                if (this.nameSpecialties.contains(nameTitle) == false) {
                                    this.nameSpecialties.add(nameTitle);
                                    privateInsert.insertSpecialty(nameTitle);
                                }
                                String codeSpecialty = admin.searchCodeSpecialtyByName(nameTitle);
                                admin.insertSpecialtyMedic(codeSpecialty, code);
                            }
                        }                                        
                }
            }                    
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       }
    }
        
    /**
     *This method is used to read and insert the Labworkers from the xml file
     * @param listLabWorkers
     */
    public void insertLabWorkers(NodeList listLabWorkers){
        AdminDB admin = new AdminDB();
        try{
            for(int i = 0 ; i < listLabWorkers.getLength() ; i++) {
                Node nodo = listLabWorkers.item(i);                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String code = element.getElementsByTagName("CODIGO").item(0).getTextContent();                    
                    String name = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String registry = element.getElementsByTagName("REGISTRO").item(0).getTextContent();
                    String DPI = element.getElementsByTagName("DPI").item(0).getTextContent();
                    String phone = element.getElementsByTagName("TELEFONO").item(0).getTextContent();
                    String nameExam = element.getElementsByTagName("EXAMEN").item(0).getTextContent();
                    String email = element.getElementsByTagName("CORREO").item(0).getTextContent();
                    String initWork = element.getElementsByTagName("TRABAJOF").item(0).getTextContent();
                    String password = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    
                    String codeExam = admin.searchCodeExamByName(nameExam);
                    
                    admin.insertLabWorker(code, name, registry, DPI, phone, email, initWork, password, codeExam);
                                                                           
                    //HERE INSERT AND RELATIONATE IN DATABASE THE DAYS THAT WORK THE LABWORKER
                        NodeList listDays = element.getElementsByTagName("DIA"); 
                        
                        for (int j = 0; j < listDays.getLength(); j++) {
                            Node nodeDay = listDays.item(j);
                            if(nodeDay.getNodeType() == Node.ELEMENT_NODE) {                            
                                String nameDay = nodeDay.getTextContent();
                                String codeDay = admin.searchCodeDayByName(nameDay);
                                admin.insertDayLabWorker(code,codeDay);
                            }
                        }                                        
                }
            }                    
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       }
    }
    
    /**
     *This method is used to read and insert the reports or informs from the xml file
     * @param listInforms
     */
    public void insertInforms(NodeList listInforms){
        MedicDB medicDB = new MedicDB();
        try{
            for(int i = 0 ; i < listInforms.getLength() ; i++) {
                Node nodo = listInforms.item(i);                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String code = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String codePatient = element.getElementsByTagName("PACIENTE").item(0).getTextContent();
                    String codeMedic = element.getElementsByTagName("MEDICO").item(0).getTextContent();                                        
                    String data = element.getElementsByTagName("INFORME").item(0).getTextContent();
                    String dateInform = element.getElementsByTagName("FECHA").item(0).getTextContent();
                    String timeInform = element.getElementsByTagName("HORA").item(0).getTextContent();
                    
                    medicDB.insertInform(code, dateInform, timeInform, data, codePatient, codeMedic);
                }
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     *This method is used to read and insert the administrators from the xml file
     * 
     * @param listAdmins
     */
    public void insertAdmins(NodeList listAdmins){
        PrivateInserts privateInsert = new PrivateInserts();
        try{
            for(int i = 0 ; i < listAdmins.getLength() ; i++) {
                Node nodo = listAdmins.item(i);                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String code = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String DPI = element.getElementsByTagName("DPI").item(0).getTextContent();
                    String name = element.getElementsByTagName("NOMBRE").item(0).getTextContent();                                        
                    String password = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    
                    privateInsert.insertAdmin(code, DPI, name, password);
                }
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *This method is used to read and insert the patients from the xml file
     * 
     * @param listPatients
     */
    public void insertPatients(NodeList listPatients){
        PrivateInserts privateInsert = new PrivateInserts();
        try{
            for(int i = 0 ; i < listPatients.getLength() ; i++) {
                Node nodo = listPatients.item(i);                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String code = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String name = element.getElementsByTagName("NOMBRE").item(0).getTextContent(); 
                    String gender = element.getElementsByTagName("SEXO").item(0).getTextContent(); 
                    String birth = element.getElementsByTagName("BIRTH").item(0).getTextContent();                     
                    String DPI = element.getElementsByTagName("DPI").item(0).getTextContent();                                                           
                    String phone = element.getElementsByTagName("TELEFONO").item(0).getTextContent();                                                           
                    String weight = element.getElementsByTagName("PESO").item(0).getTextContent();                                                           
                    String typeBlood = element.getElementsByTagName("SANGRE").item(0).getTextContent();                                                           
                    String email = element.getElementsByTagName("CORREO").item(0).getTextContent();                    
                    String password = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    
                    privateInsert.insertPatient(code, name, gender, birth, DPI, phone, weight, typeBlood, email, password);
                }
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     *This method is used to read and the exams the patients from the xml file
     * @param listExams
     */
    public void insertExams(NodeList listExams){
        AdminDB admin = new AdminDB();
        try{
            for(int i = 0 ; i < listExams.getLength() ; i++) {
                Node nodo = listExams.item(i);                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String code = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String name = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    boolean order = Boolean.getBoolean(element.getElementsByTagName("ORDEN").item(0).getTextContent().toLowerCase());
                    String description = element.getElementsByTagName("DESCRIPCION").item(0).getTextContent();
                    double cost = Double.valueOf(element.getElementsByTagName("COSTO").item(0).getTextContent());
                    String inform = element.getElementsByTagName("INFORME").item(0).getTextContent();
                    
                    admin.insertTypeExam(code, name, order, description, cost, inform);
                }
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void insertResults(NodeList listResults, String pathFolder){       
        LabWorkerDB labDB = new LabWorkerDB();
        try{
            for(int i = 0 ; i < listResults.getLength() ; i++) {
                Node nodo = listResults.item(i);                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String code = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String codePatient = element.getElementsByTagName("PACIENTE").item(0).getTextContent();
                    String codeMedic = element.getElementsByTagName("MEDICO").item(0).getTextContent();                                        
                    String codeExam = element.getElementsByTagName("EXAMEN").item(0).getTextContent();
                    String codeLabWorker = element.getElementsByTagName("LABORATORISTA").item(0).getTextContent();
                    String pathOrder = element.getElementsByTagName("ORDEN").item(0).getTextContent();
                    String pathInform = element.getElementsByTagName("INFORME").item(0).getTextContent();
                    String dateResult = element.getElementsByTagName("FECHA").item(0).getTextContent();
                    String timeResult = element.getElementsByTagName("HORA").item(0).getTextContent();    
                    File orderF;
                    if (pathOrder.equals("")) {
                        orderF = null;
                    }else{
                        orderF = new File(pathFolder+""+pathOrder);
                    }
                    File informF = new File(pathFolder+""+pathInform);
                    
                    labDB.insertResult(code, orderF, informF, dateResult, timeResult, codeLabWorker, codePatient, codeMedic, codeExam);
                }
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error insertando Result", JOptionPane.ERROR_MESSAGE);
        }                
    }
        
    public void insertAppointments(NodeList listAppointments){
        PatientDB patientDB = new PatientDB();
        AdminDB admin = new AdminDB();        
        try{
            for(int i = 0 ; i < listAppointments.getLength() ; i++) {
                Node nodo = listAppointments.item(i);                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String code = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String codePatient = element.getElementsByTagName("PACIENTE").item(0).getTextContent();
                    String codeMedic = element.getElementsByTagName("MEDICO").item(0).getTextContent();
                    String nameSpecialty = element.getElementsByTagName("ESPECIALIDAD").item(0).getTextContent();
                    String dateAppointment = element.getElementsByTagName("FECHA").item(0).getTextContent();
                    String timeAppointment = element.getElementsByTagName("HORA").item(0).getTextContent();                    
                    String codeSpecialty = admin.searchCodeSpecialtyByName(nameSpecialty);
                    
                    patientDB.insertAppointment(code, dateAppointment, timeAppointment, codePatient, codeMedic, codeSpecialty);
                }
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void insertConsultations(NodeList listConsultations){
        AdminDB admin = new AdminDB();
        try{
            for(int i = 0 ; i < listConsultations.getLength() ; i++) {
                Node nodo = listConsultations.item(i);                                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;                    
                    String type = element.getElementsByTagName("TIPO").item(0).getTextContent();
                    Double cost = Double.parseDouble(element.getElementsByTagName("COSTO").item(0).getTextContent());                    
                    admin.insertConsultation(type, cost);
                }
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
