<%-- 
    Document   : updateLabWorker
    Created on : 4/10/2020, 22:58:31
    Author     : user-ubunto
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="com.mycompany.proyecto2.Utils.LabWorker"%>
<%@page import="com.mycompany.proyecto2.AdminControlers.AdminDB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.AdminControlers.AdminControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%AdminControl adminC = new AdminControl();%>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Laboratorista</title>
    </head>
    <body>
        <center><h1>Actualizar Laboratorista</h1></center>
        <form class="formulario">
            <p>Ingrese el codigo del Laboratorista</p>
            <input type="text" name="codeLab">
            <input type="submit" name="searchCode" class="boton">            
        </form>
        <%if (request.getParameter("searchCode") != null) {
            AdminDB adminDB = new AdminDB();
            String codeLabWorker = request.getParameter("codeLab");
            if (adminDB.searchCodeLabWorkerByCode(codeLabWorker).equals("")) {%>
            Te equivocaste de codigo de laboratorista Vuelve a intentar
        <%    }else{
            LabWorker labW = adminDB.getLabWorkerByCode(codeLabWorker);
            ArrayList<String> days = adminDB.getNameDaysLabWorker(codeLabWorker);
            String checkedL = "";
            String checkedMa = "";
            String checkedMi = "";
            String checkedJ = "";
            String checkedV = "";
            String checkedS = "";
            String checkedD = "";
            for (String day : days) {
                if (day.equals("LUNES")) {
                    checkedL = "checked";
                }else if(day.equals("MARTES")){
                    checkedMa = "checked";
                }else if(day.equals("MIERCOLES")){
                    checkedMi = "checked";
                }else if(day.equals("JUEVES")){
                    checkedJ = "checked";
                }else if(day.equals("VIERNES")){
                    checkedV = "checked";
                }else if(day.equals("SABADO")){
                    checkedS = "checked";
                }else if(day.equals("DOMINGO")){
                    checkedD = "checked";
                }
            }
        %>
        <center><h2>Llene los siguientes campos para actualizar el laboratorista</h2></center>
        <form class="formulario3">
            <div class="contenedor">
            <div>
            
            <h3>Codigo</h3>
            <p><%=labW.getCode()%></p>            
            <h3>Nombre</h3>
            <input type="text" name="name" value="<%=labW.getName()%>"/><br>
            <h3>Numero de Registro</h3>
            <input type="text" name="registry" value="<%=labW.getRegistry()%>"/><br>
            </div>
            <div>
            <h3>DPI</h3>
            <input type="text" name="DPI" value="<%=labW.getDPI()%>"/><br>            
            <h3>Telefono</h3>
            <input type="text" name="phone" value="<%=labW.getPhone()%>"/><br>                
            <h3>Correo Electronico</h3>
            <input type="text" name="email" value="<%=labW.getMail()%>"/><br>
            <h3>Fecha en la que inicio a trabajar</h3>
            <input type="date" name="initDate" value="<%=labW.getInitWork()%>"/><br>    
            </div>
            <div>
            <h3>Dias que trabaja</h3>
            <label><input type="checkbox" name="cbox" value="Lunes" <%=checkedL%>> Lunes</label><br>                       
            <label><input type="checkbox" name="cbox" value="Martes" <%=checkedMa%>> Martes</label><br>                       
            <label><input type="checkbox" name="cbox" value="Miercoles" <%=checkedMi%>> Miercoles</label><br>                       
            <label><input type="checkbox" name="cbox" value="Jueves" <%=checkedJ%>> Jueves</label><br>                       
            <label><input type="checkbox" name="cbox" value="Viernes" <%=checkedV%>> Viernes</label><br>                       
            <label><input type="checkbox" name="cbox" value="Sabado" <%=checkedS%>> Sabado</label><br>                       
            <label><input type="checkbox" name="cbox" value="Domingo" <%=checkedD%>> Domingo</label><br> 
                <%ArrayList<String> exams = adminC.getAllExams();
                %><select name="exams">
                <%for (String exam : exams) {%>                    
                <option><%=exam%></option>>                    
                <%}%>
            </select><br> 
            </div>
            </div>            
            <input type="hidden" name="codeLab" value="<%=codeLabWorker%>" />
            <input type="submit" name="updateLab" value="Agregar Laboratorista" class="boton"/>
        </form>
        <% }   }
        %>
        <%if (request.getParameter("updateLab") != null) {
        String[] daysChecked = request.getParameterValues("cbox");
        String code = request.getParameter("codeLab");
        String name = request.getParameter("name");
        String registry = request.getParameter("registry");
        String DPI = request.getParameter("DPI");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String initDate = request.getParameter("initDate"); 
        String exam = request.getParameter("exams");        
        boolean created = adminC.updateLabWorker(daysChecked, code, name, registry, DPI, phone, email, initDate, exam);        
        if (created == false) {
            JOptionPane.showMessageDialog(null,"Ocurrio algo al crear el laboratorista, revise que los datos esten completos");
        }
      }
    %>
        <form>   
            <center>
                <br><br><input type="button" value="Regresar" class="boton" onclick="location.href='principalAdmin.jsp'"/>
            </center>
        </form>
    </body>
</html>
