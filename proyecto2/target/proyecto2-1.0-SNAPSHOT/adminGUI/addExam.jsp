<%-- 
    Document   : addExam
    Created on : 29/09/2020, 23:00:46
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.AdminControlers.AdminControl"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%AdminControl adminC = new AdminControl();%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregrar Examen</title>
    </head>
    <body>
        <h1>Agregar Examen</h1>
        <form>
            <h2>Llene los siguientes campos para registrar el examen</h2>
            <h3>Codigo</h3>
            <input type="text" name="code" placeholder="000"/><br>
            <h3>Nombre del examen</h3>
            <input type="text" name="name" placeholder="Juan Perez"/><br><br>
            <label><input type="checkbox" name="cbox" value="use_order"> Requiere orden Medica</label><br>
            <h3>Descripcion</h3>
            <textarea name="description" rows="5" cols="30"></textarea><br>
            <h3>Cost</h3>
            <input type="text" name="cost" /><br><br>  
            <fieldset>
                <legend>Tipo de informe</legend>                
                <label><input type="radio" name="input" value="IMG" checked/> Imagen</label><br>
                <label><input type="radio" name="input" value="PDF" /> PDF</label><br>                                              
            </fieldset>
            <input type="submit" name="add" value="Agregar Examen" />
        </form>
    </body>
    <%if (request.getParameter("add") != null) {
        String[] useOrderChecked = request.getParameterValues("cbox");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String cost = request.getParameter("cost");
        String report_type = request.getParameter("input");      
        boolean created = adminC.addNewExam(code, name, useOrderChecked, description, cost, report_type);
        if (created == false) {
            JOptionPane.showMessageDialog(null,"Ocurrio un error al crear el medico, Revise que sus datos sean correctos");
        }
      }
    %>
</html>
