package com.yefer.venta.controllers;

import com.yefer.venta.model.Producto;
import com.yefer.venta.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * contiene la clase controlador  con los metodos y la logica del proyecto
 * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
 *
 * @since [1.0.0]
 *
 */
@Controller
@RequestMapping(path = "/productos")
public class ProductoController {

    /**
     * contiene las inyecciones de los repositorios   @Autowired
     *      @Autowired
     *     private ProductosRepository productosRepository;
     *     ProductosVendidosRepository productosVendidosRepository;
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @Autowired
    private ProductosRepository productosRepository;

    /**
     * Cuando el usuario quiere agregar un producto se invoca al siguiente método del controlador:
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @GetMapping(value = "/agregar")
    public String agregarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/agregar_producto";
    }

    /**
     * Tiene todo lo necesario para mostrar los errores de validación en caso de que existan.
     * Su action es el método del controlador
     * * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @PostMapping(value = "/agregar")
    public String guardarProducto(@ModelAttribute @Valid Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "productos/agregar_producto";
        }
        if (productosRepository.findFirstByCodigo(producto.getCodigo()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        productosRepository.save(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/agregar";
    }

    /**
     * Tiene todo lo necesario para mostrar los errores de validación en caso de que existan.
     * Su action es el método del controlador
     * * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @GetMapping(value = "/mostrar")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productosRepository.findAll());
        return "productos/ver_productos";
    }

    /**
     * Para mostrar productos se visita la URL /productos/mostrar que invoca al siguiente método
     * Su action es el método del controlador
     * * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("producto", productosRepository.findById(id).orElse(null));
        return "productos/editar_producto";
    }


    /**
     *  Se colocó el parámetro ID para eso de los errores, ya sé el id se puede recuperar
     *  a través del modelo, pero lo que yo quiero es que se vea la misma URL para regresar la vista con
     *  los errores en lugar de hacer un redirect, ya que si hago un redirect, no se muestran los errores del formulario
     *  y por eso regreso mejor la vista ;)
     * Su action es el método del controlador
     * * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @PostMapping(value = "/editar/{id}")
    public String actualizarProducto(@ModelAttribute @Valid Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (producto.getId() != null) {
                return "productos/editar_producto";
            }
            return "redirect:/productos/mostrar";
        }
        Producto posibleProductoExistente = productosRepository.findFirstByCodigo(producto.getCodigo());

        if (posibleProductoExistente != null && !posibleProductoExistente.getId().equals(producto.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        productosRepository.save(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/mostrar";
    }

    /**
     * tiene el metodo elimiar Producto Por su id
     * Su action es el método del controlador
     * * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @PostMapping(value = "/eliminar")
    public String eliminarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        productosRepository.deleteById(producto.getId());
        return "redirect:/productos/mostrar";
    }
}
