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
public class Appointment {
    private String code;
    private String date;
    private String time;
    private String codePatient;
    private String codeMedic;
    private String codeSpecialty;

    public Appointment(String code, String date, String time, String codePatient, String codeMedic, String codeSpecialty) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.codePatient = codePatient;
        this.codeMedic = codeMedic;
        this.codeSpecialty = codeSpecialty;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCodePatient() {
        return codePatient;
    }

    public String getCodeMedic() {
        return codeMedic;
    }

    public String getCodeSpecialty() {
        return codeSpecialty;
    }
    
    
    
}
