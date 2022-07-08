package com.bionaturista.application.dto.producto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {

    private Integer idProducto;

    private String nombreP;

    private String imagenP;

    private String descripcionP;

    private float precioP;

    private int stockP;
}
