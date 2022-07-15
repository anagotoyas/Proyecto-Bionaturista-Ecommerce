package com.bionaturista.domain.repositories;

import com.bionaturista.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {


    Usuario findUsuarioByCorreoUsuarioAndContrasenaUsuario(String correoUsuario, String contrasenaUsuario);

    @Query("SELECT u FROM Usuario u WHERE u.rol.nombreRol=:rol")
    List<Usuario> findAllByRol(@Param("rol") String rol);

}
