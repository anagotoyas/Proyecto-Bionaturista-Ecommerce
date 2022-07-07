package com.bionaturista.domain.services.impl;

import com.bionaturista.domain.entities.Producto;
import com.bionaturista.domain.entities.Usuario;
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
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    protected final Log logger = LogFactory.getLog(getClass());


    @Value("${service.productos}")
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
        Producto producto;
        RestTemplate restTemplate=new RestTemplate();
        String findByIdProduct = this.servicioProducto + "/"+idProducto;
        Usuario usuario = this.obtenerUsuarioPorIdUsuario(idUsuario);

        ResponseEntity<Producto> response = restTemplate.exchange(findByIdProduct, HttpMethod.GET, null,
                new ParameterizedTypeReference<Producto>() {
                });
        if(response.getStatusCode() == HttpStatus.OK){
            producto = response.getBody();
            assert producto != null;
            if(producto.getStockP()>0){
                usuario.getCarritoCompras().add(producto);
            }
        }else{
            logger.error("La respuesta del servicio no se ha procesado con exito ErrorCode:" + response.getStatusCode());
            throw new InterruptedException("Error al invocar el servicio. Error Code: " + response.getStatusCode());
        }
    }


    @Override
    public void eliminarProductoAlCarrito(Integer idUsuario, Integer idProducto) throws InterruptedException{
        Usuario usuario = this.obtenerUsuarioPorIdUsuario(idUsuario);
        Producto producto;
        RestTemplate restTemplate=new RestTemplate();
        String findByIdProduct = this.servicioProducto + "/"+idProducto;
        ResponseEntity<Producto> response = restTemplate.exchange(findByIdProduct, HttpMethod.GET, null,
                new ParameterizedTypeReference<Producto>() {
                });
        if(response.getStatusCode() == HttpStatus.OK) {
            producto = response.getBody();
            usuario.getCarritoCompras().remove(producto);
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

