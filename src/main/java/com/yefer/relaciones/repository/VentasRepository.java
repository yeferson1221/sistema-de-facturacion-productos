package com.yefer.relaciones.repository;

import com.yefer.relaciones.model.Venta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepository extends CrudRepository<Venta, Integer> {
}
