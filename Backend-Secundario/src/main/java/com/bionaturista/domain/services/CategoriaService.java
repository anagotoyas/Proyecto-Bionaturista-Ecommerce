package com.bionaturista.domain.services;

import com.bionaturista.domain.entities.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria crearCategoria(Categoria categoria);
    Categoria modificarCategoria(Categoria categoria);
    void eliminarCategoria(Integer idCategoria);
    List<Categoria> listarCategoria();
    Categoria obtenerCategoriaPorIdCategoria(Integer idCategoria);

}
