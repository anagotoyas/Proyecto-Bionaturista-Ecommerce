package com.bionaturista.domain.services;

import com.bionaturista.domain.entities.Rol;

import java.util.List;

public interface RolService {

    Rol crearRol(Rol rol);
    Rol modificarRol(Rol rol);
    void eliminarRol(Integer idRol);
    List<Rol> listarRol();
    Rol obtenerRolPorIdRol(Integer idRol);

}
