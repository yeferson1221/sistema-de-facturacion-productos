package com.yefer.relaciones.repository;

import com.yefer.relaciones.model.ProductoVendido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosVendidosRepository extends CrudRepository<ProductoVendido, Integer> {

}
