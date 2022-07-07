package com.bionaturista.application.controllers;


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
    public ResponseEntity<WrapperResponse<Producto>> crearProducto(@Valid @RequestBody Producto producto){
        Producto productoNew= productoService.crearProducto(producto);
        return new WrapperResponse<>(true, "success", productoNew).createResponse();
    }
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<WrapperResponse<Void>> eliminarProducto(@PathVariable("idProducto") Integer idProducto){
        productoService.eliminarProducto(idProducto);
        return new WrapperResponse<Void>(true, "success", null).createResponse(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<WrapperResponse<Producto>> modificarProducto(@Valid @RequestBody Producto producto){
        Producto productoUpdate= productoService.modificarProducto(producto);
        return new WrapperResponse<>(true, "success",productoUpdate).createResponse();
    }


    @GetMapping
    public ResponseEntity<WrapperResponse<List<Producto>>> listarProducto(){
        List<Producto> productos=productoService.listarProducto();
        return new WrapperResponse<>(true, "success", productos).createResponse();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<WrapperResponse<Producto>> obtenerProductoPorIdProducto(@PathVariable("idProducto") Integer idProducto){
        Producto producto=productoService.obtenerProductoPorIdProducto(idProducto);
        return new WrapperResponse<>(true, "success", producto).createResponse();
    }
    @GetMapping("/contar")
    public ResponseEntity<WrapperResponse<Long>> contarProductos(){
        Long producto = productoService.countProductos();
        return new WrapperResponse<>(true, "success", producto).createResponse();
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<WrapperResponse<List<Producto>>> buscarPorNombre(String nombreProducto){
        List<Producto> productoL=productoService.buscarPorNombre(nombreProducto);
        return new WrapperResponse<>(true, "success", productoL).createResponse();
    }

}

