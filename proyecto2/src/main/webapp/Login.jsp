<%-- 
    Document   : LoginMedic
    Created on : 26/09/2020, 11:16:57
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.DBControlers.LoadXml"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Medico</title>
    </head>
    <body>
        <%if (request.getParameter("username") == null && request.getParameter("password") == null){%>
        <form METHOD="POST" ACTION="Login.jsp">
            <center>
            <h1>Iniciar sesiòn</h1>        
            <h2>Ingrese su codigo</h2>
            <input type="text" name="username" /><br>
            <h2>Ingrese su contraseña</h2>
            <input type="text" name="password" /><br><br>
            <input type="submit" value="Calcular" />
            </center>
        </form>
        <%} else {%>
            <%LoadXml loadxml = new LoadXml();%>
            <%loadxml.chooseFileXML();%>
        <%}%>
    </body>
</html>
