/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2.Utils;

import java.io.File;
import java.sql.Blob;


/**
 *
 * @author user-ubunto
 */
public class Result {
    private String code;
    private Blob orderResult;
    private Blob inform;
    private String date;
    private String time;
    private String codeLabWorker;
    private String codePatient;
    private String codeMedic;
    private String codeExam;

    public Result(String code, Blob orderResult, Blob inform, String date, String time, String codeLabWorker, String codePatient, String codeMedic, String codeExam) {
        this.code = code;
        this.orderResult = orderResult;
        this.inform = inform;
        this.date = date;
        this.time = time;
        this.codeLabWorker = codeLabWorker;
        this.codePatient = codePatient;
        this.codeMedic = codeMedic;
        this.codeExam = codeExam;
    }

    public String getCode() {
        return code;
    }

    public Blob getOrderResult() {
        return orderResult;
    }

    public Blob getInform() {
        return inform;
    }    

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCodeLabWorker() {
        return codeLabWorker;
    }

    public String getCodePatient() {
        return codePatient;
    }

    public String getCodeMedic() {
        return codeMedic;
    }

    public String getCodeExam() {
        return codeExam;
    }
    
    
    
    
}
