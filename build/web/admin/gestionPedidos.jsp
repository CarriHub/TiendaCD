<%-- 
    Document   : gestionUsuarios
    Created on : 20 abr 2024, 15:28:31
    Author     : Carri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="entidades.EstadoPedido" %>
<%@page contentType="text/html" pageEncoding="latin1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="logo.png" type="image/png">
        <link rel="stylesheet" href="../estilo/estilo.css">
        <title>Gestion Categorias</title>
    </head>
    <body>
        <div class="gestion">
            <h1>Pedidos</h1>
            <table>
                <thead>
                    <tr>
                        <th>Usuario</th>
                        <th>Fecha Pedido</th>
                        <th>Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pedidos}" var="pedido">
                        <tr>
                            <td data-label="Usuario"><c:out value="${pedido.usuario.nombre}"/></td>
                            <td data-label="Fecha Pedido"><fmt:formatDate value="${pedido.fechaPedido}" var="fechaFormat"/><c:out value="${fechaFormat}"/></td>
                            <td data-label="Estado"><form method="post" action="EditarPedido">
                                    <input type="hidden" name="idPedido" value="<c:out value="${pedido.idPedido}"/>">
                                    <select name="estado">
                                        <c:forEach items="${EstadoPedido.values()}" var="estado">
                                            <option value="<c:out value="${estado}"/>" <c:if test="${estado == pedido.estado}">selected</c:if>><c:out value="${estado}"/></option>
                                        </c:forEach>
                                    </select>
                                    <input type="submit" value="Actualizar">
                                </form></td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            <a href="gestion.jsp">Panel de Gestion</a>
        </div>
    </body>
</html>
