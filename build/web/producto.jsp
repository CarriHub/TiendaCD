<%-- 
    Document   : Carri Design
    Created on : 13 abr 2024, 14:19:41
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
        <title>Carri Design</title>
    </head>
    <body>
        <%@include file="cabecera.jsp" %>
        <div class="producto_individual">
            <img src="<c:out value="${producto.imagen}"/>" alt="imagen_producto"/>
            <div class="info_producto">
                <h2><c:out value="${producto.nombre}"/></h2>
                <h3><c:out value="${producto.precio}"/>€</h3>
                <div class="carrito_fav">
                    <c:choose>
                        <c:when test="${usuario != null}">
                            <c:set var="carritoAction" value="ProductoControlador?carrito=true"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="carritoAction" value="./login.jsp"/>
                        </c:otherwise>
                    </c:choose>
                    <form method="post" action="${carritoAction}">
                        <input type="hidden" value="<c:out value="${producto.idProducto}"/>" name="idProducto"/>
                        <select name="talla">
                            <c:forEach items="${producto.talla}" var="talla">
                                <option value="<c:out value="${talla}"/>"><c:out value="${talla}"/></option>
                            </c:forEach>
                            <input type="submit" value="Añadir al carrito"/>
                            <c:choose>
                                <c:when test="${usuario != null}">
                                    <c:set var="favoritoAction" value="ProductoControlador?idProducto=${producto.idProducto}&favorito=true"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="favoritoAction" value="./login.jsp"/>
                                </c:otherwise>
                            </c:choose>
                            <a href="${favoritoAction}">
                                <c:set var="imagenFavorito" value="false"/>
                                <c:forEach items="${usuario.getDeseado()}" var="productoDeseado">
                                    <c:if test="${productoDeseado.getIdProducto() == producto.getIdProducto()}">
                                        <c:set var="imagenFavorito" value="true"/>
                                    </c:if>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${imagenFavorito}">
                                        <img src="favorito_relleno.svg" alt="favorito"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="favorito.svg" alt="favorito"/>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                    </form>
                </div>
                <p><c:out value="${producto.descripcion}"/></p>
            </div>
        </div>
        <div class="comentarios">
            <c:if test="${usuario != null}">
                <form method="post" action="ProductoControlador">
                    <input type="hidden" value="<c:out value="${producto.idProducto}"/>" name="idProducto"/>
                    <textarea id="comentario" name="comentario" rows="5" cols="10"></textarea>
                    <input type="submit" value="Comentar"/>
                </form>
            </c:if>
            <c:forEach items="${producto.comentarios}" var="comentario">
                <h5><c:out value="${comentario.usuario.nombre}"/></h5>
                <p><c:out value="${comentario.texto}"/></p>
            </c:forEach>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
