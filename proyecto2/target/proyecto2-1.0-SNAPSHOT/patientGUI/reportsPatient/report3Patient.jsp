<%-- 
    Document   : report3Patient
    Created on : 4/10/2020, 10:53:19
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.PatientControlers.PatientReportsDB"%>
<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%  String codePatient = request.getParameter("codePatient");
        PatientReportsDB patientRDB = new PatientReportsDB();
        ArrayList<Appointment> appointments = patientRDB.getLast5AppointmentsByCodePatient(codePatient);
    %>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte 3</title>
    </head>
    <body>
        <center>
        <h1>Ultimas 5 consultas realizadas</h1>
        <table class = "table">
            <tr>
                <th>Codigo</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Codigo Paciente</th>
                <th>Codigo de Medico</th>
                <th>Codigo de Especialidad</th>
            </tr>
            <%for (Appointment appointment : appointments) {
            RegularConsultations regCon = new RegularConsultations();
            String codeSpecialty = appointment.getCodeSpecialty();
            String nameSpecialty = regCon.getNameSpecialtyByCode(codeSpecialty);
            %>
            <tr>
                <th><%=appointment.getCode()%></th>                
                <th><%=appointment.getDate()%></th>
                <th><%=appointment.getTime()%></th>
                <th><%=appointment.getCodePatient()%></th>
                <th><%=appointment.getCodeMedic()%></th>
                <th><%=nameSpecialty%></th>
            </tr>
            <%    }
            %>
        </table>
        </center>
    </body>
</html>
