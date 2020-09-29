
<%@page import="com.mycompany.proyecto2.DBControlers.LoadXml"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>        
        <form METHOD="POST" ACTION="LoginServlet">
            <center>
                <h1>Iniciar sesiòn</h1>        
                <h2>Ingrese su codigo</h2>
                <input type="text" name="username" /><br>
                <h2>Ingrese su contraseña</h2>
                <input type="password" name="userPassword" /><br><br>            
                <input type="submit" value="Ingresar" />
            </center>
        </form>        
    </body>
</html>
