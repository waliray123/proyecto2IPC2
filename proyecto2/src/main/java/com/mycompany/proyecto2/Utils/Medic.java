/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.Utils;

import java.util.ArrayList;

/**
 *
 * @author user-ubunto
 */
public class Medic {
    
    private String code;
    private String name;
    private String collegiate;
    private String DPI;
    private String phone;
    private ArrayList<String> specialties;
    private String mail;
    private String initTime;
    private String finalTime;
    private String initWork;

    public Medic(String code, String name, String collegiate, String DPI, String phone, String mail, String initTime, String finalTime, String initWork) {
        this.code = code;
        this.name = name;
        this.collegiate = collegiate;
        this.DPI = DPI;
        this.phone = phone;
        this.mail = mail;
        this.initTime = initTime;
        this.finalTime = finalTime;
        this.initWork = initWork;
    }

    public void setSpecialties(ArrayList<String> specialties) {
        this.specialties = specialties;
    }
    
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCollegiate() {
        return collegiate;
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

    public String getInitTime() {
        return initTime;
    }

    public String getFinalTime() {
        return finalTime;
    }

    public String getInitWork() {
        return initWork;
    }

    public ArrayList<String> getSpecialties() {
        return specialties;
    }
    
    
    
}
