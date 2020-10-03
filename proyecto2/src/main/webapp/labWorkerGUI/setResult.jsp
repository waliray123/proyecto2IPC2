<%-- 
    Document   : setResult
    Created on : 2/10/2020, 22:37:04
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.Result"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.LabWorkerControlers.LabWorkerControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%  LabWorkerControl labC = new LabWorkerControl();
        String codeLabWorker = request.getParameter("codeLabWorker");
        ArrayList<Result> results = labC.getPendingResultsByCodeLab(codeLabWorker);        
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Examenes Pendientes</title>
    </head>
    <body>
        <h1>Examenes Pendientes</h1>
        <p>Selecciones algun examen pendiente<p>
        <div>
            <form>
                <select>
                <%for (Result result : results) {
                    String codeResult = result.getCode();
                %>
                    <option value="<%=codeResult%>"><%=codeResult%></option>
                <%    }
                %>
                </select>
            </form>
        </div>
        <p>Si no aparece ningun examen es porque no hay o no tiene que trabajar hoy<p>
    </body>
</html>
