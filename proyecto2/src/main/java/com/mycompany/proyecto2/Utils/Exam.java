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
public class Exam {
    private String code;
    private String name;
    private boolean useOrder;
    private String description;
    private Double cost;
    private String reportType;

    public Exam(String code, String name, boolean useOrder, String description, Double cost, String reportType) {
        this.code = code;
        this.name = name;
        this.useOrder = useOrder;
        this.description = description;
        this.cost = cost;
        this.reportType = reportType;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isUseOrder() {
        return useOrder;
    }

    public String getDescription() {
        return description;
    }

    public Double getCost() {
        return cost;
    }

    public String getReportType() {
        return reportType;
    }
    
    
}
