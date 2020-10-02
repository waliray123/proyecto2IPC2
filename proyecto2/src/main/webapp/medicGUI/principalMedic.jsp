<%-- 
    Document   : principalMedic
    Created on : 28/09/2020, 23:41:57
    Author     : user-ubunto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%String codeMedic = request.getParameter("username");%>
        <center>
        <h1>Pagina principal medico</h1>
        <div>
            <form METHOD="POST" ACTION="medicGUI/completeAppointment.jsp">
                <h3> Culminar consulta </h3>
                <input type="submit" value="Culminar consulta" />
                <input type="hidden" value="<%=codeMedic%>" name="codeMedic">
            </form>            
        </div>
        </center>
    </body>
</html>
