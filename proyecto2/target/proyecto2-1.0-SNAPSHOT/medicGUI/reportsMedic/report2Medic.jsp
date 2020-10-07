<%-- 
    Document   : report2Medic
    Created on : 6/10/2020, 10:14:21
    Author     : user-ubunto
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.MedicControlers.MedicControl"%>
<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page import="com.mycompany.proyecto2.Utils.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%  String codeMedic = request.getParameter("codeMedic");
        MedicControl medicC = new MedicControl();        
        Date objDate = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);       
        ArrayList<Appointment> appointments = medicC.getAppointmentsPendingByCodeMedicAndDate(codeMedic, objSDF.format(objDate));
    %>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte 2</title>
    </head>
    <body>
        <center>
        <h1>Reporte de citas para el día en curso.</h1>
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
        </center>
    </body>
</html>
