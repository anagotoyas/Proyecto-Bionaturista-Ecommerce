package com.bionaturista.application.dto.producto;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Producto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RespuestaProducto extends Respuesta {

    @JsonProperty("datos")
    List<Producto> data;
}
