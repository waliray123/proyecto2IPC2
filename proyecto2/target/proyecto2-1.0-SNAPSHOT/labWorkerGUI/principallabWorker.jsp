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
    </body>
</html>
