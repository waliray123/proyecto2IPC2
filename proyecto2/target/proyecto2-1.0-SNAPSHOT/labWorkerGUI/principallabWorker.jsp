<%-- 
    Document   : principallabWorker
    Created on : 28/09/2020, 23:41:05
    Author     : user-ubunto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Pagina principal laboratorista</h1>
        <%String codeLabWorker = request.getParameter("username");%>
        <div>
            <form METHOD="POST" ACTION="labWorkerGUI/setResult.jsp">
                <h3> Entregar Examen</h3>
                <input type="submit" value="Entregar Examen" />
                <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
            </form>
        </div>
        <h2>Reportes de laboratorista</h2>
        <div>
            <form METHOD="POST" ACTION="labWorkerGUI/reportsLab/report1Lab.jsp">
                <h3>Examenes sin entregar</h3>
                <input type="submit" value="Ver examenes Sin entregar"/>
                <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
                <input type="hidden" value="1" name="report">
            </form>
            <form METHOD="POST" ACTION="labWorkerGUI/reportsLab/report2Lab.jsp">
                <h3>Examenes realizados en el dia</h3>
                <input type="submit" value="Ver examenes Realizados hoy"/>
                <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
                <input type="hidden" value="2" name="report">
            </form>
            <form METHOD="POST" ACTION="labWorkerGUI/reportsLab/report3Lab.jsp">
                <h3>Utilizacion de cada dia de trabajo</h3>
                <input type="submit" value="Utilizacion de hoy"/>
                <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
                <input type="hidden" value="3" name="report">
            </form>
            <form METHOD="POST" ACTION="labWorkerGUI/reportsLab/report4Lab.jsp">
                <h3>10 fechas con mas trabajo realizado</h3>
                <input type="submit" value="Ver fechas"/>
                <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
                <input type="hidden" value="4" name="report">
            </form>
        </div>
    </body>
</html>
