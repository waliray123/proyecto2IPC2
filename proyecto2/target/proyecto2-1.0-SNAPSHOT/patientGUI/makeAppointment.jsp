<%-- 
    Document   : makeAppointment
    Created on : 30/09/2020, 15:20:49
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.Medic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.PatientControlers.PatientControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%PatientControl patientC = new PatientControl();
    ArrayList<Medic> medics = null;
    if (request.getParameter("searchMedic") != null) {
        String name = request.getParameter("nameSearch");
        String specialty = request.getParameter("specialtySearch");
        String time = request.getParameter("timeSearch");
        medics = patientC.getFilterMedics(name, specialty, time);
    }else{
        medics = patientC.getAllMedics();
    }
    String codePatient = request.getParameter("codePatient");
%>     
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendar cita</title>
    </head>    
    <body> 
        <center>
        <div class="formulario3">
        <h1>Agendar una cita</h1>
        <div>
            <form class="formulario2">
                <input type="text" name="nameSearch" placeholder="Juan Perez"/>
                <input type="text" name="specialtySearch" placeholder="Especialidad"/>
                <input type="time" name="timeSearch" placeholder="Hora de Atencion"/>
                <input type="hidden" value="<%=codePatient%>" name="codePatient">
                <input type="submit" name ="searchMedic" value = "Buscar medico" class="boton2">
            </form>    
        </div>
        <h2>Medicos disponibles</h2>
        <table class = "table">
            <tr>
                <th>Codigo</th>
                <th>Nombre</th>
                <th>Colegiado</th>
                <th>DPI</th>                
                <th>Correo</th>
                <th>Especialidades</th>
                <th>Hora de entrada</th>
                <th>Hora de salida</th>
                <th>Fecha que inicio a trabajar </th>
            </tr>
            <%for (Medic medic : medics) {%>
                <tr>
                  <th><%=medic.getCode()%></th>  
                  <th><%=medic.getName()%></th>  
                  <th><%=medic.getCollegiate()%></th>  
                  <th><%=medic.getDPI()%></th>  
                  <th><%=medic.getMail()%></th>  
                  <th>
                      <%
                          for (String specialty : medic.getSpecialties()) {%>
                             <%=specialty%><br>     
                      <%    }
                      %>
                  </th>  
                  <th><%=medic.getInitTime()%></th>  
                  <th><%=medic.getFinalTime()%></th>  
                  <th><%=medic.getInitWork()%></th> 
                </tr>
            <%    }
            %>
        </table>
        <br>
        <h3>Verificar si el medico esta libre</h3>
        <form class="formulario2">
            <input type="text" name="codeMedic">            
            <input type="date" name="dateSearch">
            <input type="time" name="timeSearch">
            <input type="hidden" value="<%=codePatient%>" name="codePatient"><br>
            <input type="submit" value = "Ver Horario" name="seeShedule" class="boton2">
        </form>            
            <%if (request.getParameter("seeShedule") != null) {
                String codeMedic = request.getParameter("codeMedic");
                String date = request.getParameter("dateSearch");
                String time = request.getParameter("timeSearch");
                boolean isFree = patientC.reviewIfMedicIsFree(date, time, codeMedic);   
                if (isFree) {%>
                   <p>Si esta libre en ese horario</p>
                <%}else{%>
                   <p>No esta libre en ese horario<p> 
                <%}
            }            
            %>
        <h3>Agendar cita</h3>
            <legend>Ingresar datos</legend>
        <form class="formulario2">
            <p>Codigo de paciente: <%=codePatient%> </p>
            <p>Codigo Medico: </p><input type="text" name="codeMedic" >                              
            <p>Fecha: </p><input type="date" name="dateSearch">
            <p>Hora: </p><input type="time" name="timeSearch">
            <p>Especialidad: </p><input type="text" name="specialty"><br>
            <input type="submit" value = "Agendar" name="shedule" class="boton2">    
            <input type="hidden" value="<%=codePatient%>" name="codePatient">
        </form>
            <%if (request.getParameter("shedule") != null) {
                    codePatient =request.getParameter("codePatient");         
                    String codeMedic =request.getParameter("codeMedic");         
                    String dateAppointment =request.getParameter("dateSearch");         
                    String timeAppointment =request.getParameter("timeSearch");         
                    String specialty =request.getParameter("specialty");         
                    patientC.sheduleAppointment(codePatient, codeMedic, dateAppointment, timeAppointment, specialty);
                }
            %>
       </div>       
       </center>
       <form METHOD="POST" ACTION="/proyecto2/patientGUI/principalPatient.jsp">   
            <center>
                <input type="hidden" value="<%=codePatient%>" name="username">
                <br><br><input type="submit" value="Regresar" class="boton"/>
            </center>
        </form>
    </body>
</html>
