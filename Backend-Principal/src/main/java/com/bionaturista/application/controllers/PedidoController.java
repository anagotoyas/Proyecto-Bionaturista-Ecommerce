package com.bionaturista.application.controllers;

import com.bionaturista.application.dto.pedido.RespuestaPedido;
import com.bionaturista.application.dto.pedido.RespuestaPedidoEntity;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Pedido;
import com.bionaturista.domain.entities.Usuario;
import com.bionaturista.domain.repositories.UsuarioRepository;
import com.bionaturista.domain.services.PedidoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("Pedido Pagado.")
    private String msgPedidoPagado;

    @Value("Pedido Modificado.")
    private String msgPedidoModificado;

    @Value("Pedido Eliminado.")
    private String msgPedidoEliminado;

    @Value("Lista de Pedidos Completa.")
    private String msgPedidoLista;

    @Value("Búsqueda de Pedido Realizada.")
    private String msgPedidoBusca;

    @Value("Lista de Pedidos del Usuario Completa.")
    private String msgPedidosUsuario;

    @Value("Error en el Proceso.")
    private String msgPedidoError;

    @Value("El carrito de compras se encuentra vacío.")
    private String msgPedidoVacio;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping(value = "", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RespuestaPedidoEntity> pagarPedido(@Valid @RequestBody Pedido pedido){
        RespuestaPedidoEntity res = new RespuestaPedidoEntity();
        Pedido pedidoNull=null;
        Integer idUsuario=pedido.getUsuario().getIdUsuario();
        Usuario usuario=usuarioRepository.getById(idUsuario);

        try{
            res.setSatisfactorio(true);
            res.setCodigo("101");

            if (usuario.getCarritoCompras().isEmpty()){
                res.setMensaje(msgPedidoVacio);
            }
            else {
                res.setMensaje(msgPedidoPagado);
                Pedido pedidoNew = pedidoService.pagarPedido(pedido);
                res.setData(pedidoNew);
            }

            if (res.isSatisfactorio()==true){
                return new ResponseEntity<RespuestaPedidoEntity>(res, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<RespuestaPedidoEntity>(res, HttpStatus.BAD_REQUEST);
            }
        } catch (InterruptedException e){
            RespuestaPedidoEntity resExc = new RespuestaPedidoEntity();
            resExc.setSatisfactorio(false);
            resExc.setCodigo("109");
            resExc.setMensaje(msgPedidoError + e.getMessage());
            return new ResponseEntity<>(resExc, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            RespuestaPedidoEntity resExc = new RespuestaPedidoEntity();
            resExc.setSatisfactorio(false);
            resExc.setCodigo("109");
            resExc.setMensaje(e.getMessage());
            return new ResponseEntity<>(resExc, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<RespuestaPedidoEntity> modificarPedido(@Valid @RequestBody Pedido pedido){
        RespuestaPedidoEntity res = new RespuestaPedidoEntity();

        try {
            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgPedidoModificado);
            Pedido pedidoUpdate = pedidoService.modificarPedido(pedido);

            res.setData(pedidoUpdate);

            if (res.isSatisfactorio()==true){
                return new ResponseEntity<RespuestaPedidoEntity>(res, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<RespuestaPedidoEntity>(res, HttpStatus.BAD_REQUEST);
            }
        } catch (InterruptedException e){
            RespuestaPedidoEntity resExc = new RespuestaPedidoEntity();

            resExc.setSatisfactorio(false);
            resExc.setCodigo("109");
            resExc.setMensaje(msgPedidoError + e.getMessage());

            return new ResponseEntity<>(resExc, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            RespuestaPedidoEntity resExc = new RespuestaPedidoEntity();

            resExc.setSatisfactorio(false);
            resExc.setCodigo("109");
            resExc.setMensaje(e.getMessage());

            return new ResponseEntity<>(resExc, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{idPedido}", produces = {"application/json"})
    public ResponseEntity<Respuesta> eliminarPedido(@PathVariable("idPedido") Integer idPedido){
        Respuesta respuesta = new Respuesta();

        try{
            respuesta = pedidoService.eliminarPedido(idPedido);
            System.out.println(respuesta);

            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (InterruptedException e){
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(msgPedidoError);

            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e){

            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "", produces = {"application/json"})
    public ResponseEntity<RespuestaPedido> listarPedido() {
        RespuestaPedido res = new RespuestaPedido();

        try {
            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgPedidoLista);

            List<Pedido> pedidos = pedidoService.listarPedido();

            res.setData(pedidos);
            System.out.println(res);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (InterruptedException e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(msgPedidoError);

            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(e.getMessage());

            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{idPedido}", produces = {"application/json"})
    public ResponseEntity<RespuestaPedidoEntity> obtenerPedidoPorIdPedido(@PathVariable("idPedido") Integer idPedido){
        RespuestaPedidoEntity res = new RespuestaPedidoEntity();

        try {
            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgPedidoBusca);

            Pedido pedido = pedidoService.obtenerPedidoPorIdPedido(idPedido);

            res.setData(pedido);

            if (res.getData().getIdPedido() == null){
                res.setMensaje("No se encontró el pedido.");
            }

            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (InterruptedException e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(msgPedidoError);

            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(e.getMessage());

            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listarPorIdUsuario", produces = {"application/json"})
    public ResponseEntity<RespuestaPedido> listarPedidosPorIdUsuario(@RequestParam Usuario usuario){
        RespuestaPedido res = new RespuestaPedido();

        try{
            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgPedidosUsuario);

            List<Pedido> pedidos = pedidoService.listarPedidosPorIdUsuario(usuario);

            res.setData(pedidos);

            if (res.getData().isEmpty()==true){
                res.setMensaje("El usuario no tiene pedidos.");
            }
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(InterruptedException e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(msgPedidoError);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(e.getMessage());
            return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
