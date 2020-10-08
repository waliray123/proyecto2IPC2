<%-- 
    Document   : principallabWorker
    Created on : 28/09/2020, 23:41:05
    Author     : user-ubunto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%String codeLabWorker = request.getParameter("username");
        if (codeLabWorker == null) {
             codeLabWorker = String.valueOf(request.getAttribute("username"));
        }
    %>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Pagina principal laboratorista</h1>
        <h2>Codigo de Laboratorista: <%=codeLabWorker%></h2>
        <div class="formulario3">
            <div class="contenedor">
            <div>
                <form METHOD="POST" ACTION="/proyecto2/labWorkerGUI/setResult.jsp">
                    <h3> Entregar Examen</h3>
                    <input type="submit" value="Entregar Examen" class="boton"/>
                    <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker" >
                </form>
            </div>            
            <div>
                <h2>Reportes de laboratorista</h2>
                <form METHOD="POST" ACTION="/proyecto2/labWorkerGUI/reportsLab/report1Lab.jsp">
                    <h3>Examenes sin entregar</h3>
                    <input type="submit" value="Ver examenes Sin entregar"class="boton"/>
                    <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
                    <input type="hidden" value="1" name="report">
                </form>
                <form METHOD="POST" ACTION="/proyecto2/labWorkerGUI/reportsLab/report2Lab.jsp">
                    <h3>Examenes realizados en el dia</h3>
                    <input type="submit" value="Ver examenes Realizados hoy" class="boton"/>
                    <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
                    <input type="hidden" value="2" name="report">
                </form>
                <form METHOD="POST" ACTION="/proyecto2/labWorkerGUI/reportsLab/report3Lab.jsp">
                    <h3>Utilizacion de cada dia de trabajo</h3>
                    <input type="submit" value="Utilizacion de hoy" class="boton"/>
                    <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
                    <input type="hidden" value="3" name="report">
                </form>
                <form METHOD="POST" ACTION="/proyecto2/labWorkerGUI/reportsLab/report4Lab.jsp">
                    <h3>10 fechas con mas trabajo realizado</h3>
                    <input type="submit" value="Ver fechas" class="boton"/>
                    <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
                    <input type="hidden" value="4" name="report">
                </form>
            </div>
                    </div>
        </div>
        <form>   
            <center>
                <br><br><input type="button" value="Salir" class="boton" onclick="location.href = '/proyecto2/index.jsp'"/>
            </center>
        </form>
    </body>
</html>
