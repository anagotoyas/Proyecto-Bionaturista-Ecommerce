package com.bionaturista.domain.services;

import com.bionaturista.application.dto.categoria.CategoriaDto;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria crearCategoria(CategoriaDto categoria) throws InterruptedException;
    Categoria modificarCategoria(CategoriaDto categoria) throws InterruptedException;
    Respuesta eliminarCategoria(Integer idCategoria) throws InterruptedException;
    List<Categoria> listarCategoria() throws InterruptedException;
    Categoria obtenerCategoriaPorIdCategoria(Integer idCategoria) throws InterruptedException;

    List<Categoria> getAllCategorias();

}
