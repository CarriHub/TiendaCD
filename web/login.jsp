<%-- 
    Document   : login
    Created on : 17 abr 2024, 11:24:51
    Author     : Carri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="logo.png" type="image/png">
        <link rel="stylesheet" href="estilo/estilo.css">
        <title>Login</title>
    </head>
    <body class="login_bd">
        <%@include file="cabecera.jsp" %>
        <div class="login">
            <h1>Login</h1>
            <form method="post" action="Login">
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" />
                <br>
                <label for="contrasena">Contraseña</label>
                <input type="password" name="contrasena" id="contrasena" />
                <br>
                <input type="submit" value="Entrar">
            </form>
            <c:if test="${error != null}">
                <span class="error"><c:out value="${error}"/></span>
            </c:if>
                <p>¿Aun no tienes cuenta? <a href="registro.jsp">Ir al Registro</a></p>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
