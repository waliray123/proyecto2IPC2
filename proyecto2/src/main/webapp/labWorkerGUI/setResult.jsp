<%-- 
    Document   : setResult
    Created on : 2/10/2020, 22:37:04
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.Exam"%>
<%@page import="com.mycompany.proyecto2.DBControlers.RegularConsultations"%>
<%@page import="javax.swing.JOptionPane"%>
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
                <select name="selectCodeResult">
                <%for (Result result : results) {
                    String codeResult = result.getCode();
                %>
                    <option><%=codeResult%></option>
                <%}%>
                                    
                </select>
                <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
                <%if (results.size() > 0) {%>
                <br><input type="submit" name="selectResult" value="Seleccionar resultado"/>       
                <%} else {%>
                <p>No tienes trabajo por hacer</p>
                <%}%>                                  
            </form>
        </div>            
            <%if (request.getParameter("selectResult") != null) {
                String codeResult = request.getParameter("selectCodeResult");
                RegularConsultations regCon = new RegularConsultations();  
                Result resultFinish = null;
                for (int i = 0; i < results.size(); i++) {
                        Result result = results.get(i);
                        if (result.getCode().equals(codeResult)) {
                                resultFinish = result;
                        }
                    }
                String codePatient = resultFinish.getCodePatient();
                String codeMedic = resultFinish.getCodeMedic();
                String codeExam = resultFinish.getCodeExam();
                String dateToday = labC.getDateToday();
                Exam exam = regCon.getExamByCode(codeExam);
                String nameExam = exam.getName();
                String reportType = exam.getReportType();
                String typeFileInform = "";
                if (reportType.equals("PDF")) {
                    typeFileInform = ".pdf";
                }else{
                    typeFileInform = "image/*";
                }                
                %>
                <form method="post" enctype="multipart/form-data" action="/proyecto2/setResultServlet">
                <h4>Codigo de Paciente</h4>
                <%=codePatient%>
                <h4>Codigo de Medico</h4>
                <%=codeMedic%>
                <h4>Nombre de Examen</h4>
                <%=nameExam%>
                <h4>Codigo de Laboratorista</h4>
                <%=codeLabWorker%>
                <h4>Fecha</h4>
                <%=dateToday%>
                <h4>Hora</h4>
                <p>Automaticamente se guardara la hora en que se envie el formulario</p>
                <h4>Informe</h4>
                <p>Sube el archivo del informe (<%=reportType%>)</p>
                <input type="file" name="fileInform" accept="<%=typeFileInform%>"><br><br>
                <input type="hidden" value="<%=codeLabWorker%>" name="codeLabWorker">
                <input type="hidden" value="<%=codeResult%>" name="codeResult">
                <input type="hidden" value="<%=dateToday%>" name="dateResult">
                <input type="submit" name="finishExam" value="Terminar examen">
                </form>
            <%}%>
    </body>
</html>
