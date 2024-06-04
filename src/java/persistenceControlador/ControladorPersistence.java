/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistenceControlador;

import entidades.Categoria;
import entidades.Comentario;
import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
import entidades.UsuarioProductoCarrito;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistenceControlador.exceptions.NonexistentEntityException;

/**
 *
 * @author Carri
 */
public class ControladorPersistence {
    
    CategoriaJpaController catJpa = new CategoriaJpaController();
    ComentarioJpaController comJpa = new ComentarioJpaController();
    PedidoJpaController pedJpa = new PedidoJpaController();
    ProductoJpaController proJpa = new ProductoJpaController();
    UsuarioJpaController usuJpa = new UsuarioJpaController();
    UsuarioProductoCarritoJpaController upcJpa = new UsuarioProductoCarritoJpaController();
    
    /*Categoria*/
    public void crearCategoria(Categoria cat){
        catJpa.create(cat);
    }
    public void eliminarCategoria(int id){
        try {
            catJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarCategoria(Categoria cat){
        try {
            catJpa.edit(cat);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Categoria getCategoria(int id){
        return catJpa.findCategoria(id);
    }
    public List<Categoria> getCategorias(){
        return catJpa.findCategoriaEntities();
    }
    /*Comentario*/
    public void crearComentario(Comentario com){
        comJpa.create(com);
    }
    public void eliminarComentario(int id){
        try {
            comJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarComentario(Comentario com){
        try {
            comJpa.edit(com);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Comentario getComentario(int id){
        return comJpa.findComentario(id);
    }
    public List<Comentario> getComentarios(){
        return comJpa.findComentarioEntities();
    }
    /*Producto*/
    public void crearProducto(Producto pro){
        proJpa.create(pro);
    }
    public void eliminarProducto(int id){
        try {
            proJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarProducto(Producto pro){
        try {
            proJpa.edit(pro);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Producto getProducto(int id){
        return proJpa.findProducto(id);
    }
    public List<Producto> getProductos(){
        return proJpa.findProductoEntities();
    }
    /*Pedido*/
    public void crearPedido(Pedido ped){
        pedJpa.create(ped);
    }
    public void eliminarPedido(int id){
        try {
            pedJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarPedido(Pedido ped){
        try {
            pedJpa.edit(ped);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Pedido getPedido(int id){
        return pedJpa.findPedido(id);
    }
    public List<Pedido> getPedidos(){
        return pedJpa.findPedidoEntities();
    }
    /*Usuario*/
    public void crearUsuario(Usuario usu){
        usuJpa.create(usu);
    }
    public void eliminarUsuario(int id){
        try {
            usuJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarUsuario(Usuario usu){
        try {
            usuJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Usuario getUsuario(int id){
        return usuJpa.findUsuario(id);
    }
    public List<Usuario> getUsuarios(){
        return usuJpa.findUsuarioEntities();
    }
    /*UsuarioProductoCarritoJpaController*/
    public void crearUsuarioProductoCarrito(UsuarioProductoCarrito upc){
        upcJpa.create(upc);
    }
    public void eliminarUsuarioProductoCarrito(int id){
        try {
            upcJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarUsuarioProductoCarrito(UsuarioProductoCarrito upc){
        try {
            upcJpa.edit(upc);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public UsuarioProductoCarrito getUsuarioProductoCarrito(int id){
        return upcJpa.findUsuarioProductoCarrito(id);
    }
    public List<UsuarioProductoCarrito> getUsuarioProductoCarritos(){
        return upcJpa.findUsuarioProductoCarritoEntities();
    }
}
