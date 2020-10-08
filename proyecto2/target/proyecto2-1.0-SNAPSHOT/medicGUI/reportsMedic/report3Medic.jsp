<%-- 
    Document   : report3Medic
    Created on : 6/10/2020, 10:14:33
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.DateQuantity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page import="com.mycompany.proyecto2.MedicControlers.MedicControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <%  String codeMedic = request.getParameter("codeMedic");
        MedicControl medicC = new MedicControl();        
    %>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte 3</title>
    </head>
    <body>
        <center>
        <h1>Los pacientes con mayor cantidad de informes médicos dentro de un intervalo de tiempo.</h1>
        <form>
            <p>Fecha de inicio</p>
            <input type="date" name="date1">
            <p>Fecha de Final</p>
            <input type="date" name="date2"> <br>
            <input type="hidden" value="<%=codeMedic%>" name="codeMedic">
            <input type="submit" value="Buscar" name="search">
        </form>
        <%if (request.getParameter("search") != null) {                        
            String date1 = request.getParameter("date1");
            String date2 = request.getParameter("date2");
            ArrayList<DateQuantity> patientsQ = medicC.getPatientsWithMoreInforms(codeMedic, date1, date2);
        %>                        
        <table class = "table">
            <tr>
                <th>Codigo de Paciente</th>
                <th>Cantidad de Informes </th>
            </tr>
            <%for (DateQuantity patientQ : patientsQ) {
//            RegularConsultations regCon = new RegularConsultations();
//            String codeSpecialty = appointment.getCodeSpecialty();
//            String nameSpecialty = regCon.getNameSpecialtyByCode(codeSpecialty);
            %>
            <tr>
                <th><%=patientQ.getDate()%></th>                
                <th><%=patientQ.getQuantity()%></th>
            </tr>
            <%    }
            %>
        </table>
        <%    }
        %>
        </center>
        <form METHOD="POST" ACTION="/proyecto2/medicGUI/principalMedic.jsp">   
            <center>
                <input type="hidden" value="<%=codeMedic%>" name="username">
                <br><br><input type="submit" value="Regresar" class="boton"/>
            </center>
        </form>
    </body>
</html>
