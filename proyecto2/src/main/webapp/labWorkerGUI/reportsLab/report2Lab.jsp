<%-- 
    Document   : report2Lab
    Created on : 3/10/2020, 19:17:41
    Author     : user-ubunto
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.mycompany.proyecto2.Utils.Exam"%>
<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page import="com.mycompany.proyecto2.Utils.Result"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.LabWorkerControlers.LabWorkerReportsDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%  LabWorkerReportsDB labRDB = new LabWorkerReportsDB();
        String codeLabWorker = request.getParameter("codeLabWorker");
        Date objDate = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String dateToday = objSDF.format(objDate);
    ArrayList<Result> results = labRDB.getResulAchievedToday(codeLabWorker, dateToday);%>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte 2</title>
    </head>
    <body>
    <center>
        <h1>Examenes realizados Hoy</h1>

        <table class = "table">
            <tr>
                <th>Codigo</th>
                <th>Codigo de paciente</th>
                <th>Codigo de Medico</th>
                <th>Tipo de Examen</th>
            </tr>
            <%for (Result result : results) {
            String codeMedic = result.getCodeMedic();
            if (codeMedic == null) {
                    codeMedic = "";
                }
            RegularConsultations regCon = new RegularConsultations();
            Exam typeExam = regCon.getExamByCode(result.getCodeExam());
            String nameTypeExam = typeExam.getName();
            %>
            <tr>
                <th><%=result.getCode()%></th>
                <th><%=result.getCodePatient()%></th>
                <th><%=codeMedic%></th>
                <th><%=nameTypeExam%></th>
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
