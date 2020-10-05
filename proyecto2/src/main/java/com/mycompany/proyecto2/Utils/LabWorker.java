/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.Utils;

/**
 *
 * @author user-ubunto
 */
public class LabWorker {
    
    private String code;
    private String name;
    private String registry;
    private String DPI;
    private String phone;
    private String mail;
    private String initWork;
    private String ExamCode;

    public LabWorker(String code, String name, String registry, String DPI, String phone, String mail, String initWork, String ExamCode) {
        this.code = code;
        this.name = name;
        this.registry = registry;
        this.DPI = DPI;
        this.phone = phone;
        this.mail = mail;
        this.initWork = initWork;
        this.ExamCode = ExamCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getRegistry() {
        return registry;
    }

    public String getDPI() {
        return DPI;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getInitWork() {
        return initWork;
    }

    public String getExamCode() {
        return ExamCode;
    }
    
    
    
}
