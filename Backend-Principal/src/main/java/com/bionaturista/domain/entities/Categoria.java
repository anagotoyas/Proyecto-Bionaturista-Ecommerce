package com.bionaturista.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@Table(name="categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty("idCategoria")
    private Integer idCategoria;

    @NotNull
    @Size(min = 5, max = 50, message = "La categoria tiene como maximo 50 caracteres")
    @JsonProperty("nombreCategoria")
    @Column(name = "nombre_categoria", nullable = false, length = 50)
    private String nombreCategoria;

}
