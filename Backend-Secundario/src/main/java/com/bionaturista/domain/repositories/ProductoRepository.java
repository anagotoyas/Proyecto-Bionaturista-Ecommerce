package com.bionaturista.domain.repositories;

import com.bionaturista.domain.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value= "SELECT COUNT(*) FROM Producto", nativeQuery = true)
    Long contarProductos();

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombreProducto) LIKE %:nombreProducto%")
    List<Producto> buscarPorNombre(@Param("nombreProducto") String nombreProducto);

}
