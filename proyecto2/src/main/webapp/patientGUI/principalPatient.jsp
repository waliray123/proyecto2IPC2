

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%String namePatient = request.getParameter("username");%>
        <h1>Pagina principal paciente</h1>
        <div>
            <form METHOD="POST" ACTION="patientGUI/makeAppointment.jsp">
                <h3> Agendar Consulta </h3>
                <input type="submit" value="Agendar Cita" />
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
            </form>
        </div>   
        <div>
            <form METHOD="POST" ACTION="patientGUI/reviewAppointments.jsp">
                <h3> Ver mis citas y examenes pendientes </h3>
                <input type="submit" value="Ver citas" />
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
            </form>
        </div> 
        <div>
            <form METHOD="POST" ACTION="patientGUI/sheduleExam.jsp">
                <h3> Agendar Examen de Laboratorio </h3>
                <input type="submit" value="Agendar Examen" />
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
            </form>
        </div>
        <div>
            <form METHOD="POST" ACTION="patientGUI/medicalHistory.jsp">
                <h3> Ver mi historial medico </h3>
                <input type="submit" value="Ver historial" />
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
            </form>
        </div> 
        <h2>Reportes de Paciente</h2>
        <div>
            <form METHOD="POST" ACTION="patientGUI/reportsPatient/report1Patient.jsp">
                <h3>5 examenes de laboratorio mas recientes</h3>
                <input type="submit" value="Ver examenes"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
                <input type="hidden" value="1" name="report">
            </form>
            <form METHOD="POST" ACTION="patientGUI/reportsPatient/report2Patient.jsp">
                <h3>Buscar examenes segun tipo  y rango de fecha</h3>
                <input type="submit" value="Ver examenes"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
                <input type="hidden" value="2" name="report">
            </form>
            <form METHOD="POST" ACTION="patientGUI/reportsPatient/report3Patient.jsp">
                <h3>Ultimas 5 consultas realizadas</h3>
                <input type="submit" value="Ver consultas"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
                <input type="hidden" value="3" name="report">
            </form>
            <form METHOD="POST" ACTION="patientGUI/reportsPatient/report4Patient.jsp">
                <h3>Consultas realizadas con un médico en específico dentro de un intervalo de tiempo</h3>
                <input type="submit" value="Ver consultas"/>
                <input type="hidden" value="<%=namePatient%>" name="codePatient">
                <input type="hidden" value="4" name="report">
            </form>
        </div>
    </body>
</html>
