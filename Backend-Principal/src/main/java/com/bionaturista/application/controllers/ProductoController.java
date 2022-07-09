package com.bionaturista.application.controllers;



import com.bionaturista.application.dto.categoria.RespuestaCategoriaEntity;
import com.bionaturista.application.dto.producto.ProductoDto;
import com.bionaturista.application.dto.producto.RespuestaProducto;
import com.bionaturista.application.dto.producto.RespuestaProductoEntity;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Categoria;
import com.bionaturista.domain.entities.Producto;
import com.bionaturista.domain.services.ProductoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ProductoService productoService;

    @PostMapping(value="/crear", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RespuestaProductoEntity> crearProducto(@RequestBody ProductoDto producto){

        RespuestaProductoEntity res = new RespuestaProductoEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje("Al finnnnn");
            Producto producty = productoService.crearProducto(producto);

            res.setData(producty);


            if (res.isSatisfactorio() == true){
                return new ResponseEntity<RespuestaProductoEntity>(res, HttpStatus.OK);
            }

            else {
                return new ResponseEntity<RespuestaProductoEntity>(res, HttpStatus.BAD_REQUEST);
            }

        } catch(InterruptedException e){
            RespuestaProductoEntity respuesta = new RespuestaProductoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje("lloro" + e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            RespuestaProductoEntity respuesta = new RespuestaProductoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value="/editar", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RespuestaProductoEntity> editarProducto (@RequestBody ProductoDto producto){

        RespuestaProductoEntity res = new RespuestaProductoEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje("Editado con exito");
            Producto producty = productoService.modificarProducto(producto);

            res.setData(producty);

            if (res.isSatisfactorio() == true){
                return new ResponseEntity<RespuestaProductoEntity>(res, HttpStatus.OK);
            }

            else {
                return new ResponseEntity<RespuestaProductoEntity>(res, HttpStatus.BAD_REQUEST);
            }

        } catch(InterruptedException e){
            RespuestaProductoEntity respuesta = new RespuestaProductoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje("error" + e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            RespuestaProductoEntity respuesta = new RespuestaProductoEntity();
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("109");
            respuesta.setMensaje(e.getMessage());
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/eliminar/{idProducto}", produces = {"application/json"})
    public ResponseEntity<Respuesta> borrarProductoPorId(@PathVariable("idProducto") Integer idProducto){

        Respuesta res = new Respuesta();

        try {

            res = productoService.eliminarProducto(idProducto);
            System.out.println(res);
            return new ResponseEntity<>(res, HttpStatus.OK);

        }
        catch(InterruptedException e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje("Error");
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
    public ResponseEntity<RespuestaProducto> listarProductos() {

        RespuestaProducto res= new RespuestaProducto();
        System.out.println(res);

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje("Yupi");

            List<Producto> lista = productoService.listarProducto();

            res.setData(lista);
            System.out.println(res);
            return new ResponseEntity<>(res, HttpStatus.OK);

        }
        catch(InterruptedException e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje("Lloro" + e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){

            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje(e.getMessage());
            return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/buscar/{idProducto}", produces = {"application/json"})
    public ResponseEntity<RespuestaProductoEntity> getProductoPorId(@PathVariable("idProducto") Integer idProducto){

        RespuestaProductoEntity res = new RespuestaProductoEntity();

        try {

            res.setSatisfactorio(true);
            res.setCodigo("101");
            res.setMensaje("Se encontro crack");

            Producto producto = productoService.obtenerProductoPorIdProducto(idProducto);

            res.setData(producto);

            if (res.getData().getIdProducto() == null) {
                res.setMensaje("No se encontró el producto pipipipi");
            }

            return new ResponseEntity<>(res, HttpStatus.OK);

        }
        catch(InterruptedException e){
            res.setSatisfactorio(false);
            res.setCodigo("109");
            res.setMensaje("Error");
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
