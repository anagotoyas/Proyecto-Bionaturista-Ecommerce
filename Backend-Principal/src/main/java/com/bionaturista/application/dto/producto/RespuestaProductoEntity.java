package com.bionaturista.application.dto.producto;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Producto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RespuestaProductoEntity extends Respuesta {

    @JsonProperty("dato")
    Producto data;
}
