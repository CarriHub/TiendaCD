<%-- 
    Document   : gestionUsuarios
    Created on : 20 abr 2024, 15:28:31
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
        <link rel="stylesheet" href="../estilo/estilo.css">
        <title>Gestion Usuarios</title>
    </head>
    <body>
        <div class="gestion">
        <h1>Usuarios</h1>
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th>Contraseña</th>
                    <th>Direccion</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${usuarios}" var="usuario">
                    <tr>
                        <td data-label="Nombre"><c:out value="${usuario.nombre}"/></td>
                        <td data-label="Apellido"><c:out value="${usuario.apellido}"/></td>
                        <td data-label="Email"><c:out value="${usuario.email}"/></td>
                        <td data-label="Contraseña"><c:out value="${usuario.contraseña}"/></td>
                        <td data-label="Direccion"><c:out value="${usuario.direccion}"/></td>
                        <td><a href="GestionUsuarios?idEliminar=<c:out value="${usuario.idUsuario}"/>"><button>Eliminar</button></a></td>
                        <td><a href="../EditarUsuario?idEditar=<c:out value="${usuario.idUsuario}"/>"><button>Editar</button></a></td>
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
        <a href="gestion.jsp">Panel de Gestion</a>
        </div>
    </body>
</html>
