<%-- 
    Document   : cabecera.jsp
    Created on : 13 abr 2024, 14:01:11
    Author     : Carri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <nav>
            <div class='menu_lista'>
                <a href='CarriDesign'><img src='logo.png' alt='Carri Design'></a>
                <div><a href='Prendas'>Prendas</a></div>
                <div><a href='Prendas?filtro=1'>Accesorios</a></div>
                <div><a href='Carri Design.jsp'>Carri Design</a></div>
            </div>
            <div class='menu_objetos'>
                <div><a 
                        <c:choose>
                            <c:when test="${usuario == null}">
                                href='login.jsp'
                            </c:when>
                            <c:otherwise>
                                href='Prendas?favoritos=true'
                            </c:otherwise>
                        </c:choose>
                        ><img src='favorito.svg' alt='favorito'></a></div>
                <div><a <c:choose>
                            <c:when test="${usuario == null}">
                                href='login.jsp'
                            </c:when>
                            <c:otherwise>
                                href='Carrito'
                            </c:otherwise>
                        </c:choose>
                        ><img src='cart.svg' alt='carrito'></a></div>
                        <c:choose>
                            <c:when test="${usuario == null}">
                        <div><a href='login.jsp'>Login</a></div>
                    </c:when>
                    <c:otherwise>
                        <div><a href='EditarUsuario?idEditar=<c:out value="${usuario.getIdUsuario()}"/>'><c:out value="${usuario.getNombre()}"/></a><a href="CerrarSesion">Cerrar Sesion</a></div>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${usuario.getEmail().equals('admin@admin.admin')}">
                    <div><a href='admin/gestion.jsp'>Gestion</a></div>
                </c:if>
            </div>
            <label for="menu_imagen"><img src="menu.svg"/></label>
            <input type="checkbox" id="menu_imagen">
            <div class="menu_responsivo">
                <ul>
                    <li><a href='Prendas'>Prendas</a></li>
                    <li><a href='Prendas?filtro=1'>Accesorios</a></li>
                    <li><a href='Carri Design.jsp'>Carri Design</a></li>
                    <li>
                        <a 
                            <c:choose>
                                <c:when test="${usuario == null}">
                                    href='login.jsp'
                                </c:when>
                                <c:otherwise>
                                    href='Prendas?favoritos=true'
                                </c:otherwise>
                            </c:choose>
                            >Favoritos</a> 
                    </li>
                    <li>
                        <a <c:choose>
                                <c:when test="${usuario == null}">
                                    href='login.jsp'
                                </c:when>
                                <c:otherwise>
                                    href='Carrito'
                                </c:otherwise>
                            </c:choose>
                            >Carrito</a> 
                    </li>
                    <c:choose>
                        <c:when test="${usuario == null}">
                            <li><a href='login.jsp'>Login</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href='EditarUsuario?idEditar=<c:out value="${usuario.getIdUsuario()}"/>'><c:out value="${usuario.getNombre()}"/></a></li>
                            <li><a href="CerrarSesion">Cerrar Sesion</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${usuario.getEmail().equals('admin@admin.admin')}">
                        <li><a href='admin/gestion.jsp'>Gestion</a></li>
                        </c:if>
                </ul>
            </div>
        </nav>
    </body>
</html>
