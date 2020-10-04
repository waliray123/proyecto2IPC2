<%-- 
    Document   : report2Patient
    Created on : 3/10/2020, 23:11:41
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page import="com.mycompany.proyecto2.PatientControlers.PatientReportsDB"%>
<%@page import="com.mycompany.proyecto2.Utils.Result"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="com.mycompany.proyecto2.PatientControlers.PatientDB"%>
<%@page import="com.mycompany.proyecto2.Utils.Exam"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%  String codePatient = request.getParameter("codePatient");
        PatientDB patientDB = new PatientDB();        
        PatientReportsDB patientRDB = new PatientReportsDB();
        ArrayList<Exam> allExams = patientDB.getAllExams();
    %>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte 2</title>
    </head>
    <body>
    <center>
        <h1>Exámenes realizados de un tipo en específico dentro de un intervalo de tiempo.</h1>
        <form>
            <h4>Tipo de examen</h4>
            <select name="namesExams">
                <%for (Exam exam : allExams) {%>
                    <option><%=exam.getName()%></option>
                <%    }
                %>
            </select><br>
            <h4>Fecha de inicio</h4>
            <input type="date" name="date1"><br>            
            <h4>Fecha de final</h4>
            <input type="date" name="date2"><br>
            <input type="submit" value="Seleccionar Examen" name="selectExam">
            <input type="hidden" value="<%=codePatient%>" name="codePatient">
        </form>
            <%if (request.getParameter("selectExam") != null) {
                
                String nameExam = request.getParameter("namesExams");
                Exam examSearch = null;
                String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");
                for (int i = 0; i < allExams.size(); i++) {
                        Exam exam = allExams.get(i);
                        if (exam.getName().equals(nameExam)) {
                                examSearch = exam;
                        }
                    }
                String codeExam = examSearch.getCode();
                ArrayList<Result> results = patientRDB.getResultsByExamAndDate(codePatient, date1, date2, codeExam);%>
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
            <%    }
                
            %>
    </center>
</body>
</html>
