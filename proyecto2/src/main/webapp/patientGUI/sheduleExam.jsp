<%-- 
    Document   : sheduleExam
    Created on : 1/10/2020, 23:40:06
    Author     : user-ubunto
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.AdminControlers.AdminDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String codePatient = request.getParameter("codePatient");
        AdminDB adminDB = new AdminDB();
        ArrayList<String> exams = adminDB.getAllExams();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendar Examen</title>
    </head>
    <body>
        <h1>Agendar examen</h1>
        <form>
            <p>Seleccione el tipo de examen que desea hacer</p>
            <select name="typeExam">
                <%for (String exam : exams) {%>
                <option value="<%=exam%>"><%=exam%></option>
                <%    }
                %>
            </select> 
            <input type="submit" value="selectTypeExam" name="Seleccionar">
        </form>
                <%if (request.getParameter("selectTypeExam") != null) {
                        String typeExam = request.getParameter("typeExam");
                        
                    }
                %>
    </body>
</html>
