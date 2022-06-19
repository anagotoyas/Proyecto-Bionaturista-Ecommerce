package com.bionaturista.application.controllers;

import com.bionaturista.domain.entities.Pedido;
import com.bionaturista.domain.entities.Producto;
import com.bionaturista.domain.entities.Usuario;
import com.bionaturista.domain.repositories.ProductoRepository;
import com.bionaturista.domain.repositories.UsuarioRepository;
import com.bionaturista.domain.repositories.PedidoRepository;
import com.bionaturista.domain.services.UsuarioService;
import com.bionaturista.domain.services.PedidoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

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
    public Usuario agregarProductoAlCarrito(@PathVariable Integer idUsuario, @PathVariable Integer idProducto) {
        Producto productoN = productoRepository.findById(idProducto).get();
        Usuario usuarioN = usuarioRepository.findById(idUsuario).get();
        usuarioService.agregarProductoAlCarrito(usuarioN, productoN);
        return usuarioRepository.save(usuarioN);

    }
    @DeleteMapping("/{idUsuario}/carrito/{idProducto}")
    public Usuario eliminarProductoAlCarrito(@PathVariable Integer idUsuario, @PathVariable Integer idProducto) {
        Producto productoN = productoRepository.findById(idProducto).get();
        Usuario usuarioN = usuarioRepository.findById(idUsuario).get();
        usuarioService.eliminarProductoAlCarrito(usuarioN, productoN);
        return usuarioRepository.save(usuarioN);

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

