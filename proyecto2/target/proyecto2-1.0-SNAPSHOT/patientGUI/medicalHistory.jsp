<%-- 
    Document   : medicalHistory
    Created on : 5/10/2020, 09:10:35
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.Result"%>
<%@page import="com.mycompany.proyecto2.Utils.Appointment"%>
<%@page import="com.mycompany.proyecto2.Utils.Exam"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.Utils.Patient"%>
<%@page import="com.mycompany.proyecto2.PatientControlers.PatientControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%  PatientControl patientC = new PatientControl();
        String codePatient = "";
        codePatient = request.getParameter("codePatient");          
    %>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial Medico</title>
    </head>
    <body>
        <center>
        <h1>Historial Medico</h1>
        <%if (codePatient == null || codePatient.equals("")) {%>
        <form>
            <p>Escribe el codigo del paciente</p> 
            <input type="text" name="codePatient">
            <input type="submit" name="searchPatient" value="Buscar paciente">
        </form>
        <%}else{
            Patient patient = patientC.patientExist(codePatient);
            if (patient == null) {%>
            <p>El codigo del paciente no existe<p> 
            <form>
                <p>Escribe el codigo del paciente</p> 
                <input type="text" name="codePatient">
                <input type="submit" name="searchPatient" value="Buscar paciente">
            </form>
        <%  }else{%>
        <h3>Informacion del paciente</h3>
            <table class = "table">
            <tr>
                <th>Codigo de Paciente</th>
                <th>Nombre</th>
                <th>Genero</th>
                <th>Fecha de nacimiento</th>
                <th>DPI</th>
                <th>Telefono</th>
                <th>Peso</th>
                <th>Tipo de Sangre</th>
                <th>Correo</th>
            </tr>
            <tr>
                <td><%=patient.getCode()%></td>
                <td><%=patient.getName()%></td>
                <td><%=patient.getGender()%></td>
                <td><%=patient.getBirth()%></td>
                <td><%=patient.getDPI()%></td>
                <td><%=patient.getPhone()%></td>
                <td><%=patient.getWeight()%></td>
                <td><%=patient.getTypeBlood()%></td>
                <td><%=patient.getMail()%></td>
            </tr>
            </table>
            <h3>Examenes Realizados</h3>
            <table class = "table">
            <tr>
                <th>Codigo de examen</th>
                <th>Codigo de paciente</th>
                <th>Codigo de Medico</th>
                <th>Codigo de Laboratorista</th>
                <th>Fecha de Entrega</th>                
                <th>Hora de Entrega</th>
                <th>Tipo de Examen</th>
            </tr>
            <%ArrayList<Result> results = patientC.getResultsDeliveredByCodePatient(codePatient);
                for (Result result : results) {%>
                <tr>
                <td><%=result.getCode()%></td>
                <td><%=result.getCodePatient()%></td>
                <td><%=result.getCodeMedic()%></td>
                <td><%=result.getCodeLabWorker()%></td>
                <td><%=result.getDate()%></td>
                <td><%=result.getTime()%></td>
                <td><%=result.getCodeExam()%></td>
                </tr>
            <%    }
            %>            
            </table>
            <h3>Consultas Realizadas</h3>            
            <table class = "table">
            <tr>
                <th>Codigo Consulta</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Codigo Paciente</th>
                <th>Codigo Medico</th>                
                <th>Codigo Especialidad</th>
            </tr>
            <%ArrayList<Appointment> appointments = patientC.getAppointmentsBeforeByCodePatient(codePatient);
                for (Appointment appointment : appointments) {%>
                <tr>
                <td><%=appointment.getCode()%></td>
                <td><%=appointment.getDate()%></td>
                <td><%=appointment.getTime()%></td>
                <td><%=appointment.getCodePatient()%></td>
                <td><%=appointment.getCodeMedic()%></td>
                <td><%=appointment.getCodeSpecialty()%></td>
                </tr>
            <%    }
            %>            
            </table>            
        <%  }
        }
        %>
        </center>
    </body>
</html>
