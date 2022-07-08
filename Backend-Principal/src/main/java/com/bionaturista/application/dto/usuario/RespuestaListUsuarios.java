package com.bionaturista.application.dto.usuario;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespuestaListUsuarios extends Respuesta {

    @JsonProperty("dato")
    List<Usuario> data;
}