<%-- 
    Document   : gestionProductos
    Created on : 1 may 2024, 12:58:55
    Author     : Carri
--%>

<%@page import="entidades.Talla"%>
<%@page contentType="text/html" pageEncoding="latin1"%>
<%@ page import="entidades.Sexo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="../logo.png" type="image/png">
        <link rel="stylesheet" href="../estilo/estilo.css">
        <title>Gestion Productos</title>
    </head>
    <body>
        <div class="gestion">
            <h1>Nuevo Producto</h1>
            <form method="post" enctype="multipart/form-data" action="GestionProductos">
                <label for="nombrePro">Nombre:</label>
                <input type="text" id="nombrePro" name="nombrePro" required="">
                <br>
                <label>Categoria:</label>
                <select name="catProId">
                    <c:forEach items="${categorias}" var="categoria">
                        <option value="<c:out value="${categoria.idCategoria}"/>"><c:out value="${categoria.nombre}"/></option>
                    </c:forEach>
                </select><br>
                <div class="tallas">
                    <c:forEach items="${Talla.values()}" var="talla">
                        <label for="<c:out value="${talla.name()}"/>"><c:out value="${talla.name()}"/></label>
                        <input type="checkbox" name="tallaPro[]" value="<c:out value="${talla.name()}"/>" id="<c:out value="${talla.name()}"/>">
                    </c:forEach>
                </div>
                <br>
                <label for="imagenPro">Imagen:</label>
                <input type="file" id="imagenPro" name="imagenPro" required="">
                <br>
                <label>Sexo:</label>
                <select name="sexoPro">
                    <c:forEach items="${Sexo.values()}" var="sexo">
                        <option value="<c:out value="${sexo.name()}"/>"><c:out value="${sexo.name()}"/></option>
                    </c:forEach>
                </select><br>
                <label for="descripPro">Descripción:</label>
                <textarea id="descripPro" name="descripPro" rows="5" cols="10" required=""></textarea>
                <br>
                <label for="precioPro">Precio:</label>
                <input type="text" id="precioPro" name="precioPro" pattern="^\d*(\.\d{0,2})?$" required="">
                <br>
                <input type="submit" value="Crear">
            </form>
            <h1>Productos</h1>
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Categoria</th>
                        <th>Precio</th>
                        <th>Descripcion</th>
                        <th>Imagen</th>
                        <th>Sexo</th>
                        <th>Tallas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productos}" var="producto">
                        <tr>
                            <td data-label="Nombre"><c:out value="${producto.nombre}"/></td>
                            <td data-label="Categoria">
                                <c:forEach items="${categorias}" var="categoria">
                                    <c:if test="${categoria.idCategoria == producto.categoria.idCategoria}">
                                        <c:out value="${categoria.nombre}"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td data-label="Precio"><c:out value="${producto.precio}"/></td>
                            <td data-label="Descripcion"><c:out value="${producto.descripcion}"/></td>
                            <td data-label=""><img src="../<c:out value="${producto.imagen}"/>" alt="imagen producto"/></td>
                            <td data-label="Sexo"><c:out value="${producto.sexo}"/></td>
                            <td data-label="Tallas">
                                <c:forEach items="${producto.talla}" var="talla">
                                    <c:out value="${talla} "/>
                                </c:forEach>
                            </td>
                            <td><a href="GestionProductos?idEliminar=<c:out value="${producto.idProducto}"/>"><button>Eliminar</button></a></td>
                            <td><a href="EditarProducto?idEditar=<c:out value="${producto.idProducto}"/>"><button>Editar</button></a></td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            <a href="gestion.jsp">Panel de Gestion</a>
        </div>
    </body>
</html>
