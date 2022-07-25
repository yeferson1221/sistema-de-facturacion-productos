package com.yefer.relaciones.repository;

import com.yefer.relaciones.model.ProductoVendido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * contiene interface  del repositorio carga metodos propios de CrudRepository
 * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
 *
 * @since [1.0.0]
 *
 */
@Repository
public interface ProductosVendidosRepository extends CrudRepository<ProductoVendido, Integer> {

}
