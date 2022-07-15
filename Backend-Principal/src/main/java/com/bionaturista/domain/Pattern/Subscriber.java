package com.bionaturista.domain.Pattern;

import com.bionaturista.domain.entities.Pedido;
import com.bionaturista.domain.entities.Usuario;

public interface Subscriber {
    void actualizar(Usuario u, Usuario u2, String accion);
}
