package com.bionaturista.application.dto.rol;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Rol;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuetaRol extends Respuesta {

    @JsonProperty("dato")
    Rol data;
}