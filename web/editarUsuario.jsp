<%-- 
    Document   : editarUsuario
    Created on : 20 abr 2024, 16:15:45
    Author     : Carri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="latin1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="logo.png" type="image/png">
        <link rel="stylesheet" href="estilo/estilo.css">
        <title>Editar Usuario</title>
    </head>
    <body>
        <div class="gestion">
            <h1>Edicion Usuario</h1>
            <form method="post" action="EditarUsuario">
                <input type="hidden" name="idEditar" value="<c:out value="${usuarioEditar.idUsuario}"/>">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" value="<c:out value="${usuarioEditar.nombre}"/>" required=""/><br>
                <label for="apellido">Apellido</label>
                <input type="text" id="apellido" name="apellido" value="<c:out value="${usuarioEditar.apellido}"/>" required=""/><br>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="<c:out value="${usuarioEditar.email}"/>" required=""/><br>
                <label for="contrasena">Contraseña</label>
                <input type="text" id="contrasena" name="contrasena" value="<c:out value="${usuarioEditar.contraseña}"/>" required=""/><br>
                <label for="direccion">Direccion</label>
                <input type="text" id="direccion" name="direccion" value="<c:out value="${usuarioEditar.direccion}"/>" required=""/><br>
                <input type="submit">
            </form>
            <c:if test="${usuario.getEmail().equals('admin@admin.admin')}">
                <a href="admin/gestion.jsp">Panel de Gestion</a>
            </c:if>
            <a href="CarriDesign">Inicio</a>
        </div>
    </body>
</html>
