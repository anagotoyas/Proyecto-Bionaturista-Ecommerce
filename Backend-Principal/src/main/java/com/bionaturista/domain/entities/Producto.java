package com.bionaturista.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
}
