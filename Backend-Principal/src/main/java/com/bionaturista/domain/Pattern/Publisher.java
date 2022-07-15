package com.bionaturista.domain.Pattern;

import com.bionaturista.domain.entities.Usuario;

public interface Publisher {
    void notificar(Usuario u, String accion);
}
