package com.bionaturista.application.dto.compuesto;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Compuesto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RespuestaCompuesto extends Respuesta {

    @JsonProperty("datos")
    List<Compuesto> data;
}
