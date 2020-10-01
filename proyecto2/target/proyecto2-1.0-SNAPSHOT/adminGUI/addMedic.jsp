
<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.AdminControlers.AdminControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%AdminControl adminC = new AdminControl();%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Medico</title>
    </head>
    <body>        
        <h1>Agregar Medico</h1>
        <form>
            <h2>Llene los siguientes campos para ingresar al medico</h2>
            <h3>Codigo</h3>
            <input type="text" name="code" placeholder="MED-000"/><br>
            <h3>Nombre</h3>
            <input type="text" name="name" placeholder="Juan Perez"/><br>
            <h3>Numero de colegiado</h3>
            <input type="text" name="collegiate" placeholder="0001"/><br>
            <h3>DPI</h3>
            <input type="text" name="DPI" /><br>
            <h3>Telefono</h3>
            <input type="text" name="phone" placeholder="87654321"/><br>                
            <h3>Correo Electronico</h3>
            <input type="text" name="email" placeholder="medico0@correo.com"/><br>
            <h3>Hora de entrada</h3>
            <input type="time" name="initTime"/><br>
            <h3>Hora de salida</h3>
            <input type="time" name="finalTime" /><br>
            <h3>Fecha en la que inicio a trabajar</h3>
            <input type="date" name="initDate" /><br>   
            <h3>Contraseña</h3>
            <input type="text" name="password" /><br>               
            <h3>Especialidades</h3>
            <%ArrayList<String> specialties = adminC.getAllSpecialties();
            for (String specialty : specialties) {%>
            <label><input type="checkbox" name="cbox" value="<%=specialty%>"> <%=specialty%></label><br>
            <%}%>                        
                <input type="submit" name="add" value="Agregar Medico" />
        </form>
    </body>
    <%if (request.getParameter("add") != null) {
        String[] specialtiesChecked = request.getParameterValues("cbox");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String collegiate = request.getParameter("collegiate");
        String DPI = request.getParameter("DPI");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String initTime = request.getParameter("initTime");
        String finalTime = request.getParameter("finalTime");        
        String initDate = request.getParameter("initDate");        
        String password = request.getParameter("password");        
        boolean created = adminC.addNewMedic(specialtiesChecked, code, name, collegiate, DPI, phone, email, password, initTime, finalTime, initDate);        
        if (created == false) {
            JOptionPane.showMessageDialog(null,"Ocurrio un error al crear el medico, Revise que sus datos sean correctos");
        }
      }
    %>
</html>
