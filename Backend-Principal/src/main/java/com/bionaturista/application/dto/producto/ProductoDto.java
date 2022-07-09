package com.bionaturista.application.dto.producto;

import com.bionaturista.domain.entities.Categoria;
import com.bionaturista.domain.entities.Compuesto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ProductoDto {

    Integer idProducto;
    String nombreProducto;
    String imagenProducto;
    String descripcionProducto;
    int precioProducto;
    Integer stockProducto;


    private Compuesto compuesto;

    private Categoria categoria;


}