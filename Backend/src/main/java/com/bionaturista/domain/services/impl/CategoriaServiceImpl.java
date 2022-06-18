package com.bionaturista.domain.services.impl;

import com.bionaturista.domain.entities.Categoria;
import com.bionaturista.domain.repositories.CategoriaRepository;
import com.bionaturista.domain.services.CategoriaService;
import com.bionaturista.validators.CategoriaValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        CategoriaValidator.validate(categoria);
        return categoriaRepository.save(categoria);

    }

    @Override
    public Categoria modificarCategoria(Categoria categoria) {
        CategoriaValidator.validate(categoria);
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Integer idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }

    @Override
    public List<Categoria> listarCategoria() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obtenerCategoriaPorIdCategoria(Integer idCategoria) {
        return categoriaRepository.findById(idCategoria).orElse(new Categoria());
    }
}

