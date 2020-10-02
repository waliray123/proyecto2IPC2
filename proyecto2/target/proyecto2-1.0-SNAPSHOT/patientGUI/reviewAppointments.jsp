<%-- 
    Document   : reviewAppointments
    Created on : 1/10/2020, 00:48:52
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.Result"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.mycompany.proyecto2.Utils.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.PatientControlers.PatientControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%  PatientControl patientC = new PatientControl();
        String codePatient = request.getParameter("codePatient");
        Date objDate = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        ArrayList<Appointment> appointments = patientC.getAppointmentsByCodePatientAndDate(objSDF.format(objDate), codePatient);
        ArrayList<Result> results = patientC.getResultsPendingByCodePatient(codePatient);
    %>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver citas y consultas</title>
    </head>
    <body>
        <h1>Ver mis Citas Y Examenes Pendientes</h1>
        <h3>Citas Pendientes</h3>
        <table class = "table">
            <tr>
                <th>Codigo</th>
                <th>Codigo Paciente</th>
                <th>Nombre Medico</th>
                <th>Especialidad</th>                
                <th>Fecha</th>
                <th>Hora</th>
            </tr>
            <%for (Appointment appointment : appointments) {%>
            <tr>
                <td><%=appointment.getCode()%></td>
                <td><%=appointment.getCodePatient()%></td>
                <td><%=appointment.getCodeMedic()%></td>
                <td><%=appointment.getCodeSpecialty()%></td>
                <td><%=appointment.getDate()%></td>
                <td><%=appointment.getTime()%></td>
            </tr>
            <%    }                
            %>
        </table>
        <h3>Examenes Pendientes</h3>
        <table class = "table">
            <tr>
                <th>Codigo</th>
                <th>Codigo Paciente</th>
                <th>Nombre Medico</th>
                <th>Especialidad</th>                
                <th>Fecha</th>
                <th>Hora</th>
            </tr>
            <%for (Result exam : results) {%>
            <tr>
                <td><%=exam.getCode()%></td>
                <td><%=exam.getCodePatient()%></td>
                <td><%=exam.getCodeMedic()%></td>
                <td><%=exam.getCodeExam()%></td>
            </tr>
            <%    }                
            %>
        </table>
    </body>
</html>
