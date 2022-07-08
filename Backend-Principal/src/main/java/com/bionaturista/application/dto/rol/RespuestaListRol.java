package com.bionaturista.application.dto.rol;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Rol;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespuestaListRol extends Respuesta {

    @JsonProperty("dato")
    List<Rol> data;
}