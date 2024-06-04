<%-- 
    Document   : gestionUsuarios
    Created on : 20 abr 2024, 15:28:31
    Author     : Carri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Nueva Categoria</h1>
            <form method="post" enctype="multipart/form-data" action="GestionCategorias">
                <label for="nombreCat">Nombre:</label>
                <input type="text" id="nombreCat" name="nombreCat" required=""/>
                <br>
                <label for="imagenCat">Imagen:</label>
                <input type="file" id="imagenCat" name="imagenCat" required=""/>
                <br>
                <input type="submit" value="Crear">
            </form>
            <h1>Categorias</h1>
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Imagen</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${categorias}" var="categoria">
                        <tr>
                            <td data-label="Nombre"><c:out value="${categoria.nombre}"/></td>
                            <td><img src="../<c:out value="${categoria.imagen}"/>" alt="imagen categoria"/></td>
                            <td><a href="GestionCategorias?idEliminar=<c:out value="${categoria.idCategoria}"/>"><button>Eliminar</button></a></td>
                            <td><a href="EditarCategoria?idEditar=<c:out value="${categoria.idCategoria}"/>"><button>Editar</button></a></td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            <a href="gestion.jsp">Panel de Gestion</a>
        </div>
    </body>
</html>
