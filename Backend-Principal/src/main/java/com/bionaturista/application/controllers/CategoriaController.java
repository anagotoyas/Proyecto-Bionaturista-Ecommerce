package com.bionaturista.application.controllers;

import com.bionaturista.application.dto.categoria.CategoriaDto;
import com.bionaturista.application.dto.categoria.RespuestaCategoria;
import com.bionaturista.application.dto.categoria.RespuestaCategoriaEntity;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Categoria;
import com.bionaturista.domain.services.CategoriaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private CategoriaService categoriaService;

    @Value("Lista de categorias completa.")
    private String msglistaCategoria;

    @Value("Error al momento de listar categorias")
    private String msgerrorProceso;

    @Value("Busqueda realizada")
    private String msgBuscar;

    @Value("Categoria creada")
    private String msgCrear;

    @Value("Categoria modificada")
    private String msgEditar;

    @PostMapping(value="/crear", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RespuestaCategoriaEntity> crearCategoria(@RequestBody CategoriaDto categoria){

        RespuestaCategoriaEntity res = new RespuestaCategoriaEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgCrear);
            Categoria category = categoriaService.crearCategoria(categoria);

            res.setData(category);

            if (res.isSatisfactorio() == true){
                return new ResponseEntity<RespuestaCategoriaEntity>(res, HttpStatus.OK);
            }

            else {
                return new ResponseEntity<RespuestaCategoriaEntity>(res, HttpStatus.BAD_REQUEST);
            }

        } catch(InterruptedException e){
            RespuestaCategoriaEntity respuesta = new RespuestaCategoriaEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(msgerrorProceso + e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            RespuestaCategoriaEntity respuesta = new RespuestaCategoriaEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/editar", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RespuestaCategoriaEntity> editarCategoria(@RequestBody CategoriaDto categoria){

        RespuestaCategoriaEntity res = new RespuestaCategoriaEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgEditar);
            Categoria category = categoriaService.modificarCategoria(categoria);

            res.setData(category);

            if (res.isSatisfactorio() == true){
                return new ResponseEntity<RespuestaCategoriaEntity>(res, HttpStatus.OK);
            }

            else {
                return new ResponseEntity<RespuestaCategoriaEntity>(res, HttpStatus.BAD_REQUEST);
            }

        } catch(InterruptedException e){
            RespuestaCategoriaEntity respuesta = new RespuestaCategoriaEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(msgerrorProceso + e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            RespuestaCategoriaEntity respuesta = new RespuestaCategoriaEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="", produces = {"application/json"})
    public ResponseEntity<RespuestaCategoria> getAllCategorias() {

        RespuestaCategoria respuestaCategoria = new RespuestaCategoria();

        try {

            respuestaCategoria.setSatisfactorio(true);
            respuestaCategoria.setCodigo("101");
            respuestaCategoria.setMensaje(msglistaCategoria);

            List<Categoria> lista = categoriaService.getAllCategorias();

            respuestaCategoria.setData(lista);
            System.out.println(respuestaCategoria);
            return new ResponseEntity<>(respuestaCategoria, HttpStatus.OK);

        } catch(Exception e){

            respuestaCategoria.setSatisfactorio(false);
            respuestaCategoria.setCodigo("109");
            respuestaCategoria.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuestaCategoria,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/listar", produces = {"application/json"})
    public ResponseEntity<RespuestaCategoria> listarCategorias() {

        RespuestaCategoria respuestaCategoria = new RespuestaCategoria();

        try {

            respuestaCategoria.setSatisfactorio(true);
            respuestaCategoria.setCodigo("101");
            respuestaCategoria.setMensaje(msglistaCategoria);

            List<Categoria> lista = categoriaService.listarCategoria();

            respuestaCategoria.setData(lista);
            System.out.println(respuestaCategoria);
            return new ResponseEntity<>(respuestaCategoria, HttpStatus.OK);

        }
        catch(InterruptedException e){
            respuestaCategoria.setSatisfactorio(false);
            respuestaCategoria.setCodigo("109");
            respuestaCategoria.setMensaje(msgerrorProceso);
            return new ResponseEntity<>(respuestaCategoria, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){

            respuestaCategoria.setSatisfactorio(false);
            respuestaCategoria.setCodigo("109");
            respuestaCategoria.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuestaCategoria,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/buscar/{idCategoria}", produces = {"application/json"})
    public ResponseEntity<RespuestaCategoriaEntity> getCategoriaPorId(@PathVariable("idCategoria") Integer idCategoria){

        RespuestaCategoriaEntity res = new RespuestaCategoriaEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje(msgBuscar);

            Categoria categoria = categoriaService.obtenerCategoriaPorIdCategoria(idCategoria);

            res.setData(categoria);

            if (res.getData().getIdCategoria() == null) {
                res.setMensaje("No se encontró la categoria");
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

    @DeleteMapping(value = "/eliminar/{idCategoria}", produces = {"application/json"})
    public ResponseEntity<Respuesta> borrarCategoriaPorId(@PathVariable("idCategoria") Integer idCategoria){

        Respuesta res = new Respuesta();

        try {

            res = categoriaService.eliminarCategoria(idCategoria);
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



}
