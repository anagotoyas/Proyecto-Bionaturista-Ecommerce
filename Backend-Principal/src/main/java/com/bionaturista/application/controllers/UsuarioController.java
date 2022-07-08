package com.bionaturista.application.controllers;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.application.dto.usuario.RespuestaCarrito;
import com.bionaturista.application.dto.usuario.RespuestaListUsuarios;
import com.bionaturista.application.dto.usuario.RespuestaUsuario;
import com.bionaturista.domain.entities.Producto;
import com.bionaturista.domain.entities.Usuario;
import com.bionaturista.domain.services.UsuarioService;
import com.bionaturista.domain.services.PedidoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private  final UsuarioService usuarioService;
    private PedidoService pedidoService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<RespuestaUsuario>> registrarUsuario(@Valid @RequestBody Usuario usuario) {


        RespuestaUsuario respuesta = new RespuestaUsuario();

        try {
            Usuario usuarioNew = usuarioService.crearUsuario(usuario);

            respuesta.setMensaje("El usuario se ha encontrado con exito");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");
            respuesta.setData(usuarioNew);

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (Exception e){

            respuesta.setMensaje("El no usuario se ha encontrado con exito");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping("/login")
    public ResponseEntity<WrapperResponse<RespuestaUsuario>> logIn (@Valid @RequestBody Usuario user) throws Exception{

        RespuestaUsuario respuesta = new RespuestaUsuario();

        try {
            String correoUsuario= user.getCorreoUsuario();
            String contrasenaUsuario= user.getContrasenaUsuario();
            Usuario userObj=null;
            if (correoUsuario !=null && contrasenaUsuario!= null){
                userObj=usuarioService.fetchUserByCorreoyContra(correoUsuario,contrasenaUsuario);
            }

            respuesta.setMensaje("Ha iniciado sesión con éxito");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");
            respuesta.setData(userObj);

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (Exception e){

            respuesta.setMensaje("No se ha podido iniciar sesión");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<WrapperResponse<RespuestaListUsuarios>> listarUsuario(){

        RespuestaListUsuarios respuesta = new RespuestaListUsuarios();

        try {
            List<Usuario> usuarios = usuarioService.listarUsuario();

            respuesta.setMensaje("success");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");
            respuesta.setData(usuarios);

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (Exception e){

            respuesta.setMensaje("failed");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }


    }
    @PutMapping("/{idUsuario}/carrito/{idProducto}")
    public ResponseEntity<WrapperResponse<Respuesta>> agregarProductoAlCarrito(@PathVariable Integer idUsuario, @PathVariable Integer idProducto) {

        Respuesta respuesta = new Respuesta();

        try {
            usuarioService.agregarProductoAlCarrito(idUsuario,idProducto);

            respuesta.setMensaje("El producto se ha agregado con exito");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (InterruptedException e){

            respuesta.setMensaje("No se ha podido agregar el producto");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }



    }
    @DeleteMapping("/{idUsuario}/carrito/{idProducto}")
    public ResponseEntity<WrapperResponse<Respuesta>> eliminarProductoAlCarrito(@PathVariable Integer idUsuario, @PathVariable Integer idProducto) {

        Respuesta respuesta = new Respuesta();

        try {
            usuarioService.eliminarProductoAlCarrito(idUsuario, idProducto);

            respuesta.setMensaje("El producto se ha eliminado con exito");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (InterruptedException e){

            respuesta.setMensaje("No se ha podido eliminar el producto");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<WrapperResponse<RespuestaUsuario>> obtenerUsuarioPorIdUsuario(@PathVariable("idUsuario") Integer idUsuario) {


        RespuestaUsuario respuesta = new RespuestaUsuario();

        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorIdUsuario(idUsuario);

            respuesta.setMensaje("El usuario se ha encontrado con exito");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");
            respuesta.setData(usuario);

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (Exception e){

            respuesta.setMensaje("El no usuario se ha encontrado con exito");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{idUsuario}/carrito")
    public ResponseEntity<WrapperResponse<RespuestaCarrito>> obtenerCarritoPorIdUsuario(@PathVariable("idUsuario") Integer idUsuario) {


        RespuestaCarrito respuesta = new RespuestaCarrito();

        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorIdUsuario(idUsuario);

            respuesta.setMensaje("El usuario se ha encontrado con exito");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");
            respuesta.setData(usuario.getCarritoCompras());

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (Exception e){

            respuesta.setMensaje("El no usuario se ha encontrado con exito");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }

    }
}

