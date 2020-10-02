

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
    </body>
</html>
