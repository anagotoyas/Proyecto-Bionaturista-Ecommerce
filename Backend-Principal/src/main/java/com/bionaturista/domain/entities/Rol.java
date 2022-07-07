package com.bionaturista.domain.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @NotNull
    @Size(min=3,max=50, message="El nombre debe ser mínimo 3 caracteres y máximo 50.")
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreRol;

    @OneToMany(mappedBy ="rol")
    private List<Usuario> usuario;

}

