package com.bionaturista.domain.repositories;

import com.bionaturista.domain.entities.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
}
