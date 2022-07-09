package com.bionaturista.application.controllers;

import com.bionaturista.application.dto.compuesto.CompuestoDto;
import com.bionaturista.application.dto.compuesto.RespuestaCompuesto;
import com.bionaturista.application.dto.compuesto.RespuestaCompuestoEntity;
import com.bionaturista.application.dto.estadoPedido.RespuestaEstadoPedido;
import com.bionaturista.application.dto.estadoPedido.RespuestaEstadoPedidoEntity;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Compuesto;
import com.bionaturista.domain.entities.EstadoPedido;
import com.bionaturista.domain.services.EstadoPedidoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoPedidoController {

    @Autowired
    EstadoPedidoService estadoPedidoService;

    @Value("Lista de estados de pedidos completa.")
    private String msgListaEstado;

    @Value("Error al momento de listar estados de pedidos.")
    private String msgErrorProceso;

    @Value("Búsqueda realizada.")
    private String msgBuscar;

    @Value("Estado de pedido creado")
    private String msgCrear;

    @Value("Estado de pedido modificado")
    private String msgEditar;


    @PostMapping(value="/crear", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RespuestaEstadoPedidoEntity> crearEstado(@RequestBody EstadoPedido estadoPedido){

        RespuestaEstadoPedidoEntity res = new RespuestaEstadoPedidoEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgCrear);
            EstadoPedido estado1 = estadoPedidoService.crearEstado(estadoPedido);

            res.setData(estado1);

            if (res.isSatisfactorio() == true){
                return new ResponseEntity<RespuestaEstadoPedidoEntity>(res, HttpStatus.OK);
            }

            else {
                return new ResponseEntity<RespuestaEstadoPedidoEntity>(res, HttpStatus.BAD_REQUEST);
            }

        } catch(InterruptedException e){
            RespuestaEstadoPedidoEntity respuesta = new RespuestaEstadoPedidoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(msgErrorProceso + e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            RespuestaEstadoPedidoEntity respuesta = new RespuestaEstadoPedidoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/editar", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RespuestaEstadoPedidoEntity> modificarEstado(@RequestBody EstadoPedido estadoPedido){

        RespuestaEstadoPedidoEntity res = new RespuestaEstadoPedidoEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgEditar);
            EstadoPedido estado1 = estadoPedidoService.modificarEstado(estadoPedido);

            res.setData(estado1);

            if (res.isSatisfactorio() == true){
                return new ResponseEntity<RespuestaEstadoPedidoEntity>(res, HttpStatus.OK);
            }

            else {
                return new ResponseEntity<RespuestaEstadoPedidoEntity>(res, HttpStatus.BAD_REQUEST);
            }

        } catch(InterruptedException e){
            RespuestaEstadoPedidoEntity respuesta = new RespuestaEstadoPedidoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(msgErrorProceso + e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            RespuestaEstadoPedidoEntity respuesta = new RespuestaEstadoPedidoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/eliminar/{idEstado}", produces = {"application/json"})
    public ResponseEntity<Respuesta> eliminarEstado(@PathVariable("idEstado") Integer idEstado){

        Respuesta res = new Respuesta();

        try {

            res = estadoPedidoService.eliminarEstado(idEstado);
            System.out.println(res);
            return new ResponseEntity<>(res, HttpStatus.OK);

        }
        catch(InterruptedException e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(msgErrorProceso);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){

            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(e.getMessage());
            return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/listar", produces = {"application/json"})
    public ResponseEntity<RespuestaEstadoPedido> listarCompuestos() {

        RespuestaEstadoPedido respuestaEstadoPedido = new RespuestaEstadoPedido();

        try {

            respuestaEstadoPedido.setSatisfactorio(true);
            respuestaEstadoPedido.setCodigo("101");
            respuestaEstadoPedido.setMensaje(msgListaEstado);

            List<EstadoPedido> lista = estadoPedidoService.listarEstado();

            respuestaEstadoPedido.setData(lista);

            return new ResponseEntity<>(respuestaEstadoPedido, HttpStatus.OK);

        }
        catch(InterruptedException e){
            respuestaEstadoPedido.setSatisfactorio(false);
            respuestaEstadoPedido.setCodigo("109");
            respuestaEstadoPedido.setMensaje(msgErrorProceso);
            return new ResponseEntity<>(respuestaEstadoPedido, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){

            respuestaEstadoPedido.setSatisfactorio(false);
            respuestaEstadoPedido.setCodigo("109");
            respuestaEstadoPedido.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuestaEstadoPedido,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




    @GetMapping(value = "/buscar/{idEstado}", produces = {"application/json"})
    public ResponseEntity<RespuestaEstadoPedidoEntity> obtenerEstadoPorIdEstado(@PathVariable("idEstado") Integer idEstado){

        RespuestaEstadoPedidoEntity res = new RespuestaEstadoPedidoEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgBuscar);

            EstadoPedido estadoPedido = estadoPedidoService.obtenerEstadoPorIdEstado(idEstado);

            res.setData(estadoPedido);

            if (res.getData().getIdEstado() == null) {
                res.setMensaje("No se encontró el estado de pedido");
            }

            return new ResponseEntity<>(res, HttpStatus.OK);

        }
        catch(InterruptedException e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(msgErrorProceso);
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





