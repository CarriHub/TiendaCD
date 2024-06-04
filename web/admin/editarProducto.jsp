<%-- 
    Document   : editarCategoria
    Created on : 1 may 2024, 11:48:36
    Author     : Carri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entidades.Talla" %>
<%@ page import="entidades.Sexo" %>
<%@page contentType="text/html" pageEncoding="latin1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="../logo.png" type="image/png">
        <link rel="stylesheet" href="../estilo/estilo.css">
        <title>Editar Producto</title>
    </head>
    <body>
        <div class="gestion">
            <h1>Editar Producto</h1>
            <form method="post" enctype="multipart/form-data" action="EditarProducto">
                <input type="hidden" value="<c:out value="${productoEditar.idProducto}"/>" name="idProEdit">
                <label for="nombrePro">Nombre:</label>
                <input type="text" id="nombrePro" name="nombrePro" value="<c:out value="${productoEditar.nombre}"/>" required="">
                <br>
                <label>Categoria:</label>
                <select name="catProId">
                    <c:forEach items="${categorias}" var="categoria">
                        <option value="<c:out value="${categoria.idCategoria}"/>" <c:if test="${productoEditar.categoria.idCategoria == categoria.idCategoria}">selected</c:if>><c:out value="${categoria.nombre}"/></option>
                    </c:forEach>
                </select><br>
                <div class="tallas">
                    <c:forEach items="${Talla.values()}" var="talla">
                        <label for="<c:out value="${talla.name()}"/>"><c:out value="${talla.name()}"/></label>
                        <input type="checkbox" name="tallaPro[]" value="<c:out value="${talla.name()}"/>" id="<c:out value="${talla.name()}"/>" <c:if test="${productoEditar.talla.contains(talla)}">checked</c:if>/>
                    </c:forEach>
                </div>
                <br>
                <label for="imagenPro">Imagen:</label>
                <input type="file" id="imagenPro" name="imagenPro"  value="<c:out value="${productoEditar.imagen}"/>" required="">
                <br>
                <label>Sexo:</label>
                <select name="sexoPro">
                    <c:forEach items="${Sexo.values()}" var="sexo">
                        <option value="<c:out value="${sexo.name()}"/>" <c:if test="${productoEditar.sexo == sexo}">selected</c:if>><c:out value="${sexo.name()}"/></option>
                    </c:forEach>
                </select><br>
                <label for="descripPro">Descripción:</label>
                <textarea id="descripPro" name="descripPro" rows="5" cols="10" required=""><c:out value="${productoEditar.descripcion}"/></textarea>
                <br>
                <label for="precioPro">Precio:</label>
                <input type="text" id="precioPro" name="precioPro" pattern="^\d*(\.\d{0,2})?$" value="<c:out value="${productoEditar.precio}"/>" required="">
                <br>
                <input type="submit" value="Editar">
            </form>
        </div>
    </body>
</html>
