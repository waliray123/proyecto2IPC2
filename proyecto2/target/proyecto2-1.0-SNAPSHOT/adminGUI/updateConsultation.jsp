<%-- 
    Document   : updateConsultationf
    Created on : 30/09/2020, 09:41:23
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.AdminControlers.AdminControl"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%AdminControl adminC = new AdminControl();%>
    <%String codeConsultation = "";%>
    <%String costConsultation = "";%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar consulta</title>
    </head>
    <%if (request.getParameter("load") == null) {%>
    <body>
        <form name="form1" id="form1" method="get"> 
            <h1>Modificar consulta</h1>            
            <h3>Seleccionar consulta</h3>
            <%ArrayList<String> specialties = adminC.getAllSpecialties();%>
            <select name="specialties">
            <%for (String specialty : specialties) {%>
            <option><%=specialty%></option>
            <%}%> 
            </select>
            <input type="submit" value="Cargar Consulta" id="load"name="load" /><br>            
        </form> 
        <%if (request.getParameter("update") != null) {
            costConsultation = request.getParameter("cost");
            codeConsultation = request.getParameter("codeConsultation");
            adminC.updateConsultation(codeConsultation, costConsultation);
        }
    %>
    <%}else if (request.getParameter("load") != null) { 
        String type = request.getParameter("specialties");
        costConsultation = adminC.getCostConsultationByType(type);
        codeConsultation = adminC.getCodeConsultationByType(type);
    %>    
        <form name="form1" id="form1" method="get"> 
            <h1>Modificar consulta</h1>            
            <h3>Seleccionar consulta</h3>
            <%ArrayList<String> specialties = adminC.getAllSpecialties();%>
            <select name="specialties">
            <%for (String specialty : specialties) {
                if (specialty.equals(type)) {%>
                    <option selected><%=specialty%></option>
                <%}else{%>
                    <option><%=specialty%></option>
                <%}%>                        
            <%}%> 
            </select>
            <input type="submit" value="Cargar Consulta" id="load"name="load" /><br>
            <h3>Costo de la consulta</h3>
            <input type="text" name="cost" value="<%=costConsultation%>"/><br>            
            <input type="submit" value="Actualizar Consulta"id="update"name="update" />
            <input type="hidden" value="<%=codeConsultation%>"  id="codeConsultation" name="codeConsultation" />
        </form>
    </body>        
    <%
       }
    %>
    
    
</html>
