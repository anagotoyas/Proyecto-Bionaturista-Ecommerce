package com.bionaturista.domain.services.impl;

import com.bionaturista.domain.entities.EstadoPedido;
import com.bionaturista.domain.repositories.EstadoPedidoRepository;
import com.bionaturista.domain.services.EstadoPedidoService;
import com.bionaturista.validators.EstadoPedidoValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoPedidoServiceImpl implements EstadoPedidoService {

    public final EstadoPedidoRepository estadoPedidoRepository;

    public EstadoPedidoServiceImpl(EstadoPedidoRepository estadoPedidoRepository){
        this.estadoPedidoRepository=estadoPedidoRepository;
    }

    @Override
    public EstadoPedido crearEstado(EstadoPedido estadoPedido) {
        EstadoPedidoValidator.validate(estadoPedido);
        return estadoPedidoRepository.save(estadoPedido);
    }

    @Override
    public EstadoPedido modificarEstado(EstadoPedido estadoPedido) {
        EstadoPedidoValidator.validate(estadoPedido);
        return estadoPedidoRepository.save(estadoPedido);
    }

    @Override
    public void eliminarEstado(Integer idEstado) {
        estadoPedidoRepository.deleteById(idEstado);
    }

    @Override
    public List<EstadoPedido> listarEstado() {
        return estadoPedidoRepository.findAll();
    }

    @Override
    public EstadoPedido obtenerEstadoPorIdEstado(Integer idEstado) {
        return estadoPedidoRepository.findById(idEstado).orElse(new EstadoPedido());
    }
}

