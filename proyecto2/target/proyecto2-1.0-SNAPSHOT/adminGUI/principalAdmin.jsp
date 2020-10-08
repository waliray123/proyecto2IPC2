
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="/proyecto2/css/styles.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina principal Admin</title>
    </head>
    <body>
    <center>
        <h1>Pagina principal Administrador</h1>   
        <div class="formulario3">
        <div class="contenedor" >            
            <div>
                <h2>Opciones</h2>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/addMedic.jsp">
                    <h3> Agregar medicos </h3>
                    <input type="submit" value="Agregar Medico" class="boton" />
                </form>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/updateMedic.jsp">
                    <h3> Actualizar medico </h3>
                    <input type="submit" value="Actualizar Medico" class="boton"/>
                </form>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/addLabWorker.jsp">
                    <h3> Agregar Laboratorista</h3>
                    <input type="submit" value="Agregar Laboratorista" class="boton"/>
                </form>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/updateLabWorker.jsp">
                    <h3> Actualizar Laboratorista</h3>
                    <input type="submit" value="Actualizar Laboratorista" class="boton"/>
                </form>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/addExam.jsp">
                    <h3> Crear examen </h3>
                    <input type="submit" value="Crear examen" class="boton"/>
                </form>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/updateConsultation.jsp">
                    <h3> Modificar Consulta </h3>
                    <input type="submit" value="Modificar Consulta" class="boton"/>
                </form>
            </div>
            <div>
                <h2>Reportes de Paciente</h2>        
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/reportsAdmin/report1Admin.jsp">
                    <h3>Los 10 médicos que más informes han realizado dentro de un intervalo de tiempo.</h3>
                    <input type="submit" value="Ver Medicos" class="boton"/>
                    <input type="hidden" value="1" name="report" >
                </form>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/reportsAdmin/report2Admin.jsp">
                    <h3>Ingresos obtenidos por médico en un intervalo de tiempo.</h3>
                    <input type="submit" value="Ver Ingresos"class="boton"/>
                    <input type="hidden" value="2" name="report">
                </form>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/reportsAdmin/report3Admin.jsp">
                    <h3>Los 5 médicos con menor cantidad de citas dentro de un intervalo de tiempo</h3>
                    <input type="submit" value="Ver Medicos"class="boton"/>
                    <input type="hidden" value="3" name="report">
                </form>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/reportsAdmin/report4Admin.jsp">
                    <h3>Los exámenes de laboratorio más demandados en un intervalo de tiempo.</h3>
                    <input type="submit" value="Ver Examenes"class="boton"/>
                    <input type="hidden" value="4" name="report">
                </form>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/reportsAdmin/report5Admin.jsp">
                    <h3>Los médicos con mayor cantidad de exámenes de laboratorio requeridos</h3>
                    <input type="submit" value="Ver Medicos"class="boton"/>
                    <input type="hidden" value="4" name="report">
                </form>
                <form METHOD="POST" ACTION="/proyecto2/adminGUI/reportsAdmin/report6Admin.jsp">
                    <h3>Ingresos generados por paciente en un intervalo de tiempo.</h3>
                    <input type="submit" value="Ver Ingresos"class="boton"/>
                    <input type="hidden" value="4" name="report">
                </form>
            </div>
        </div>
        </div>
        <form>   
            <center>
                <br><br><input type="button" value="Salir" class="boton" onclick="location.href='/proyecto2/index.jsp'"/>
            </center>
        </form>
    </center>
</body>
</html>
