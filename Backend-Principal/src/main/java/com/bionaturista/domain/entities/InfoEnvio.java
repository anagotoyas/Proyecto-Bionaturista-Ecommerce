package com.bionaturista.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "infoEnvios")
@Getter
@Setter
public class InfoEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdInfoEnvio;

    private String nombreContactoEnvio;

    private String numTelefonoEnvio;

    private String direccionEnvio;

    private String apartamentoEnvio;

    private String codigoPostal;
}
