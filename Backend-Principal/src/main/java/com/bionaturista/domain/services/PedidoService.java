package com.bionaturista.domain.services;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Pedido;
import com.bionaturista.domain.entities.Usuario;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoService {

    //CRUD
    Pedido pagarPedido(Pedido pedido) throws InterruptedException;
    Pedido modificarPedido(Pedido pedido) throws InterruptedException;
    Respuesta eliminarPedido(Integer idPedido) throws InterruptedException;
    Pedido obtenerPedidoPorIdPedido(Integer idPedido) throws InterruptedException;
    List<Pedido> listarPedido() throws InterruptedException;
    List<Pedido> listarPedidosPorIdUsuario(Usuario usuario) throws InterruptedException;

    //Funcionalities
    Pedido modificarEstadoPedido(Integer idPedido) throws InterruptedException;
    void cancelarPedido(Integer idPedido) throws InterruptedException;
    float obtenerMontoTotal(Usuario usuario, Pedido pedido) throws InterruptedException;
    float obtenerSubtotal(Pedido pedido) throws InterruptedException;



}