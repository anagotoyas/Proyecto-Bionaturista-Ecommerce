package com.bionaturista.domain.repositories;

import com.bionaturista.domain.entities.Pedido;
import com.bionaturista.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query("SELECT p FROM Pedido p WHERE p.usuario=:usuario")
    List<Pedido> listarPedidosPorIdUsuario(@Param("usuario") Usuario usuario);

}
