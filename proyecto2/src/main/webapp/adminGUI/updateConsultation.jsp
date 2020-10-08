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
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar consulta</title>
    </head>
    <%if (request.getParameter("load") == null) {%>
    <body>
        <form name="form1" id="form1" method="get" class="formulario"> 
            <center>
            <h1>Modificar consulta</h1>            
            <h3>Seleccionar consulta</h3>
            <%ArrayList<String> specialties = adminC.getAllSpecialties();%>
            <select name="specialties" class="setFont">
            <%for (String specialty : specialties) {%>
            <option><%=specialty%></option>
            <%}%> 
            </select>
            <br><br><input type="submit" value="Cargar Consulta" id="load"name="load" class="boton"/><br>            
            </center>
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
        <form name="form1" id="form1" method="get" class="formulario"> 
            <center>
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
            <input type="submit" value="Actualizar Consulta"id="update"name="update" class="boton"/>
            <input type="hidden" value="<%=codeConsultation%>"  id="codeConsultation" name="codeConsultation" />
            </center>
        </form>
    </body>        
    <%
       }
    %>
    <form>   
            <center>
                <br><br><input type="button" value="Regresar" class="boton" onclick="location.href='principalAdmin.jsp'"/>
            </center>
    </form>
    
</html>
