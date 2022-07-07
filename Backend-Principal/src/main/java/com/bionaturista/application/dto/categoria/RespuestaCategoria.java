package com.bionaturista.application.dto.categoria;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RespuestaCategoria extends Respuesta {

    @JsonProperty("datos")
    List<Categoria> data;

}
