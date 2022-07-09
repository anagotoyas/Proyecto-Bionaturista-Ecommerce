package com.bionaturista.domain.services;

import com.bionaturista.application.dto.producto.ProductoDto;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Producto;

import java.util.List;

public interface ProductoService {

    Producto crearProducto(ProductoDto producto)  throws InterruptedException;

    Producto modificarProducto(ProductoDto producto)  throws InterruptedException;

    Respuesta eliminarProducto(Integer idProducto)  throws InterruptedException;

    List<Producto> listarProducto()  throws InterruptedException;

    Producto obtenerProductoPorIdProducto(Integer idProducto)  throws InterruptedException;

    Long countProductos()  throws InterruptedException;

    List<Producto> buscarPorNombre(String nombreP)  throws InterruptedException;
}
