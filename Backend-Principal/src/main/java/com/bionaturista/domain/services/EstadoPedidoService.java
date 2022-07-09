package com.bionaturista.domain.services;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.EstadoPedido;

import java.util.List;

public interface EstadoPedidoService {
    EstadoPedido crearEstado(EstadoPedido estadoPedido) throws InterruptedException;
    EstadoPedido modificarEstado(EstadoPedido estadoPedido) throws InterruptedException;
    Respuesta eliminarEstado(Integer idEstado) throws InterruptedException;
    List<EstadoPedido> listarEstado() throws InterruptedException;
    EstadoPedido obtenerEstadoPorIdEstado(Integer idEstado) throws InterruptedException;



}

