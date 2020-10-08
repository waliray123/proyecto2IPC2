

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%String namePatient = request.getParameter("username");
            if (namePatient == null) {
                namePatient = String.valueOf(request.getAttribute("username"));
            }
        %>
        <h1>Pagina principal paciente</h1>
        <h2>Codigo de Paciente: <%=namePatient%></h2>
        <div class="formulario3">
        <div class="contenedor">        
        <div>
            <h3>Opciones</h3>
            <form METHOD="POST" ACTION="/proyecto2/patientGUI/makeAppointment.jsp">
                <h3> Agendar Consulta </h3>
                <input type="submit" value="Agendar Cita" class="boton"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
            </form>
            <form METHOD="POST" ACTION="/proyecto2/patientGUI/reviewAppointments.jsp">
                <h3> Ver mis citas y examenes pendientes </h3>
                <input type="submit" value="Ver citas" class="boton"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
            </form>
            <form METHOD="POST" ACTION="/proyecto2/patientGUI/sheduleExam.jsp">
                <h3> Agendar Examen de Laboratorio </h3>
                <input type="submit" value="Agendar Examen" class="boton"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
            </form>
            <form METHOD="POST" ACTION="/proyecto2/patientGUI/medicalHistory.jsp">
                <h3> Ver mi historial medico </h3>
                <input type="submit" value="Ver historial" class="boton"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
            </form>
        </div>         
        <div>
            <h2>Reportes de Paciente</h2>
            <form METHOD="POST" ACTION="/proyecto2/patientGUI/reportsPatient/report1Patient.jsp">
                <h3>5 examenes de laboratorio mas recientes</h3>
                <input type="submit" value="Ver examenes" class="boton"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
                <input type="hidden" value="1" name="report">
            </form>
            <form METHOD="POST" ACTION="/proyecto2/patientGUI/reportsPatient/report2Patient.jsp">
                <h3>Buscar examenes segun tipo  y rango de fecha</h3>
                <input type="submit" value="Ver examenes" class="boton"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
                <input type="hidden" value="2" name="report">
            </form>
            <form METHOD="POST" ACTION="/proyecto2/patientGUI/reportsPatient/report3Patient.jsp">
                <h3>Ultimas 5 consultas realizadas</h3>
                <input type="submit" value="Ver consultas" class="boton"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
                <input type="hidden" value="3" name="report">
            </form>
            <form METHOD="POST" ACTION="/proyecto2/patientGUI/reportsPatient/report4Patient.jsp">
                <h3>Consultas realizadas con un médico en específico dentro de un intervalo de tiempo</h3>
                <input type="submit" value="Ver consultas" class="boton"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
                <input type="hidden" value="4" name="report">
            </form>
        </div>
        </div>
        </div>
        <form>   
            <center>
                <br><br><input type="button" value="Salir" class="boton" onclick="location.href = '/proyecto2/index.jsp'"/>
            </center>
        </form>
    </body>
</html>
