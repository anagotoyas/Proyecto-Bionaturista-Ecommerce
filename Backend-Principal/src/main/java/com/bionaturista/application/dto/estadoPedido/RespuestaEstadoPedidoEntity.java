package com.bionaturista.application.dto.estadoPedido;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.EstadoPedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class RespuestaEstadoPedidoEntity extends Respuesta {

    @JsonProperty("dato")
    EstadoPedido data;
}
