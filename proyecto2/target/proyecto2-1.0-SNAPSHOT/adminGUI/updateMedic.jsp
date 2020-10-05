<%-- 
    Document   : updateMedic
    Created on : 4/10/2020, 20:55:59
    Author     : user-ubunto
--%>

<%@page import="com.mycompany.proyecto2.Utils.encryptPassword"%>
<%@page import="com.mycompany.proyecto2.AdminControlers.AdminControl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.proyecto2.Utils.Medic"%>
<%@page import="com.mycompany.proyecto2.AdminControlers.AdminDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%AdminControl adminC = new AdminControl();%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar medico</title>
    </head>
    <body>
        <h1>Actualizar medico</h1>
        <form>
            <p>Ingrese el codigo del medico</p>
            <input type="text" name="codeMedic">
            <input type="submit" name="searchCode">            
        </form>
        <%if (request.getParameter("searchCode") != null) {
                AdminDB adminDB = new AdminDB();
                String codeMedic = request.getParameter("codeMedic");
                codeMedic = adminDB.searchCodeMedicByCode(codeMedic);
                if (codeMedic.equals("") || codeMedic == null) {%>
                <p>Ingreso mal el codigo del medico</p>
                <p>Vuelva a intentarlo</p>
        <%      }else{
                Medic medic = adminDB.searchMedicByCode(codeMedic);
                ArrayList<String> specialtiesMedic = medic.getSpecialties();
                %>
                <form>
                    <h2>Llene los siguientes campos para ingresar al medico</h2>
            <h3>Codigo</h3>
            <input type="text" name="code" value="<%=medic.getCode()%>"/><br>
            <h3>Nombre</h3>
            <input type="text" name="name" value="<%=medic.getName()%>"/><br>
            <h3>Numero de colegiado</h3>
            <input type="text" name="collegiate" value="<%=medic.getCollegiate()%>"/><br>
            <h3>DPI</h3>
            <input type="text" name="DPI" value="<%=medic.getDPI()%>"/><br>
            <h3>Telefono</h3>
            <input type="text" name="phone" value="<%=medic.getPhone()%>"/><br>                
            <h3>Correo Electronico</h3>
            <input type="text" name="email" value="<%=medic.getMail()%>"/><br>
            <h3>Hora de entrada</h3>
            <input type="time" name="initTime" value="<%=medic.getInitTime()%>"/><br>
            <h3>Hora de salida</h3>
            <input type="time" name="finalTime" value="<%=medic.getFinalTime()%>"/><br>
            <h3>Fecha en la que inicio a trabajar</h3>
            <input type="date" name="initDate" value="<%=medic.getInitWork()%>"/><br>        
            <h3>Especialidades</h3>
            <%ArrayList<String> specialties = adminC.getAllSpecialties();
            for (String specialty : specialties) {
                boolean isChecked = false;
                for (String specialty1 : specialtiesMedic) {
                    if (specialty1.equals(specialty)) {                        
                        isChecked = true;
                    }
                }
                if (isChecked) {%>
                    <label><input type="checkbox" name="cbox" value="<%=specialty%>" checked> <%=specialty%></label><br>
                <%}else{%>
                    <label><input type="checkbox" name="cbox" value="<%=specialty%>" > <%=specialty%></label><br>
                <%}
            }%>                        
                <input type="submit" name="updateM" value="Actualizar Medico" />
                </form>                
        <%        }
            }
        %>
       <%if (request.getParameter("updateM") != null) {
        String[] specialtiesChecked = request.getParameterValues("cbox");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String collegiate = request.getParameter("collegiate");
        String DPI = request.getParameter("DPI");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String initTime = request.getParameter("initTime");
        String finalTime = request.getParameter("finalTime");        
        String initDate = request.getParameter("initDate");        
        String password = request.getParameter("password");        
        boolean created = adminC.addNewMedic(specialtiesChecked, code, name, collegiate, DPI, phone, email, password, initTime, finalTime, initDate);        
        if (created == false) {
            JOptionPane.showMessageDialog(null,"Ocurrio un error al crear el medico, Revise que sus datos sean correctos");
        }
      }
    %>
    </body>
</html>
