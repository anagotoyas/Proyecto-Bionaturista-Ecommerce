package com.bionaturista.application.dto.usuario;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Producto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class RespuestaCarrito extends Respuesta {

    @JsonProperty("dato")
    Set<Producto> data;

}
