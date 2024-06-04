/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Carri
 */
@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUsuario;

    private String nombre;

    private String apellido;

    private String email;

    private String contraseña;

    private String direccion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Usuario_Producto_Carrito",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idProducto"))
    private List<Producto> carrito;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Usuario_Producto_Favorito",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idProducto"))
    private List<Producto> deseado;

    public Usuario() {
    }

    public Usuario( String nombre, String apellido, String email, String contraseña, String direccion, List<Pedido> pedidos, List<Producto> carrito, List<Producto> deseado) {        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.direccion = direccion;
        this.pedidos = pedidos;
        this.carrito = carrito;
        this.deseado = deseado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Producto> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<Producto> carrito) {
        this.carrito = carrito;
    }

    public List<Producto> getDeseado() {
        return deseado;
    }

    public void setDeseado(List<Producto> deseado) {
        this.deseado = deseado;
    }

    
    
    
}
