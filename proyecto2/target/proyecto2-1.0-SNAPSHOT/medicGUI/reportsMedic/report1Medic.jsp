<%-- 
    Document   : report1Medic
    Created on : 6/10/2020, 10:14:07
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.MedicControlers.MedicControl"%>
<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page import="com.mycompany.proyecto2.Utils.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%  String codeMedic = request.getParameter("codeMedic");
        MedicControl medicC = new MedicControl();        
    %>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte 1</title>
    </head>
    <body>
        <center>
        <h1>Reporte de citas agendadas en un intervalo de tiempo</h1>
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
            ArrayList<Appointment> appointments = medicC.getAppointmentsByCodeMedicAndDate(codeMedic, date1, date2);
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
