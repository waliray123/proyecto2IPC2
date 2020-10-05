<%-- 
    Document   : index
    Created on : 5/10/2020, 00:30:54
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.DBControlers.LoadXml"%>
<%@page import="com.mycompany.proyecto2.Utils.VerifyDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%VerifyDB verifyDB = new VerifyDB();
        String codeAdmin = verifyDB.search1Admin();
        if (codeAdmin.equals("")) {
            LoadXml loadXML = new LoadXml();
            loadXML.chooseFileXML();
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Principal jsp</title>
    </head>
    <body>
        <center>
        <h1>Hospital</h1>
        <h2>Bienvenido a nuestra pagina web</h2>                
        <button onclick="location.href='Login.jsp'">Ingresar</button>
        <h3>Registrarse como paciente</h3>
        <input type="button" value="Registrarse" onclick="location.href='registerNewPatient.jsp'">
        </center>
    </body>
</html>
