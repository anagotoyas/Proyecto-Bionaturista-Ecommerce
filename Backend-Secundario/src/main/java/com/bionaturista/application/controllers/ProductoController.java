package com.bionaturista.application.controllers;


import com.bionaturista.domain.entities.Categoria;
import com.bionaturista.domain.entities.Producto;
import com.bionaturista.domain.services.ProductoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService=productoService;
    }


    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto){
        Producto productoNew= productoService.crearProducto(producto);
        return new ResponseEntity<Producto>(productoNew, HttpStatus.CREATED);
    }


    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("idProducto") Integer idProducto){
        productoService.eliminarProducto(idProducto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @PutMapping
    public ResponseEntity<Producto> modificarProducto(@Valid @RequestBody Producto producto){
        Producto productoUpdate= productoService.modificarProducto(producto);
        return new ResponseEntity<Producto>(productoUpdate, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Producto>> listarProducto(){
        List<Producto> productos=productoService.listarProducto();
        return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Producto> obtenerProductoPorIdProducto(@PathVariable("idProducto") Integer idProducto){
        Producto producto=productoService.obtenerProductoPorIdProducto(idProducto);
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }
    @GetMapping("/contar")
    public ResponseEntity<Long> contarProductos(){
        Long producto = productoService.countProductos();
        return new ResponseEntity<Long>(producto, HttpStatus.OK);
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<List<Producto>> buscarPorNombre(String nombreProducto){
        List<Producto> productoL=productoService.buscarPorNombre(nombreProducto);
        return new ResponseEntity<List<Producto>>(productoL, HttpStatus.OK);
    }

}

