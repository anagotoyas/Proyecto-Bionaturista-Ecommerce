package com.bionaturista.domain.services.impl;

import com.bionaturista.application.dto.categoria.RespuestaCategoriaEntity;
import com.bionaturista.application.dto.compuesto.CompuestoDto;
import com.bionaturista.application.dto.compuesto.RespuestaCompuestoEntity;
import com.bionaturista.application.dto.estadoPedido.RespuestaEstadoPedido;
import com.bionaturista.application.dto.estadoPedido.RespuestaEstadoPedidoEntity;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Compuesto;
import com.bionaturista.domain.entities.EstadoPedido;
import com.bionaturista.domain.repositories.CompuestoRepository;
import com.bionaturista.domain.repositories.EstadoPedidoRepository;
import com.bionaturista.domain.services.EstadoPedidoService;
import com.bionaturista.validators.EstadoPedidoValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoPedidoServiceImpl implements EstadoPedidoService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    EstadoPedidoRepository estadoPedidoRepository;

    @Value("${service.estado}")
    String serviceEstadoPedido;

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
    public Respuesta eliminarEstado(Integer idEstado) throws InterruptedException {
        Respuesta respuesta = new Respuesta();

        if (estadoPedidoRepository.existsById(idEstado)){
            estadoPedidoRepository.deleteById(idEstado);
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("101");
            respuesta.setMensaje("Pedido Eliminado.");
            return respuesta;
        }
        else {
            throw new InterruptedException("Error en la respuesta del Servicio Invocado.");
        }
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

