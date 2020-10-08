<%-- 
    Document   : report4Patient
    Created on : 4/10/2020, 11:35:59
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page import="com.mycompany.proyecto2.PatientControlers.PatientReportsDB"%>
<%@page import="com.mycompany.proyecto2.Utils.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%  String codePatient = request.getParameter("codePatient");
        
    %>    
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte 4</title>
    </head>
    <body>
        <center>
        <h1>Consultas realizadas con un médico en específico dentro de un intervalo de tiempo</h1>
        <form>
            <p>Fecha de inicio<p>
            <input type="date" name="date1"><br>
            <p>Fecha de final<p>
            <input type="date" name="date2"><br>
            <p>Codigo Medico<p>
            <input type="text" name="codeMedic"><br><br>
            <input type="submit" name="selectDate" value="Ver Consultas">
            <input type="hidden" value="<%=codePatient%>" name="codePatient">
        </form>
        <%if(request.getParameter("selectDate") != null){
            PatientReportsDB patientRDB = new PatientReportsDB();
            String codeMedic = request.getParameter("codeMedic");
            String date1 = request.getParameter("date1");
            String date2 = request.getParameter("date2");
            ArrayList<Appointment> appointments = patientRDB.getAppointmentByCodeMedicAndDate(codePatient, date1, date2, codeMedic);
        %>
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
        <%}%>
        </center>
        <form METHOD="POST" ACTION="/proyecto2/patientGUI/principalPatient.jsp">   
            <center>
                <input type="hidden" value="<%=codePatient%>" name="username">
                <br><br><input type="submit" value="Regresar" class="boton"/>
            </center>
        </form>
    </body>
</html>
