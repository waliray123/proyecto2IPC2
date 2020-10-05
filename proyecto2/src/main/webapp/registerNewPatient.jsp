<%-- 
    Document   : registerNewPatient
    Created on : 4/10/2020, 18:37:36
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.DBControlers.PrivateInserts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo paciente</title>
    </head>
    <body>
        <center>
        <h1>Registrarste</h1>
        <h3>Si deseas registrarte como paciente debes llenar el siguiente formulario</h3>
        <form>
            <p>Codigo de Paciente</p>
            <input type="text" name="code">
            <p>Nombre</p>
            <input type="text" name="name">
            <p>Genero</p>
            <input type="text" name="gender">
            <p>Fecha de nacimiento</p>
            <input type="date" name="birth">
            <p>DPI</p>
            <input type="text" name="DPI">
            <p>Telefono</p>
            <input type="text" name="phone">
            <p>Peso en libras</p>
            <input type="text" name="weight">
            <p>Tipo de sangre</p>
            <input type="text" name="type_blood">
            <p>Correo</p>
            <input type="text" name="email">
            <p>Contraseña</p>
            <input type="text" name="password"><br>
            <input type="submit" name="register">
        </form>
        <br>
        <br>
        <br>
        <input type="button" value="Regresar" onclick="location.href='index.html'">
        </center>
        <%if (request.getParameter("register") != null) {
            PrivateInserts privInsert = new PrivateInserts();
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String birth = request.getParameter("birth");
            String DPI = request.getParameter("DPI");
            String phone = request.getParameter("phone");
            String weight = request.getParameter("weight");
            String typeBlood = request.getParameter("type_blood");
            String mail = request.getParameter("email");
            String password = request.getParameter("password");
            boolean isReg = privInsert.registerPatient(code, name, gender, birth, DPI, phone, weight, typeBlood, mail, password);            
            if (isReg == true) {%>
            <center>Se registro correctamente</center>
        <%    }else{%>
            <center>Ocurrio un error vuelva a intentarlo</center>                
            <%}
            }
        %>
    </body>
</html>
