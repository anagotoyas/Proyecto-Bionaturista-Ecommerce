package com.bionaturista.domain.services.impl;

import com.bionaturista.domain.entities.Producto;
import com.bionaturista.domain.repositories.ProductoRepository;
import com.bionaturista.domain.services.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository){

        this.productoRepository = productoRepository;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Integer idProducto) {
        productoRepository.deleteById(idProducto);
    }

    @Override
    public List<Producto> listarProducto() {

        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorIdProducto(Integer idProducto) {
        return productoRepository.findById(idProducto).orElse(new Producto());
    }

    @Override
    public Long countProductos() {
        return productoRepository.contarProductos();
    }

    @Override
    public List<Producto> buscarPorNombre(String nombreP) {
        return productoRepository.buscarPorNombre(nombreP);
    }
}

