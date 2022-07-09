package com.bionaturista.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@ToString
@Table(name="productos")
public class Producto {

    @Id
    @JsonProperty("idProducto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idProducto;

    @NotNull
    @JsonProperty("nombreProducto")
    @Size(min = 2, max = 70, message = "El nombre del producto debe tener como mínimo 2 caracteres")
    @Column(name="nombre_producto", nullable = false, length = 70)
    String nombreProducto;

    @JsonProperty("imagenProducto")
    @Column(name="imagen_producto")
    String imagenProducto;

    @JsonProperty("descripcionProducto")
    @Column(name="descripcion_producto")
    String descripcionProducto;

    @NotNull
    @JsonProperty("precioProducto")
    @Column(name="precio_producto")
    float precioProducto;

    @NotNull
    @JsonProperty("stockProducto")
    @Column(name="stock_producto")
    int stockProducto;

    @ManyToOne
    @JsonProperty("compuesto")
    @JoinColumn(name = "id_compuesto", nullable = false,
            foreignKey = @ForeignKey(name = "FK_id_compuesto"))
    private Compuesto compuesto;

    @ManyToOne
    @JsonProperty("categoria")
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

    //@JsonBackReference(value="comp")
    public Compuesto getCompuesto() {
        return compuesto;
    }

    public void setCompuesto(Compuesto compuesto) {
        this.compuesto = compuesto;
    }

    //@JsonBackReference(value="cat")
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
