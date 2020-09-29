
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina principal Admin</title>
    </head>
    <body>
    <center>
        <h1>Pagina principal Administrador</h1>
        <h2>Opciones</h2>
        <div>
            <form METHOD="POST" ACTION="LoginServlet">
                <h3> Agregar medicos </h3>
                <input type="submit" value="Agregar Medico" />
            </form>
        </div>
        <div>
            <form METHOD="POST" ACTION="LoginServlet">
                <h3> Agregar Laboratorista</h3>
                <input type="submit" value="Agregar Laboratorista" />
            </form>
        </div>
        <div>
            <form METHOD="POST" ACTION="LoginServlet">
                <h3> Crear examen </h3>
                <input type="submit" value="Crear examen" />
            </form>
        </div>
        <div>
            <form METHOD="POST" ACTION="LoginServlet">
                <h3> Modificar Consulta </h3>
                <input type="submit" value="Modificar Consulta" />
            </form>
        </div>
    </center>
</body>
</html>
