package com.bionaturista.domain.services;

import com.bionaturista.domain.entities.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario);

    Usuario modificarUsuario(Usuario usuario);

    void eliminarUsuario(Integer idUsuario);

    List<Usuario> listarUsuario();

    Usuario obtenerUsuarioPorIdUsuario(Integer idUsuario);

    void agregarProductoAlCarrito(int idUsuario, int idProducto) throws InterruptedException;

    void eliminarProductoAlCarrito(Integer idUsuario, Integer idProducto) throws InterruptedException;

    Usuario fetchUserByCorreoyContra(String correoUsuario, String contrasenaUsuario);

}
