package com.bionaturista.application.dto.estadoPedido;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.EstadoPedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString

public class RespuestaEstadoPedido extends Respuesta {
    @JsonProperty("datos")
    List<EstadoPedido> data;
}
