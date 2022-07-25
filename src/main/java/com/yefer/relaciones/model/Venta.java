package com.yefer.relaciones.model;

import javax.persistence.*;
import java.util.Set;

/**
 * la clase VENTA  sus entidades  comparte una relacion con clase ProductoVendido ( @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
 *     private Set<ProductoVendido> productos;)  donde una venta puede tener muchos Productos vendidos
 * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
 *
 * @since [1.0.0]
 *
 */
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fechaYHora;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Set<ProductoVendido> productos;

    /**
     * contiene constructor sin datos
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    public Venta() {


       // this.fechaYHora = Utiles.obtenerFechaYHoraActual();
    }


    /**
     * contiene los getter y setter
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTotal() {
        Float total = 0f;
        for (ProductoVendido productoVendido : this.productos) {
            total += productoVendido.getTotal();
        }
        return total;
    }

    public String getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(String fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Set<ProductoVendido> getProductos() {
        return productos;
    }

    public void setProductos(Set<ProductoVendido> productos) {
        this.productos = productos;
    }
}
