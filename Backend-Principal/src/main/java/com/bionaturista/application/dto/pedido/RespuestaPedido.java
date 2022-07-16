package com.bionaturista.application.dto.pedido;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Pedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

public class RespuestaPedido extends Respuesta {
    @JsonProperty("datos")
    List<Pedido> data;
}
