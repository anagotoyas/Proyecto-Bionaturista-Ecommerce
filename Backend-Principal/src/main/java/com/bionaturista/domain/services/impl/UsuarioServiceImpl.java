package com.bionaturista.domain.services.impl;

import com.bionaturista.application.dto.producto.ProductoDto;
import com.bionaturista.domain.entities.Producto;
import com.bionaturista.domain.entities.Usuario;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    protected final Log logger = LogFactory.getLog(getClass());

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
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

}

