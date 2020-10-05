<%-- 
    Document   : report6Admin
    Created on : 4/10/2020, 14:33:26
    Author     : user-ubunto
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.AdminControlers.AdminReportsDB"%>
<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page import="com.mycompany.proyecto2.Utils.DateQuantity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte 6</title>
    </head>
    <body>
        <center>
        <h1>Ingresos generados por paciente en un intervalo de tiempo.</h1>
        <form>
            <p>Fecha de inicio</p>
            <input type="date" name="date1"><br>
            <p>Fecha de Final</p>
            <input type="date" name="date2"><br>
            <input type="submit" name="selectDate">
        </form>
        <%if (request.getParameter("selectDate") != null) {
                RegularConsultations regCon = new RegularConsultations();
                String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");
                AdminReportsDB adminRDB = new AdminReportsDB();
                ArrayList<DateQuantity> patientsQ = adminRDB.getAllIncomesPatientsByDate(date1, date2);
                String namePatient = "";%>
        <table class = "table">
            <tr>
                <th>Codigo Medico</th>
                <th>Nombre de Medico</th>
                <th>Ingresos del medico en Q.</th>
            </tr>            
            <%for (DateQuantity medicQ : patientsQ) {
                namePatient = regCon.searchNamePatientByCode(medicQ.getDate());%>
            <tr>
                <td><%=medicQ.getDate()%></td>
                <td><%=namePatient%></td>
                <td><%=medicQ.getQuantity()%></td>                
            </tr>        
            <%}%>  
        </table>
        <%}%>
        </center>
    </body>
</html>
