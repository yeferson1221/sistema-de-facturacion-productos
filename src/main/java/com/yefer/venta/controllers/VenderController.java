package com.yefer.venta.controllers;

import com.yefer.venta.model.Producto;
import com.yefer.venta.model.ProductoVendido;
import com.yefer.venta.model.Venta;
import com.yefer.venta.repository.ProductosRepository;
import com.yefer.venta.repository.ProductosVendidosRepository;
import com.yefer.venta.repository.VentasRepository;
import com.yefer.venta.service.ProductoParaVender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * contiene la clase controlador  con los metodos y la logica del proyecto
 * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
 *
 * @since [1.0.0]
 *
 */
@Controller
@RequestMapping(path = "/vender")
public class VenderController {

    /**
     * contiene las inyecciones de los repositorios   @Autowired
     *     @Autowired
     *     VentasRepository ventasRepository;
     *     @Autowired
     *     ProductosRepository productosRepository;
     *     @Autowired
     *     ProductosVendidosRepository productosVendidosRepository;
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @Autowired
    VentasRepository ventasRepository;
    @Autowired
    ProductosRepository productosRepository;
    @Autowired
    ProductosVendidosRepository productosVendidosRepository;

    /**
     * obtenerCarrito el metodo nos permite optener el carrto con la compras
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
        ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }

    /**
     * obtenerCarrito guargar el carrito este no tiene persistencia en la BD
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request) {
        request.getSession().setAttribute("carrito", carrito);
    }

    /**
     * contiene un optener lsiat de ventas
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @GetMapping(value = "/")
    public String interfazVender(Model model, HttpServletRequest request) {
        model.addAttribute("producto", new Producto());
        float total = 0;
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        for (ProductoParaVender p: carrito) total += p.getTotal();
        model.addAttribute("total", total);
        return "vender/vender";
    }

    /**
     * nospermite ahora si metdodo POST guardar BD si no es cancelada la venta
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @PostMapping(value = "/agregar")
    public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        Producto productoBuscadoPorCodigo = productosRepository.findFirstByCodigo(producto.getCodigo());
        if (productoBuscadoPorCodigo == null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto con el código " + producto.getCodigo() + " no existe")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/vender/";
        }
        if (productoBuscadoPorCodigo.sinExistencia()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto está agotado")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/vender/";
        }
        boolean encontrado = false;
        for (ProductoParaVender productoParaVenderActual : carrito) {
            if (productoParaVenderActual.getCodigo().equals(productoBuscadoPorCodigo.getCodigo())) {
                productoParaVenderActual.aumentarCantidad();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            carrito.add(new ProductoParaVender(productoBuscadoPorCodigo.getNombre(), productoBuscadoPorCodigo.getCodigo(), productoBuscadoPorCodigo.getPrecio(), productoBuscadoPorCodigo.getExistencia(), productoBuscadoPorCodigo.getId(), 1f));
        }
        this.guardarCarrito(carrito, request);
        return "redirect:/vender/";
    }


    /**
     * nospermite eliminar del carrito  alguna venta
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @PostMapping(value = "/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
        return "redirect:/vender/";
    }


    private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }

    /**
     * nospermite optener la venta para cancelarla
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @GetMapping(value = "/limpiar")
    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta cancelada")
                .addFlashAttribute("clase", "info");
        return "redirect:/vender/";
    }

    /**
     * nospermiteregistrar la venta si a sido exitosa 
     *
     * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
     *
     * @since [1.0.0]
     *
     */
    @PostMapping(value = "/terminar")
    public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        // Si no hay carrito o está vacío, regresamos inmediatamente
        if (carrito == null || carrito.size() <= 0) {
            return "redirect:/vender/";
        }
        Venta v = ventasRepository.save(new Venta());
        // Recorrer el carrito
        for (ProductoParaVender productoParaVender : carrito) {
            // Obtener el producto fresco desde la base de datos
            Producto p = productosRepository.findById(productoParaVender.getId()).orElse(null);
            if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
            // Le restamos existencia
            p.restarExistencia(productoParaVender.getCantidad());
            // Lo guardamos con la existencia ya restada
            productosRepository.save(p);
            // Creamos un nuevo producto que será el que se guarda junto con la venta
            ProductoVendido productoVendido = new ProductoVendido(productoParaVender.getCantidad(), productoParaVender.getPrecio(), productoParaVender.getNombre(), productoParaVender.getCodigo(), v);
            // Y lo guardamos
            productosVendidosRepository.save(productoVendido);
        }

        // Al final limpiamos el carrito
        this.limpiarCarrito(request);
        // e indicamos una venta exitosa
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta realizada correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/vender/";
    }
}
