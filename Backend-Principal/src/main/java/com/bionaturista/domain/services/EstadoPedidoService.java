package com.bionaturista.domain.services;

import com.bionaturista.domain.entities.EstadoPedido;

import java.util.List;

public interface EstadoPedidoService {
    EstadoPedido crearEstado(EstadoPedido estadoPedido);
    EstadoPedido modificarEstado(EstadoPedido estadoPedido);
    void eliminarEstado(Integer idEstado);
    List<EstadoPedido> listarEstado();
    EstadoPedido obtenerEstadoPorIdEstado(Integer idEstado);

}

