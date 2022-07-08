package com.bionaturista.application.dto.usuario;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Compuesto;
import com.bionaturista.domain.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaUsuario extends Respuesta {

    @JsonProperty("dato")
    Usuario data;
}