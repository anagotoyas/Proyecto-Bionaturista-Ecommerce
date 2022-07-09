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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


}
