package com.bionaturista.application.controllers;

import com.bionaturista.application.dto.categoria.CategoriaDto;
import com.bionaturista.application.dto.categoria.RespuestaCategoria;
import com.bionaturista.application.dto.categoria.RespuestaCategoriaEntity;
import com.bionaturista.application.dto.compuesto.CompuestoDto;
import com.bionaturista.application.dto.compuesto.RespuestaCompuesto;
import com.bionaturista.application.dto.compuesto.RespuestaCompuestoEntity;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Categoria;
import com.bionaturista.domain.entities.Compuesto;
import com.bionaturista.domain.services.CompuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compuestos")
public class CompuestoController {

    @Autowired
    CompuestoService compuestoService;

    @Value("Lista de compuestos completa.")
    private String msglistaCompuesto;

    @Value("Error al momento de listar compuestos")
    private String msgerrorProceso;

    @Value("Busqueda realizada")
    private String msgBuscar;

    @Value("Compuesto creado")
    private String msgCrear;

    @Value("Compuesto modificado")
    private String msgEditar;

    @PostMapping(value="/crear", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RespuestaCompuestoEntity> crearCompuesto(@RequestBody CompuestoDto compuesto){

        RespuestaCompuestoEntity res = new RespuestaCompuestoEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgCrear);
            Compuesto compuesto1 = compuestoService.crearCompuesto(compuesto);

            res.setData(compuesto1);

            if (res.isSatisfactorio() == true){
                return new ResponseEntity<RespuestaCompuestoEntity>(res, HttpStatus.OK);
            }

            else {
                return new ResponseEntity<RespuestaCompuestoEntity>(res, HttpStatus.BAD_REQUEST);
            }

        } catch(InterruptedException e){
            RespuestaCompuestoEntity respuesta = new RespuestaCompuestoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(msgerrorProceso + e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            RespuestaCompuestoEntity respuesta = new RespuestaCompuestoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/editar", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RespuestaCompuestoEntity> editarCompuesto(@RequestBody CompuestoDto compuesto){

        RespuestaCompuestoEntity res = new RespuestaCompuestoEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgEditar);
            Compuesto compuesto1 = compuestoService.modificarCompuesto(compuesto);

            res.setData(compuesto1);

            if (res.isSatisfactorio() == true){
                return new ResponseEntity<RespuestaCompuestoEntity>(res, HttpStatus.OK);
            }

            else {
                return new ResponseEntity<RespuestaCompuestoEntity>(res, HttpStatus.BAD_REQUEST);
            }

        } catch(InterruptedException e){
            RespuestaCompuestoEntity respuesta = new RespuestaCompuestoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(msgerrorProceso + e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            RespuestaCompuestoEntity respuesta = new RespuestaCompuestoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/eliminar/{idCompuesto}", produces = {"application/json"})
    public ResponseEntity<Respuesta> borrarCompuestoPorId(@PathVariable("idCompuesto") Integer idCompuesto){

        Respuesta res = new Respuesta();

        try {

            res = compuestoService.eliminarCompuesto(idCompuesto);
            System.out.println(res);
            return new ResponseEntity<>(res, HttpStatus.OK);

        }
        catch(InterruptedException e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(msgerrorProceso);
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
    public ResponseEntity<RespuestaCompuesto> listarCompuestos() {

        RespuestaCompuesto respuestaCompuesto = new RespuestaCompuesto();

        try {

            respuestaCompuesto.setSatisfactorio(true);
            respuestaCompuesto.setCodigo("101");
            respuestaCompuesto.setMensaje(msglistaCompuesto);

            List<Compuesto> lista = compuestoService.listarCompuesto();

            respuestaCompuesto.setData(lista);

            return new ResponseEntity<>(respuestaCompuesto, HttpStatus.OK);

        }
        catch(InterruptedException e){
            respuestaCompuesto.setSatisfactorio(false);
            respuestaCompuesto.setCodigo("109");
            respuestaCompuesto.setMensaje(msgerrorProceso);
            return new ResponseEntity<>(respuestaCompuesto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){

            respuestaCompuesto.setSatisfactorio(false);
            respuestaCompuesto.setCodigo("109");
            respuestaCompuesto.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuestaCompuesto,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value="", produces = {"application/json"})
    public ResponseEntity<RespuestaCompuesto> getAllCompuestos() {

        RespuestaCompuesto respuestaCompuesto = new RespuestaCompuesto();

        try {

            respuestaCompuesto.setSatisfactorio(true);
            respuestaCompuesto.setCodigo("101");
            respuestaCompuesto.setMensaje(msglistaCompuesto);

            List<Compuesto> lista = compuestoService.getAllCompuestos();

            respuestaCompuesto.setData(lista);

            return new ResponseEntity<>(respuestaCompuesto, HttpStatus.OK);

        } catch(Exception e){

            respuestaCompuesto.setSatisfactorio(false);
            respuestaCompuesto.setCodigo("109");
            respuestaCompuesto.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuestaCompuesto,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/buscar/{idCompuesto}", produces = {"application/json"})
    public ResponseEntity<RespuestaCompuestoEntity> getCompuestoPorId(@PathVariable("idCompuesto") Integer idCompuesto){

        RespuestaCompuestoEntity res = new RespuestaCompuestoEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgBuscar);

            Compuesto compuesto = compuestoService.obtenerCompuestoPorIdCompuesto(idCompuesto);

            res.setData(compuesto);

            if (res.getData().getIdCompuesto() == null) {
                res.setMensaje("No se encontró el compuesto");
            }

            return new ResponseEntity<>(res, HttpStatus.OK);

        }
        catch(InterruptedException e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(msgerrorProceso);
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
