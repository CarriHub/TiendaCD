/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Carri
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPedido;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPedido;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "pedido")
    private List<UsuarioProductoCarrito> usuarioProductoCarritos;

    public Pedido() {
    }

    public Pedido(Date fechaPedido, EstadoPedido estado, Usuario usuario, List<UsuarioProductoCarrito> usuarioProductoCarritos) {
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.usuario = usuario;
        this.usuarioProductoCarritos = usuarioProductoCarritos;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<UsuarioProductoCarrito> getUsuarioProductoCarritos() {
        return usuarioProductoCarritos;
    }

    public void setUsuarioProductoCarritos(List<UsuarioProductoCarrito> usuarioProductoCarritos) {
        this.usuarioProductoCarritos = usuarioProductoCarritos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

