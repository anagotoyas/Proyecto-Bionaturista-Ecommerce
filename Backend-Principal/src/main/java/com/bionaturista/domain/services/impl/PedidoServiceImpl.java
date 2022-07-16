package com.bionaturista.domain.services.impl;

import com.bionaturista.application.dto.producto.ProductoDto;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.Pattern.Publisher;
import com.bionaturista.domain.entities.*;
import com.bionaturista.domain.repositories.*;
import com.bionaturista.domain.services.PedidoService;
import com.bionaturista.domain.services.ProductoService;
import com.bionaturista.validators.PedidoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PedidoServiceImpl implements PedidoService, Publisher {
    private final PedidoRepository pedidoRepository;
    @Autowired
    private InfoEnvioRepository infoEnvioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;
    @Autowired
    private UsuarioServiceImpl service;
    private ProductoService productoService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) throws InterruptedException{
        this.pedidoRepository=pedidoRepository;
    }

    @Override
    public Pedido pagarPedido(Pedido pedido) throws InterruptedException {
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
        //pedido.setProductosPedido(usuarioFinal.getCarritoCompras());
        PedidoValidator.validate(pedido);
        //Vasea el carrito y se lo guarda al usuario (actualiza) ☼☼☼
        Set<Producto> nuevoCarrito = null;
        usuarioFinal.setCarritoCompras(nuevoCarrito);

        //Se debe agregar un metodo exits para verificar si la información de envio ya existe, pero me da pereza hacerlo
        InfoEnvio iv = this.infoEnvioRepository.save(pedido.getInfoEnvio());
        InfoEnvio info = this.infoEnvioRepository.findById(iv.getIdInfoEnvio()).orElse(new InfoEnvio());
        pedido.setInfoEnvio(info);

        this.notificar(pedido.getUsuario(), "ha pagado su pedido");

        usuarioRepository.save(usuarioFinal);
        //Y guarda el pedido as it should :)
        return pedidoRepository.save(pedido);
    }

    @Override
    public float obtenerMontoTotal(Usuario usuario, Pedido pedido) throws InterruptedException {
        Set<Producto> productos = usuario.getCarritoCompras();
        float total=pedido.getMontoPago();
        for (Producto producto : productos){
            //Suma de precios
            total+=producto.getPrecioProducto();
            //Resta al stock de cada productazo
            producto.setStockProducto(producto.getStockProducto()-1);
            //Hacemos el traspaso de Product a ProductDto para que se modifique bien lindo
            ProductoDto productoDto = new ProductoDto();
            productoDto.setIdProducto(producto.getIdProducto());
            productoDto.setDescripcionProducto(producto.getDescripcionProducto());
            productoDto.setImagenProducto(producto.getImagenProducto());
            productoDto.setNombreProducto(producto.getNombreProducto());
            productoDto.setCategoria(producto.getCategoria());
            productoDto.setCompuesto(producto.getCompuesto());
            //Guardamos el Producto con menos stockazo
            productoService.modificarProducto(productoDto);
        }
        return total;
    }

    @Override
    public float obtenerSubtotal(Pedido pedido) throws InterruptedException {
        float subtotal=0f;
        float montoCompleto=pedido.getMontoPago();
        float montoEnvio= pedido.getCostoEnvio();
        subtotal=montoCompleto-montoEnvio;
        return subtotal;
    }

    @Override
    public Pedido modificarPedido(Pedido pedido) throws InterruptedException {
        PedidoValidator.validate(pedido);
        this.notificar(pedido.getUsuario(), "ha modificado su pedido");
        return pedidoRepository.save(pedido);
    }

    @Override
    public Respuesta eliminarPedido(Integer idPedido) throws InterruptedException {
        Respuesta respuesta = new Respuesta();

        if (pedidoRepository.existsById(idPedido)){
            Pedido pedido=pedidoRepository.findById(idPedido).orElse(new Pedido());
            this.notificar(pedido.getUsuario(), "ha eliminado su pedido");
            pedidoRepository.deleteById(idPedido);
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
    public Pedido obtenerPedidoPorIdPedido(Integer idPedido) throws InterruptedException {
        return pedidoRepository.findById(idPedido).orElse(new Pedido());
    }

    @Override
    public List<Pedido> listarPedido() throws InterruptedException {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido modificarEstadoPedido(Integer idPedido) throws InterruptedException {
        return null;
    }

    @Override
    public List<Pedido> listarPedidosPorIdUsuario(Usuario usuario) throws InterruptedException {
        List<Pedido> usuarioId = pedidoRepository.listarPedidosPorIdUsuario(usuario);
        return usuarioId;
    }

    @Override
    public void cancelarPedido(Integer idPedido) throws InterruptedException {
        Pedido pedido=pedidoRepository.findById(idPedido).orElse(new Pedido());
        EstadoPedido enviado=estadoPedidoRepository.pedidoEnviado();
        pedido.setEstadoPedido(enviado);
        this.notificar(pedido.getUsuario(), "ha cancelado su pedido");
    }
    @Override
    public void notificar(Usuario u2, String accion) {
        for (Usuario u:service.buscarUsuariosByRol("admin")) {
            service.actualizar(u, u2, accion);
        }
    }

}

