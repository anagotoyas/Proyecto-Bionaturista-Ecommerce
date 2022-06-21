package com.bionaturista.domain.services.impl;

import com.bionaturista.domain.entities.*;
import com.bionaturista.domain.repositories.*;
import com.bionaturista.domain.services.PedidoService;
import com.bionaturista.validators.PedidoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    @Autowired
    private InfoEnvioRepository infoEnvioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository){
        this.pedidoRepository=pedidoRepository;
    }

    @Override
    public Pedido pagarPedido(Pedido pedido) {
        //Asigno el montoPago el costoEnvío para de ahí sumarle los productos jeje
        pedido.setMontoPago(pedido.getCostoEnvio());
        //Obtengo el Usuario mediante su idUsuario para obtener su carrito pex
        Integer idsazo=pedido.getUsuario().getIdUsuario();
        Usuario usuarioFinal=usuarioRepository.getById(idsazo);
        //Le sumamos al montoPago los precios de cada product y lo guardamoss
        pedido.setMontoPago(obtenerMontoTotal(usuarioFinal, pedido));
        //Obtiene el subTotal
        pedido.setSubtotal(obtenerSubtotal(pedido));
        //Valida ☼
        pedido.setProductosPedido(usuarioFinal.getCarritoCompras());
        PedidoValidator.validate(pedido);
        //Vasea el carrito y se lo guarda al usuario (actualiza *en esencia*) ☼☼☼
        Set<Producto> nuevoCarrito = null;
        usuarioFinal.setCarritoCompras(nuevoCarrito);

        //Se debe agregar un metodo exits para verificar si la información de envio ya existe, pero me da pereza hacerlo
        InfoEnvio iv = this.infoEnvioRepository.save(pedido.getInfoEnvio());
        InfoEnvio info = this.infoEnvioRepository.findById(iv.getIdInfoEnvio()).orElse(new InfoEnvio());
        pedido.setInfoEnvio(info);



        usuarioRepository.save(usuarioFinal);
        //Y guarda el pedido as it should :)
        return pedidoRepository.save(pedido);
    }

    @Override
    public float obtenerMontoTotal(Usuario usuario, Pedido pedido) {
        Set<Producto> productos = usuario.getCarritoCompras();
        float total=pedido.getMontoPago();
        for (Producto producto : productos){
            //Suma de precios
            total+=producto.getPrecioP();
            //Resta al stock de cada productazo
            producto.setStockP(producto.getStockP()-1);
            productoRepository.save(producto);
        }
        return total;
    }

    @Override
    public float obtenerSubtotal(Pedido pedido) {
        float subtotal=0f;
        float montoCompleto=pedido.getMontoPago();
        float montoEnvio= pedido.getCostoEnvio();
        subtotal=montoCompleto-montoEnvio;
        return subtotal;
    }

    @Override
    public Pedido modificarPedido(Pedido pedido) {
        PedidoValidator.validate(pedido);
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminarPedido(Integer idPedido) {
        pedidoRepository.deleteById(idPedido);
    }

    @Override
    public Pedido obtenerPedidoPorIdPedido(Integer idPedido) {
        return pedidoRepository.findById(idPedido).orElse(new Pedido());
    }

    @Override
    public List<Pedido> listarPedido() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido modificarEstadoPedido(Integer idPedido) {
        return null;
    }

    @Override
    public List<Pedido> listarPedidosPorIdUsuario(Usuario usuario) {
        List<Pedido> usuarioId = pedidoRepository.listarPedidosPorIdUsuario(usuario);
        return usuarioId;
    }

    @Override
    public void cancelarPedido(Integer idPedido) {
        Pedido pedido=pedidoRepository.findById(idPedido).orElse(new Pedido());
        EstadoPedido enviado=estadoPedidoRepository.pedidoEnviado();
        pedido.setEstadoPedido(enviado);
    }

}

