package com.bionaturista.domain.services;

import com.bionaturista.domain.entities.Pedido;
import com.bionaturista.domain.entities.Usuario;

import java.util.List;

public interface PedidoService {

    //CRUD
    Pedido pagarPedido(Pedido pedido);
    Pedido modificarPedido(Pedido pedido);
    void eliminarPedido(Integer idPedido);
    Pedido obtenerPedidoPorIdPedido(Integer idPedido);
    List<Pedido> listarPedido();

    //Funcionalities
    Pedido modificarEstadoPedido(Integer idPedido);
    void cancelarPedido(Integer idPedido);
    float obtenerMontoTotal(Usuario usuario, Pedido pedido);
    float obtenerSubtotal(Pedido pedido);



}