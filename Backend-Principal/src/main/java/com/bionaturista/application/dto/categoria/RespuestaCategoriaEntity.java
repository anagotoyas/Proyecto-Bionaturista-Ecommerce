package com.bionaturista.application.dto.categoria;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RespuestaCategoriaEntity extends Respuesta {

    @JsonProperty("dato")
    Categoria data;

}
