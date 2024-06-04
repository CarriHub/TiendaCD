<%-- 
    Document   : Carri Design
    Created on : 17 abr 2024, 13:11:40
    Author     : Carri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <%@include file="../cabecera.jsp" %>
        <div class="div_carrito">
            <c:choose>
                <c:when test="${not empty carritoUsuario}">
                    <table class="carrito">
                        <thead>
                            <tr>
                                <th>Imagen</th>
                                <th>Nombre</th>
                                <th>Talla</th>
                                <th>Precio</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${carritoUsuario}" var="linea">
                                <tr>
                                    <td data-label=""><img src="<c:out value="${linea.getProducto().imagen}"/>" alt="imagen producto"></td>
                                    <td data-label="Nombre"><c:out value="${linea.getProducto().nombre}"/></td>
                                    <td data-label="Talla"><c:out value="${linea.talla}"/></td>
                                    <td data-label="Precio"><c:out value="${linea.getProducto().precio}"/>€</td>
                                    <td data-label=""><a href="Carrito?idEliminar=<c:out value="${linea.getProducto().idProducto}"/>"><button>Eliminar</button></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div style="margin-bottom: 2rem;">
                        El carrito esta vacío
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="total">
                <div>
                    Total: <c:out value="${dinero}"/>€
                </div>
                <a href="Carrito?confirmar=true"><button>Tramitar pedido</button></a>
            </div>
            <c:if test="${not empty pedidosUsuario}">
                <c:forEach items="${pedidosUsuario}" var="pedido">
                    <div class="pedidos">
                        <div>
                            <div>
                                Fecha: <fmt:formatDate value="${pedido.fechaPedido}" var="fechaFormat"/><c:out value="${fechaFormat}"/>
                            </div>
                            <div>
                                Estado: <c:out value="${pedido.estado}"/>
                            </div>
                        </div>
                    </div>
                    <hr>
                </c:forEach>
            </c:if>
        </div>
        <%@include file="../footer.jsp" %>
    </body>
</html>
