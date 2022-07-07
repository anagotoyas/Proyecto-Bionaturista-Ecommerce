package com.bionaturista.domain.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "compuestos")
public class Compuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompuesto;

    @NotNull
    @Size(min=3,max=100, message="El nombre debe ser mínimo 3 caracteres y máximo 100.")
    @Column(name = "nombreCompuesto", nullable = false)
    private String nombreCompuesto;

}
