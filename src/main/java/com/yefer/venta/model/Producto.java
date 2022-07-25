package com.yefer.venta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * aqui en esta clase PRODUCTO encontramos el modelo  con sus parametros, tambien las librerias usadas
 * los respectivos campos tienen sus validaciones
 *
 * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
 *
 * @since [1.0.0]
 *
 */
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 50, message = "El nombre debe medir entre 1 y 50")
    private String nombre;

    @NotNull(message = "Debes especificar el código")
    @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50")
    private String codigo;

    @NotNull(message = "Debes especificar el precio")
    @Min(value = 0, message = "El precio mínimo es 0")
    private Float precio;

    @NotNull(message = "Debes especificar la existencia")
    @Min(value = 0, message = "La existencia mínima es 0")
    private Float existencia;

    /**
     * aqui en esta el contructor que inicializan sus respectivos parametros
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */

    public Producto(String nombre, String codigo, Float precio, Float existencia, Integer id) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.existencia = existencia;
        this.id = id;
    }

    /**
     * aqui en esta el contructor sin el parametro id
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    public Producto(String nombre, String codigo, Float precio, Float existencia) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.existencia = existencia;
    }

    /**
     * aqui en esta el contructor solo con el parametro codigo no debe ser null(nulo)
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    public Producto(@NotNull(message = "Debes especificar el código") @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50") String codigo) {
        this.codigo = codigo;
    }


    /**
     * constructior sin datos
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    public Producto() {
    }

    /**
     * getter y setter
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    public boolean sinExistencia() {
        return this.existencia <= 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getExistencia() {
        return existencia;
    }

    public void setExistencia(Float existencia) {
        this.existencia = existencia;
    }

    public void restarExistencia(Float existencia) {
        this.existencia -= existencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
