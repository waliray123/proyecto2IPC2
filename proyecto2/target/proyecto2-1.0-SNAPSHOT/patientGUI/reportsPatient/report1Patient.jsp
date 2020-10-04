<%-- 
    Document   : report1Patient
    Created on : 3/10/2020, 22:12:39
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.Exam"%>
<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page import="com.mycompany.proyecto2.PatientControlers.PatientReportsDB"%>
<%@page import="com.mycompany.proyecto2.Utils.Result"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
    <%  String codePatient = request.getParameter("codePatient");
        PatientReportsDB patientRDB = new PatientReportsDB();
        ArrayList<Result> results = patientRDB.getMostRecentResults(codePatient);
    %>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte 1</title>
    </head>
    <body>
        <center>
        <h1>Ultimos 5 examenes de laboratorio realizados</h1>
        <table class = "table">
            <tr>
                <th>Codigo</th>
                <th>Codigo de paciente</th>
                <th>Codigo de Medico</th>
                <th>Codigo de Laboratorista</th>
                <th>Fecha de Entrega</th>
                <th>Hora de Entrega</th>
                <th>Tipo de Examen</th>
            </tr>
            <%for (Result result : results) {
            String codeMedic = result.getCodeMedic();
            if (codeMedic == null) {
                    codeMedic = "";
                }
            RegularConsultations regCon = new RegularConsultations();
            Exam typeExam = regCon.getExamByCode(result.getCodeExam());
            String nameTypeExam = typeExam.getName();
            %>
            <tr>
                <th><%=result.getCode()%></th>
                <th><%=result.getCodePatient()%></th>
                <th><%=codeMedic%></th>
                <th><%=result.getCodeLabWorker()%></th>
                <th><%=result.getDate()%></th>
                <th><%=result.getTime()%></th>
                <th><%=nameTypeExam%></th>
            </tr>
            <%    }
            %>
        </center>
    </body>
</html>
