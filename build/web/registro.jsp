<%-- 
    Document   : registro
    Created on : 17 abr 2024, 12:09:40
    Author     : Carri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="logo.png" type="image/png">
        <link rel="stylesheet" href="estilo/estilo.css">
        <title>Registro</title>
    </head>
    <body class="login_bd">
        <%@include file="cabecera.jsp" %>
        <div class="registro">
            <h1>Registro</h1>
            <form method="post" action="Registro">
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" required=""/>
                <br>
                <label for="contrasena">Contraseña</label>
                <input type="password" name="contrasena" id="contrasena" required=""/>
                <br>
                <label for="nombre">Nombre</label>
                <input type="text" name="nombre" id="nombre" required=""/>
                <br>
                <label for="apellido">Apellido</label>
                <input type="text" name="apellido" id="apellido" required=""/>
                <br>
                <label for="direccion">Direccion</label>
                <input type="text" name="direccion" id="direccion" required=""/>
                <br>
                <input type="submit" value="Registrarse">
            </form>
            <c:if test="${error != null}">
                <span class="error"><c:out value="${error}"/></span>
            </c:if>
                <p>¿Ya tienes cuenta? <a href="login.jsp">Ir al Login</a></p>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
