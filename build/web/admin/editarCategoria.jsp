<%-- 
    Document   : editarCategoria
    Created on : 1 may 2024, 11:48:36
    Author     : Carri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="latin1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="../logo.png" type="image/png">
        <link rel="stylesheet" href="../estilo/estilo.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Editar Categoria</title>
    </head>
    <body>
        <h1>Editar Categoria</h1>
        <form action="EditarCategoria" method="post" enctype="multipart/form-data">
            <input type="hidden" value="<c:out value="${categoriaEditar.idCategoria}"/>" name="idCatEdit">
            <label for="nombreEditar">Nombre:</label>
            <input type="text" id="nombreEditar" name="nombreEditar" value="<c:out value="${categoriaEditar.nombre}"/>" required="">
            <br>
            <label for="imagenEditar">Imagen:</label>
            <input type="file" id="imagenEditar" name="imagenEditar" value="<c:out value="${categoriaEditar.imagen}"/>" required="">
            <br>
            <input type="submit" value="Editar">
        </form>
    </body>
</html>
