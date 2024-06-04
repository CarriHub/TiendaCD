/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Carri
 */
@Entity
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProducto;

    @ManyToOne
    private Categoria categoria;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private String descripcion;

    private float precio;

    private String imagen;

    @ElementCollection
    private List<Talla> talla;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    public Producto() {
    }

    public Producto(Categoria categoria, String nombre, Sexo sexo, String descripcion, float precio, List<Talla> talla, List<Comentario> comentarios, String imagen) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.sexo = sexo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.talla = talla;
        this.comentarios = comentarios;
        this.imagen = imagen;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<Talla> getTalla() {
        return talla;
    }

    public void setTalla(List<Talla> talla) {
        this.talla = talla;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
