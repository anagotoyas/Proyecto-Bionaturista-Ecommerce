package com.bionaturista.domain.services.impl;

import com.bionaturista.application.dto.producto.ProductoDto;
import com.bionaturista.domain.Pattern.Subscriber;
import com.bionaturista.domain.entities.Notificacion;
import com.bionaturista.domain.entities.Pedido;
import com.bionaturista.domain.entities.Producto;
import com.bionaturista.domain.entities.Usuario;
import com.bionaturista.domain.repositories.NotificacionRepository;
import com.bionaturista.domain.repositories.ProductoRepository;
import com.bionaturista.domain.repositories.UsuarioRepository;
import com.bionaturista.domain.services.UsuarioService;
import com.bionaturista.validators.UsuarioValidator;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService, Subscriber {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    private final NotificacionRepository notificacionRepository;

    protected final Log logger = LogFactory.getLog(getClass());

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ProductoRepository productoRepository, NotificacionRepository notificacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.notificacionRepository = notificacionRepository;

    }

    @Value("${service.producto}")
    private String servicioProducto;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        UsuarioValidator.validate(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario modificarUsuario(Usuario usuario) {
        return null;
    }



    @Override
    public void eliminarUsuario(Integer idUsuario) {

    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> buscarUsuariosByRol(String rol) {
        return this.usuarioRepository.findAllByRol(rol);
    }

    @Override
    public Usuario obtenerUsuarioPorIdUsuario(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(new Usuario());
    }


    @Override
    public void agregarProductoAlCarrito(int idUsuario, int idProducto) throws InterruptedException{

        ProductoDto producto;
        RestTemplate restTemplate=new RestTemplate();
        String findByIdProduct = this.servicioProducto + "/"+idProducto;
        Usuario usuario = this.obtenerUsuarioPorIdUsuario(idUsuario);

        ResponseEntity<ProductoDto> response = restTemplate.exchange(findByIdProduct, HttpMethod.GET, null,
                new ParameterizedTypeReference<ProductoDto>() {
                });
        if(response.getStatusCode() == HttpStatus.OK){
            producto = response.getBody();
            Producto productoEntidad = this.productoRepository.findById(producto.getIdProducto()).orElse(new Producto());
            if(producto.getStockProducto()>0){
                usuario.getCarritoCompras().add(productoEntidad);

                usuarioRepository.save(usuario);
            }
        }else{
            logger.error("La respuesta del servicio no se ha procesado con exito ErrorCode:" + response.getStatusCode());
            throw new InterruptedException("Error al invocar el servicio. Error Code: " + response.getStatusCode());
        }
    }


    @Override
    public void eliminarProductoAlCarrito(Integer idUsuario, Integer idProducto) throws InterruptedException{
        Usuario usuario = this.obtenerUsuarioPorIdUsuario(idUsuario);
        ProductoDto producto;
        RestTemplate restTemplate=new RestTemplate();
        String findByIdProduct = this.servicioProducto + "/"+idProducto;
        ResponseEntity<ProductoDto> response = restTemplate.exchange(findByIdProduct, HttpMethod.GET, null,
                new ParameterizedTypeReference<ProductoDto>() {
                });
        if(response.getStatusCode() == HttpStatus.OK) {
            producto = response.getBody();

            Producto productoEntidad = this.productoRepository.findById(producto.getIdProducto()).orElse(new Producto());

            usuario.getCarritoCompras().remove(productoEntidad);
        }else{
            logger.error("La respuesta del servicio no se ha procesado con exito ErrorCode:" + response.getStatusCode());
            throw new InterruptedException("Error al invocar el servicio. Error Code: " + response.getStatusCode());
        }
    }

    @Override
    public Usuario fetchUserByCorreoyContra(String correoUsuario, String contrasenaUsuario){
        return usuarioRepository.findUsuarioByCorreoUsuarioAndContrasenaUsuario(correoUsuario, contrasenaUsuario);
    }

    @Override
    public void actualizar(Usuario u, Usuario u2, String accion) {
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuario(u);
        notificacion.setContenido("El usuario "+ u2.getNombreUsuario()+" "+accion);
        this.notificacionRepository.save(notificacion);

    }
}

