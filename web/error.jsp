<%-- 
    Document   : error
    Created on : 3 mar 2024, 11:16:52
    Author     : Carri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="estilo/estilo.css">
        <link rel="stylesheet" href="../estilo/estilo.css">
        <title>ERROR</title>
    </head>
    <body>
        <div class="paginaerror">
            <h1>404</h1>
            <p>Página no encontrada</p>
            <c:choose>
                <c:when test="${usuario.getEmail() != 'admin@admin.admin'}">
                    <a href="/CarriDesign">Inicio</a>
                </c:when>
                <c:otherwise>
                    <a href="../CarriDesign">Inicio</a>
                </c:otherwise>
            </c:choose>
            
        </div>
    </body>
</html>