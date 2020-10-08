<%-- 
    Document   : updateMedic
    Created on : 4/10/2020, 20:55:59
    Author     : user-ubunto
--%>

<%@page import="javax.swing.JOptionPane"%>
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
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar medico</title>
    </head>
    <body>
    <center><h1>Actualizar medico</h1></center>
    <form class="formulario">
        <h4>Ingrese el codigo del medico</h4>
        <input type="text" name="codeMedic">
        <input type="submit" name="searchCode">            
    </form>
    <%if (request.getParameter("searchCode") != null) {
            AdminDB adminDB = new AdminDB();
            String codeMedic = request.getParameter("codeMedic");
            codeMedic = adminDB.searchCodeMedicByCode(codeMedic);
            if (codeMedic.equals("") || codeMedic == null) {%>
    <center>
        <p>Ingreso mal el codigo del medico</p>
        <p>Vuelva a intentarlo</p>
    </center>
    <%      } else {
        Medic medic = adminDB.searchMedicByCode(codeMedic);
        ArrayList<String> specialtiesMedic = medic.getSpecialties();
    %>
    <form class="formulario3">        
        <div class="contenedor">
        <div>
        <h2>Llene los siguientes campos para ingresar al medico</h2>
        <h3>Codigo</h3>
        <p><%=codeMedic%></p>
        <h3>Nombre</h3>
        <input type="text" name="name" value="<%=medic.getName()%>"/><br>
        <h3>Numero de colegiado</h3>
        <input type="text" name="collegiate" value="<%=medic.getCollegiate()%>"/><br>
        <h3>DPI</h3>
        <input type="text" name="DPI" value="<%=medic.getDPI()%>"/><br>
        <h3>Telefono</h3>
        <input type="text" name="phone" value="<%=medic.getPhone()%>"/><br>  
        </div>
        <div>
        <h3>Correo Electronico</h3>
        <input type="text" name="email" value="<%=medic.getMail()%>"/><br>
        <h3>Hora de entrada</h3>
        <input type="time" name="initTime" value="<%=medic.getInitTime()%>"/><br>
        <h3>Hora de salida</h3>
        <input type="time" name="finalTime" value="<%=medic.getFinalTime()%>"/><br>
        <h3>Fecha en la que inicio a trabajar</h3>
        <input type="date" name="initDate" value="<%=medic.getInitWork()%>"/><br>  
        </div>
        <div>
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
            <%} else {%>
        <label><input type="checkbox" name="cbox" value="<%=specialty%>" > <%=specialty%></label><br>
            <%}
                    }%>  
        </div>
        </div>
        <center>
        <input type="hidden" name="codeMedic" value="<%=codeMedic%>">
        <input type="submit" name="updateM" value="Actualizar Medico" class="boton"/>        
        </center>
    </form>          
    <%        }
        }
    %>
    <form>   
        <center>
            <br><br><input type="button" value="Regresar" class="boton" onclick="location.href='principalAdmin.jsp'"/>
        </center>
    </form>
    <%if (request.getParameter("updateM") != null) {
            String[] specialtiesChecked = request.getParameterValues("cbox");
            String code = request.getParameter("codeMedic");
            String name = request.getParameter("name");
            String collegiate = request.getParameter("collegiate");
            String DPI = request.getParameter("DPI");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String initTime = request.getParameter("initTime");
            String finalTime = request.getParameter("finalTime");
            String initDate = request.getParameter("initDate");
            boolean created = adminC.updateMedic(specialtiesChecked, code, name, collegiate, DPI, phone, email, initTime, finalTime, initDate);
            if (created == false) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al Actualizar el medico, Revise que sus datos sean correctos");
            }
        }
    %>
</body>
</html>
