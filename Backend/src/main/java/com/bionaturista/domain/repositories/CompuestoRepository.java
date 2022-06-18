package com.bionaturista.domain.repositories;

import com.bionaturista.domain.entities.Compuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompuestoRepository extends JpaRepository<Compuesto, Integer> {
}
