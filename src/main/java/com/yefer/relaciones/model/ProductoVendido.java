package com.yefer.relaciones.model;

import javax.persistence.*;

/**
 * la clase PRODUCTOVENDIDO  sus entidades  comparte una relacion con clase Venta (@JoinColumn private Venta venta)
 * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
 *
 * @since [1.0.0]
 *
 */
@Entity
public class ProductoVendido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Float cantidad, precio;
    private String nombre, codigo;
    @ManyToOne
    @JoinColumn
    private Venta venta;

    /**
     * Contiene el constructor con sus parametros inicializados sin el atributo id
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    public ProductoVendido(Float cantidad, Float precio, String nombre, String codigo, Venta venta) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombre = nombre;
        this.codigo = codigo;
        this.venta = venta;
    }

    /**
     * Contiene el constructor sin datos
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */

    public ProductoVendido() {
    }

    /**
     * contiene los getter y setter
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    public Float getTotal() {
        return this.cantidad * this.precio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
