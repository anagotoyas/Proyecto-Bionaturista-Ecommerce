package com.bionaturista.domain.services;

import com.bionaturista.domain.entities.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario);
    Usuario modificarUsuario(Usuario usuario);
    void eliminarUsuario(Integer idUsuario);
    List<Usuario> listarUsuario();
    Usuario obtenerUsuarioPorIdUsuario(Integer idUsuario);
    //void agregarProductoAlCarrito(Usuario usuario, Producto producto);
    //void eliminarProductoAlCarrito(Usuario usuario, Producto producto);

    Usuario fetchUserByCorreoyContra(String correoUsuario, String contrasenaUsuario);


}
