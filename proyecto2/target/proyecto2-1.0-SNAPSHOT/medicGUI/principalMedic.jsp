<%-- 
    Document   : principalMedic
    Created on : 28/09/2020, 23:41:57
    Author     : user-ubunto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%String codeMedic = request.getParameter("username");%>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    
        <center>
        <h1>Pagina principal medico</h1>
        <h3>Codigo de Medico: <%=codeMedic%></h3>
        </center>
        <div = class="formulario3">
        <div class="contenedor">
        <div>
            <h2>Opciones</h2>
            <form METHOD="POST" ACTION="/proyecto2/medicGUI/completeAppointment.jsp">
                <h3> Culminar consulta </h3>
                <input type="submit" value="Culminar consulta" class="boton"/>
                <input type="hidden" value="<%=codeMedic%>" name="codeMedic" >
            </form>            
            <form METHOD="POST" ACTION="/proyecto2/patientGUI/medicalHistory.jsp">
                <h3> Ver Historial medico de un Paciente </h3>
                <input type="submit" value="Ver Historial" class="boton" />
                <input type="hidden" value="<%=codeMedic%>" name="codeMedic">
            </form>            
        </div>
        <div>
            <h2>Reportes Medico</h2>
        
            <form METHOD="POST" ACTION="/proyecto2/medicGUI/reportsMedic/report1Medic.jsp">
                <h3> Reporte de citas agendadas en un intervalo de tiempo </h3>
                <input type="submit" value="Ver Citas" class="boton"/>
                <input type="hidden" value="<%=codeMedic%>" name="codeMedic">
            </form>            

            <form METHOD="POST" ACTION="/proyecto2/medicGUI/reportsMedic/report2Medic.jsp">
                <h3> Reporte de citas para el día en curso. </h3>
                <input type="submit" value="Ver Citas" class="boton"/>
                <input type="hidden" value="<%=codeMedic%>" name="codeMedic">
            </form>            

            <form METHOD="POST" ACTION="/proyecto2/medicGUI/reportsMedic/report3Medic.jsp">
                <h3> Los pacientes con mayor cantidad de informes médicos dentro de un intervalo de tiempo. </h3>
                <input type="submit" value="Ver Pacientes" class="boton"/>
                <input type="hidden" value="<%=codeMedic%>" name="codeMedic">
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
