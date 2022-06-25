package com.bionaturista.domain.services;

import com.bionaturista.domain.entities.Producto;

import java.util.List;

public interface ProductoService {

    Producto crearProducto(Producto producto);
    Producto modificarProducto(Producto producto);
    void eliminarProducto(Integer idProducto);
    List<Producto> listarProducto();
    Producto obtenerProductoPorIdProducto(Integer idProducto);
    Long countProductos();
    List<Producto> buscarPorNombre(String nombreP);
}
