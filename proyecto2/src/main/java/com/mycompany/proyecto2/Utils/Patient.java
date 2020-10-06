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
public class Patient {
    private String code;
    private String name;
    private String gender;
    private String birth;
    private String DPI;
    private String phone;
    private String weight;
    private String typeBlood;
    private String mail;   

    public Patient(String code, String name, String gender, String birth, String DPI, String phone, String weight, String typeBlood, String mail) {
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.DPI = DPI;
        this.phone = phone;
        this.weight = weight;
        this.typeBlood = typeBlood;
        this.mail = mail;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public String getDPI() {
        return DPI;
    }

    public String getPhone() {
        return phone;
    }

    public String getWeight() {
        return weight;
    }

    public String getTypeBlood() {
        return typeBlood;
    }

    public String getMail() {
        return mail;
    }
    
    
    
}
