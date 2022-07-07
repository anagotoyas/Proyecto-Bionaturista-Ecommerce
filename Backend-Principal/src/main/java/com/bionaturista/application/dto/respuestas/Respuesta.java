package com.bionaturista.application.dto.respuestas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Respuesta {

    @JsonProperty("satisfactorio")
    boolean satisfactorio;
    @JsonProperty("codigo")
    String codigo;
    @JsonProperty("mensaje")
    String mensaje;

}
