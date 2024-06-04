<%-- 
    Document   : Carri Design
    Created on : 17 abr 2024, 13:11:40
    Author     : Carri
--%>
<%@ page import="entidades.Sexo" %>
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
        <c:if test="${mostrarFiltro == true}">
            <span class="filtro_prendas">
                <select id="select_filtro" name="filtro" onchange="filtrado()" >
                    <option value="null">Filtro</option>
                    <optgroup label="Sexo">
                        <c:forEach items="${Sexo.values()}" var="sexo">
                            <option value="<c:out value="${sexo.name()}"/>"><c:out value="${sexo.name()}"/></option>
                        </c:forEach>
                    </optgroup>
                    <optgroup label="Categoria">
                        <c:forEach items="${categorias}" var="categoria">
                            <option value="<c:out value="${categoria.idCategoria}"/>"><c:out value="${categoria.nombre}"/></option>
                        </c:forEach>
                    </optgroup>
                </select>
            </span>
        </c:if>
        <div class="prendas">
            <c:forEach items="${productos}" var="producto">
                <div class="producto">
                    <a href="ProductoControlador?idProducto=<c:out value="${producto.idProducto}"/>">
                        <img src="<c:out value="${producto.imagen}"/>" alt="producto"/>
                        <div>
                            <span><c:out value="${producto.nombre}"/></span>
                            <span><c:out value="${producto.precio}"/>€</span>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
        <%@include file="footer.jsp" %>
    </body>
    <script>
        function filtrado() {
            var x = document.getElementById("select_filtro").value;
            window.location.href = "Prendas?filtro=" + x;
        }
    </script>
</html>
