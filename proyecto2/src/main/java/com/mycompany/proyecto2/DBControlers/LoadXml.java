/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.DBControlers;

import com.mycompany.proyecto2.AdminControlers.AdminDB;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.util.logging.Logger;
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
            
    
    public void chooseFileXML(){
        try {            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivo XML", "xml"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == APPROVE_OPTION) {
                readXML(fileChooser.getSelectedFile().getPath());                        
            } 
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void readXML(String path){
        try {            
            File fileXML = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            
            Document document = documentBuilder.parse(fileXML);
            
            document.getDocumentElement().normalize();           
            
            NodeList listExams = document.getElementsByTagName("examen");            
            insertExams(listExams);
            
            
        } catch(Exception e) {
            
        }                
    }

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

}
