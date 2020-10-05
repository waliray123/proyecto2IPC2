<%-- 
    Document   : report4Admin
    Created on : 4/10/2020, 14:33:09
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.Exam"%>
<%@page import="com.mycompany.proyecto2.Utils.DateQuantity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.AdminControlers.AdminReportsDB"%>
<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>Los exámenes de laboratorio más demandados en un intervalo de tiempo.</h1>
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
                ArrayList<DateQuantity> examsQ = adminRDB.getMostDemandedExam(date1, date2);
                Exam exam;%>
        <table class = "table">
            <tr>
                <th>Codigo Examen</th>
                <th>Nombre de Examen</th>
                <th>Cantidad de Examenes</th>
            </tr>            
            <%for (DateQuantity examQ : examsQ) {
                exam = regCon.getExamByCode(examQ.getDate());%>
            <tr>
                <td><%=examQ.getDate()%></td>
                <td><%=exam.getName()%></td>
                <td><%=examQ.getQuantity()%></td>                
            </tr>        
            <%}%>  
        </table>
        <%}%>
    </center>
</body>
</html>
