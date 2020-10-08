<%-- 
    Document   : report4Lab
    Created on : 3/10/2020, 22:02:02
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.DateQuantity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.LabWorkerControlers.LabWorkerReportsDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%  LabWorkerReportsDB labRDB = new LabWorkerReportsDB();
        String codeLabWorker = request.getParameter("codeLabWorker");
    ArrayList<DateQuantity> datesQ = labRDB.getDatesWithMoreExamsAchieved();%>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center>
        <h1>Dias con mas trabajo</h1>
        <table class = "table">
            <tr>
                <th>Fecha</th>
                <th>Cantidad de examenes entregados</th>
            </tr>
            <%for (DateQuantity dateQ : datesQ) {%>
            <tr>
                <th><%=dateQ.getDate()%></th>
                <th><%=dateQ.getQuantity()%></th>
            </tr>
            <%    }
            %>
        </table>
        </center>
        <form METHOD="POST" ACTION="/proyecto2/labWorkerGUI/principallabWorker.jsp">   
            <center>
                <input type="hidden" value="<%=codeLabWorker%>" name="username">
                <br><br><input type="submit" value="Regresar" class="boton"/>
            </center>
        </form>
    </body>
</html>
