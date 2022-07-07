package com.bionaturista.application.dto.compuesto;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Compuesto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RespuestaCompuestoEntity extends Respuesta {

    @JsonProperty("dato")
    Compuesto data;

}
