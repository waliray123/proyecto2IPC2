<%-- 
    Document   : completeAppointment
    Created on : 1/10/2020, 17:13:29
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page import="com.mycompany.proyecto2.MedicControlers.MedicControl"%>
<%@page import="com.mycompany.proyecto2.Utils.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
        <title>Culminar Consulta</title>
    </head>
    <body>
        <h1>Culminar Consulta</h1>
        <%if (request.getParameter("selectAppointment") == null) {%>                              
        <form method="POST" class="formulario">
            <h3>Seleccione la consulta</h3>
            <p>Solo se muestran las consultas que tiene pendientes en el dia</p>  
            <select name="appointments">
                <%for (Appointment appointment : appointments) {
                        String code = appointment.getCode();%>
                        <option><%=code%></option>
                <%    }
                %>                
            </select>
            <input type="hidden" value="<%=codeMedic%>" name="codeMedic">
            <input type="submit" value="Seleccionar cita" name = "selectAppointment">
        </form>
        <%} else if(request.getParameter("appointments").equals("") == false){
            RegularConsultations regCon = new RegularConsultations();            
            String codeAppointment = request.getParameter("appointments");
            Appointment appointment = regCon.getAppointmentByCode(codeAppointment);
            String dateToday = objSDF.format(objDate);
            String codePatient = appointment.getCodePatient();
            String timeAppointment = appointment.getTime();  
            String codeInform = medicC.getLastCodeInform();
        %>
        <form class="formulario3"> 
            <center>
            <h4>Codigo del informe:</h4> <p><%=codeInform%></p>
            <h4>Codigo de la cita:</h4> <p><%=codeAppointment%></p>
            <h4>Codigo del Medico:</h4> <p><%=codeMedic%></p>
            <h4>Codigo del Paciente:</h4> <p><%=codePatient%></p>
            <h4>Fecha de Hoy:</h4> <p><%=dateToday%></p>
            <h4>Hora de la cita:</h4> <p><%=timeAppointment%></p>
            <h4>Descripcion de la consulta (Informe):</h4>
            <textarea name="inform" rows="10" cols="45"></textarea><br>
            <input type="hidden" value="<%=codeMedic%>" name="codeMedic">
            <input type="hidden" value="<%=codePatient%>" name="codePatient">
            <input type="hidden" value="<%=codeAppointment%>" name="codeAppointment">
            <input type="hidden" value="<%=dateToday%>" name="dateToday">
            <input type="hidden" value="<%=timeAppointment%>" name="timeAppointment">
            <input type="hidden" value="<%=codeInform%>" name="codeInform">
            <input type="submit" value="Guardar informe" name = "sendInform">
            </center>
        </form>
        <%}%>
        <%if(request.getParameter("sendInform") != null){
            String codeAppointment = request.getParameter("codeAppointment");
            String dateToday = request.getParameter("dateToday");
            String codePatient = request.getParameter("codePatient");
            String timeAppointment = request.getParameter("timeAppointment");  
            String codeInform = request.getParameter("codeInform"); 
            String dataInform = request.getParameter("inform"); 
            medicC.insertInform(codeInform, dateToday, timeAppointment, dataInform, codePatient, codeMedic);
        }
        %>
        <form METHOD="POST" ACTION="/proyecto2/medicGUI/principalMedic.jsp">   
            <center>
                <input type="hidden" value="<%=codeMedic%>" name="username">
                <br><br><input type="submit" value="Regresar" class="boton"/>
            </center>
        </form>
    </body>
</html>
