<%-- 
    Document   : addLabWorker
    Created on : 29/09/2020, 19:22:16
    Author     : user-ubunto
--%>

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
        <h1>Agregar Laboratorista</h1>
        <form>
            <h2>Llene los siguientes campos para ingresar al medico</h2>
            <h3>Codigo</h3>
            <input type="text" name="code" placeholder="LAB-000"/><br>
            <h3>Nombre</h3>
            <input type="text" name="name" placeholder="Juan Perez"/><br>
            <h3>Numero de Registro</h3>
            <input type="text" name="registry" placeholder="SALUD-0000001"/><br>
            <h3>DPI</h3>
            <input type="text" name="DPI" /><br>
            <h3>Telefono</h3>
            <input type="text" name="phone" placeholder="87654321"/><br>                
            <h3>Correo Electronico</h3>
            <input type="text" name="email" placeholder="lab0@correo.com"/><br>
            <h3>Fecha en la que inicio a trabajar</h3>
            <input type="date" name="initDate" /><br>  
            <h3>Contraseña</h3>
            <input type="text" name="password" /><br>               
            <h3>Dias que trabaja</h3>
            <label><input type="checkbox" name="cbox" value="Lunes"> Lunes</label><br>                       
            <label><input type="checkbox" name="cbox" value="Martes"> Martes</label><br>                       
            <label><input type="checkbox" name="cbox" value="Miercoles"> Miercoles</label><br>                       
            <label><input type="checkbox" name="cbox" value="Jueves"> Jueves</label><br>                       
            <label><input type="checkbox" name="cbox" value="Viernes"> Viernes</label><br>                       
            <label><input type="checkbox" name="cbox" value="Sabado"> Sabado</label><br>                       
            <label><input type="checkbox" name="cbox" value="Domingo"> Domingo</label><br> 
                <%ArrayList<String> exams = adminC.getAllExams();
                %><select name="exams">
                <%for (String exam : exams) {%>                    
                <option><%=exam%></option>>                    
                <%}%>
            </select><br>         
            <input type="submit" name="add" value="Agregar Laboratorista" />
        </form>
    </body>
    <%if (request.getParameter("add") != null) {
        String[] daysChecked = request.getParameterValues("cbox");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String registry = request.getParameter("registry");
        String DPI = request.getParameter("DPI");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String initDate = request.getParameter("initDate");
        String password = request.getParameter("password");   
        String exam = request.getParameter("exams");        
        boolean created = adminC.addNewLabWorker(daysChecked, code, name, registry, DPI, phone, email, initDate, password, exam);        
        if (created == false) {
            JOptionPane.showMessageDialog(null,"Ocurrio algo al crear el laboratorista, revise que los datos esten completos");
        }
      }
    %>
</html>
