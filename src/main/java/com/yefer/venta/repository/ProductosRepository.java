package com.yefer.venta.repository;

import com.yefer.venta.model.Producto;
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
public interface ProductosRepository extends CrudRepository<Producto, Integer> {

    Producto findFirstByCodigo(String codigo);
}