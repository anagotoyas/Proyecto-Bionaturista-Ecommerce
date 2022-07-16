package com.bionaturista.application.dto.pedido;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Pedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RespuestaPedidoEntity extends Respuesta {
    @JsonProperty("dato")
    Pedido data;
}
