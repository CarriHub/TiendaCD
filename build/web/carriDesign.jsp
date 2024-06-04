<%-- 
    Document   : Carri Design
    Created on : 13 abr 2024, 14:19:41
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
        <title>Carri Design</title>
    </head>
    <body>
        <%@include file="cabecera.jsp" %>
        <div class="principal">
            <div class="novedades">
                <a href="Prendas?filtro=novedades">Novedades</a>
            </div>
            <div class="sexo">
                <div class="hombre">
                    <a href="Prendas?filtro=HOMBRE">Hombre</a>
                </div>
                <div class="unisex">
                    <a href="Prendas?filtro=UNISEX">Unisex</a>
                </div>
                <div class="mujer">
                    <a href="Prendas?filtro=MUJER">Mujer</a>
                </div>
            </div>
            <div class="colecciones">
                <c:forEach items="${categorias}" var="categoria">
                    <div class="colecciones_individual" style="background-image: url('${categoria.imagen}')">
                        <a href="Prendas?filtro=<c:out value="${categoria.idCategoria}"/>"><span><c:out value="${categoria.nombre}"/></span></a>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
