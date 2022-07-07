package com.bionaturista.application.controllers;

import com.bionaturista.domain.entities.Categoria;
import com.bionaturista.domain.services.CategoriaService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService=categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoria){
        Categoria categoriaNew= categoriaService.crearCategoria(categoria);
        return new ResponseEntity<Categoria>(categoriaNew, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Categoria> modificarCategoria(@Valid @RequestBody Categoria categoria){
        Categoria categoriaUpdate = categoriaService.modificarCategoria(categoria);
        return new ResponseEntity<Categoria>(categoriaUpdate, HttpStatus.CREATED);
    }
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable("idCategoria") Integer idCategoria){
        categoriaService.eliminarCategoria(idCategoria);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategoria(){
        List<Categoria> categorias=categoriaService.listarCategoria();
        return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
    }
    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> obtenerCategoriaPorIdCategoria(@PathVariable("idCategoria") Integer idCategoria){
        Categoria categoria=categoriaService.obtenerCategoriaPorIdCategoria(idCategoria);
        return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
    }
}

