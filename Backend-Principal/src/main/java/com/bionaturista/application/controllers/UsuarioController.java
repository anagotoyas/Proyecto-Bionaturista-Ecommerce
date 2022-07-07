package com.bionaturista.application.controllers;

import com.bionaturista.application.dto.respuestas.Respuesta;
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
    public ResponseEntity<WrapperResponse<Usuario>> registrarUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioNew = usuarioService.crearUsuario(usuario);
        return new WrapperResponse<>(true,"success", usuarioNew).createResponse();
    }
    @PostMapping("/login")
    public ResponseEntity<WrapperResponse<Usuario>> logIn (@Valid @RequestBody Usuario user) throws Exception{
        String correoUsuario= user.getCorreoUsuario();
        String contrasenaUsuario= user.getContrasenaUsuario();
        Usuario userObj=null;
        if (correoUsuario !=null && contrasenaUsuario!= null){
            userObj=usuarioService.fetchUserByCorreoyContra(correoUsuario,contrasenaUsuario);
        }
        if(userObj==null){
            throw new Exception("malas credenciales");
        }
        return new WrapperResponse<>(true,"success", userObj).createResponse();}

    @GetMapping
    public ResponseEntity<WrapperResponse<List<Usuario>>> listarUsuario(){
        List<Usuario> usuarios = usuarioService.listarUsuario();
        return new WrapperResponse<>(true, "success", usuarios).createResponse();
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
    public ResponseEntity<WrapperResponse<Usuario>> obtenerUsuarioPorIdUsuario(@PathVariable("idUsuario") Integer idUsuario) {
        Usuario usuario = usuarioService.obtenerUsuarioPorIdUsuario(idUsuario);
        return new WrapperResponse<>(true,"success",usuario).createResponse();
    }

    @GetMapping("/{idUsuario}/carrito")
    public Set<Producto> obtenerCarritoPorIdUsuario(@PathVariable("idUsuario") Integer idUsuario) {
      Usuario usuario = usuarioService.obtenerUsuarioPorIdUsuario(idUsuario);
      return usuario.getCarritoCompras();
    }
}

