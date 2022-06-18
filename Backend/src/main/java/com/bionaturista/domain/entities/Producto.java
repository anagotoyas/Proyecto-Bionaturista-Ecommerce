package com.bionaturista.domain.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="producto")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @NotNull
    @Size(min = 2, max = 70, message = "El nombre del producto debe tener como mínimo 2 caracteres")
    @Column(name="nombre_producto", nullable = false, length = 70)
    private String nombreP;

    @Column(name="imagen_producto")
    private String imagenP;

    @Column(name="descripcion_producto")
    private String descripcionP;

    @NotNull
    @Column(name="precio_producto")
    private float precioP;

    @NotNull
    @Column(name="stock_producto")
    private int stockP;

    @ManyToOne
    @JoinColumn(name = "id_compuesto", nullable = false,
            foreignKey = @ForeignKey(name = "FK_id_compuesto"))
    private Compuesto compuesto;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false,
            foreignKey = @ForeignKey(name = "FK_id_categoria"))
    private Categoria categoria;

    @JsonIgnore
    @ManyToMany(mappedBy = "carritoCompras")
    private Set<Usuario> carritos = new LinkedHashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "productosPedido")
    private Set<Pedido> pedidos= new LinkedHashSet<>();

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getImagenP() {
        return imagenP;
    }

    public void setImagenP(String imagenP) {
        this.imagenP = imagenP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public float getPrecioP() {
        return precioP;
    }

    public void setPrecioP(float precioP) {
        this.precioP = precioP;
    }

    public int getStockP() {
        return stockP;
    }

    public void setStockP(int stockP) {
        this.stockP = stockP;
    }

    public Compuesto getCompuesto() {
        return compuesto;
    }

    public void setCompuesto(Compuesto compuesto) {
        this.compuesto = compuesto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Usuario> getCarritos() {
        return carritos;
    }

    public void setCarritos(Set<Usuario> carritos) {
        this.carritos = carritos;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

