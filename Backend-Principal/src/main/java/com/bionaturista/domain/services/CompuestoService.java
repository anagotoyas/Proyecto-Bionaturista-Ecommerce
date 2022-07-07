package com.bionaturista.domain.services;

import com.bionaturista.application.dto.compuesto.CompuestoDto;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Compuesto;

import java.util.List;

public interface CompuestoService {

    Compuesto crearCompuesto(CompuestoDto compuesto) throws InterruptedException;
    Compuesto modificarCompuesto(CompuestoDto compuesto) throws InterruptedException;
    Respuesta eliminarCompuesto(Integer idCompuesto) throws InterruptedException;
    List<Compuesto> listarCompuesto() throws InterruptedException;
    Compuesto obtenerCompuestoPorIdCompuesto(Integer idCompuesto) throws InterruptedException;

    List<Compuesto> getAllCompuestos();

}
