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
    private String nombreProducto;

    @Column(name="imagen_producto")
    private String imagenProducto;

    @Column(name="descripcion_producto")
    private String descripcionProducto;

    @NotNull
    @Column(name="precio_producto")
    private float precioProducto;

    @NotNull
    @Column(name="stock_producto")
    private int stockProducto;

    @ManyToOne

    @JoinColumn(name = "id_compuesto", nullable = false,
            foreignKey = @ForeignKey(name = "FK_id_compuesto"))
    private Compuesto compuesto;

    @ManyToOne

    @JoinColumn(name = "id_categoria", nullable = false,
            foreignKey = @ForeignKey(name = "FK_id_categoria"))
    private Categoria categoria;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
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


    }


