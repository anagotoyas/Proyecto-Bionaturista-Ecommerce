package com.bionaturista.application.dto.infoEnvio;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.InfoEnvio;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString

public class RespuestaInfoEnvio extends Respuesta {

    @JsonProperty("datos")
    List<InfoEnvio> data;
}
