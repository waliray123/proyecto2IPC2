<%-- 
    Document   : sheduleExam
    Created on : 1/10/2020, 23:40:06
    Author     : user-ubunto
--%>

<%@page import="java.io.File"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="com.mycompany.proyecto2.PatientControlers.PatientControl"%>
<%@page import="com.mycompany.proyecto2.Utils.Exam"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.AdminControlers.AdminDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        PatientControl patientC = new PatientControl();
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
            <input type="hidden" value="<%=codePatient%>" name="codePatient"/>
            <input type="submit" value="Seleccionar" name="selectTypeExam"/>
        </form>
        <%if (request.getParameter("selectTypeExam") != null) {
                String typeExam = request.getParameter("typeExam");
                Exam exam = patientC.getExamByName(typeExam);
                String codeExam = exam.getCode();
                String costExam = String.valueOf(exam.getCost());
                boolean useOrder = exam.isUseOrder();
                String codeResult = patientC.getLastResult();
        %>
        <form>
            <h4>Codigo de Examen</h4>  
            <%=codeResult%>
            <h4>Codigo de Paciente</h4>
            <%=codePatient%>                              
            <h4>Tipo de examen</h4>
            <%=typeExam%>
            <h4>Costo de Examen</h4>
            Q.<%=costExam%><br>
            <%if (useOrder) {%>
            <h4>Codigo de Medico</h4> 
            <input type="text" name="codeMedic"/><br>
            <h4>Archivo de la orden medica</h4>
            <input type="file" name="file" accept=".pdf"><br><br> 
            <%    }
            %> 
            <input type="hidden" value="<%=codeResult%>" name="codeResult" />
            <input type="hidden" value="<%=codePatient%>" name="codePatient" />
            <input type="hidden" value="<%=codeExam%>" name="codeExam" />
            <br><input type="submit" value="Agendar Examen" name="insertResult"/>            
        </form> 
            <%}else if (request.getParameter("insertResult") != null) {
                String codeResult = request.getParameter("");
                String pathInform = request.getParameter("file");
                
//                File informFile = ;
                JOptionPane.showMessageDialog(null,"Agendar Examen " + pathInform);
                }
                            
            %>
    </body>
</html>
