package com.bionaturista.application.controllers;

import com.bionaturista.domain.entities.Compuesto;
import com.bionaturista.domain.services.CompuestoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/compuestos")
public class CompuestoController {

    private final CompuestoService compuestoService;

    public CompuestoController(CompuestoService compuestoService){
        this.compuestoService=compuestoService;
    }

    @PostMapping
    public ResponseEntity<Compuesto> crearCompuesto(@Valid @RequestBody Compuesto compuesto){
        Compuesto compuestoNew= compuestoService.crearCompuesto(compuesto);
        return new ResponseEntity<Compuesto>(compuestoNew, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Compuesto> modificarCompuesto(@Valid @RequestBody Compuesto compuesto){
        Compuesto compuestoUpdate = compuestoService.modificarCompuesto(compuesto);
        return new ResponseEntity<Compuesto>(compuestoUpdate, HttpStatus.CREATED);
    }
    @DeleteMapping("/{idCompuesto}")
    public ResponseEntity<Void> eliminarCompuesto(@PathVariable("idCompuesto") Integer idCompuesto){
        compuestoService.eliminarCompuesto(idCompuesto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<List<Compuesto>> listarCompuesto(){
        List<Compuesto> compuestos=compuestoService.listarCompuesto();
        return new ResponseEntity<List<Compuesto>>(compuestos, HttpStatus.OK);
    }
    @GetMapping("/{idCompuesto}")
    public ResponseEntity<Compuesto> obtenerCompuestoPorIdCompuesto(@PathVariable("idCompuesto") Integer idCompuesto){
        Compuesto compuesto=compuestoService.obtenerCompuestoPorIdCompuesto(idCompuesto);
        return new ResponseEntity<Compuesto>(compuesto, HttpStatus.OK);
    }
}

