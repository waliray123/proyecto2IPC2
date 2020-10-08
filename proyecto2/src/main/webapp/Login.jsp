
<%@page import="com.mycompany.proyecto2.DBControlers.LoadXml"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>                  
        <center >
            <h1>Iniciar sesion</h1>  
        <form METHOD="POST" ACTION="LoginServlet" class="formulario">                                    
                <h2>Ingrese su codigo</h2>
                <input type="text" name="username" /><br>
                <h2>Ingrese su contraseña</h2>
                <input type="password" name="userPassword" /><br><br>            
                <input type="submit" value="Ingresar" class="boton"/>            
        </form>
            <br><input type="button" value="Regresar" onclick="location.href='index.jsp'" class="boton">
        </center>
    </body>
</html>
