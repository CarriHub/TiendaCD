/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import persistenceControlador.ControladorPersistence;

/**
 *
 * @author Carri
 */
public class ControladorEntidades {

    ControladorPersistence controlador = new ControladorPersistence();

    /*Categoria*/
    public void crearCategoria(Categoria cat) {
        controlador.crearCategoria(cat);
    }

    public void eliminarCategoria(int id) {
        controlador.eliminarCategoria(id);
    }

    public void editarCategoria(Categoria cat) {
        controlador.editarCategoria(cat);
    }

    public Categoria getCategoria(int id) {
        return controlador.getCategoria(id);
    }

    public List<Categoria> getCategorias() {
        return controlador.getCategorias();
    }
    
    /*Comentario*/
    public void crearComentario(Comentario com) {
        controlador.crearComentario(com);
    }

    public void eliminarComentario(int id) {
        controlador.eliminarComentario(id);
    }

    public void editarComentario(Comentario com) {
        controlador.editarComentario(com);
    }

    public Comentario getComentario(int id) {
        return controlador.getComentario(id);
    }

    public List<Comentario> getComentarios() {
        return controlador.getComentarios();
    }
    
    /*Pedido*/
    public void crearPedido(Pedido ped) {
        controlador.crearPedido(ped);
    }

    public void eliminarPedido(int id) {
        controlador.eliminarPedido(id);
    }

    public void editarPedido(Pedido ped) {
        controlador.editarPedido(ped);
    }

    public Pedido getPedido(int id) {
        return controlador.getPedido(id);
    }

    public List<Pedido> getPedidos() {
        return controlador.getPedidos();
    }
    
    /*Producto*/
    public void crearProducto(Producto pro) {
        controlador.crearProducto(pro);
    }

    public void eliminarProducto(int id) {
        controlador.eliminarProducto(id);
    }

    public void editarProducto(Producto pro) {
        controlador.editarProducto(pro);
    }

    public Producto getProducto(int id) {
        return controlador.getProducto(id);
    }

    public List<Producto> getProductos() {
        return controlador.getProductos();
    }
    
    /*Usuario*/
    public void crearUsuario(Usuario usu) {
        controlador.crearUsuario(usu);
    }

    public void eliminarUsuario(int id) {
        controlador.eliminarUsuario(id);
    }

    public void editarUsuario(Usuario usu) {
        controlador.editarUsuario(usu);
    }

    public Usuario getUsuario(int id) {
        return controlador.getUsuario(id);
    }

    public List<Usuario> getUsuarios() {
        return controlador.getUsuarios();
    }
    /*UsuarioProductoCarrito*/
    public void crearUsuarioProductoCarrito(UsuarioProductoCarrito upc) {
        controlador.crearUsuarioProductoCarrito(upc);
    }

    public void eliminarUsuarioProductoCarrito(int id) {
        controlador.eliminarUsuarioProductoCarrito(id);
    }

    public void editarUsuarioProductoCarrito(UsuarioProductoCarrito upc) {
        controlador.editarUsuarioProductoCarrito(upc);
    }

    public UsuarioProductoCarrito getUsuarioProductoCarrito(int id) {
        return controlador.getUsuarioProductoCarrito(id);
    }

    public List<UsuarioProductoCarrito> getUsuarioProductoCarritos() {
        return controlador.getUsuarioProductoCarritos();
    }
}
