package co.edu.javeriana.juego_caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.service.ProductoService;

    
@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/list")
    public ModelAndView mostrarProductos() {
        List<Producto> products = productoService.searchAll();
        ModelAndView modelAndView = new ModelAndView("producto-list");
        modelAndView.addObject("listaProductos", products);
        return modelAndView;
    }
    @GetMapping("/view/{idProducto}")
    public ModelAndView buscarPersona(@PathVariable("idProducto") Long id) {
        Producto product = productoService.searchById(id);
        ModelAndView modelAndView = new ModelAndView("producto-view");
        modelAndView.addObject("producto", product);
        return modelAndView;
    }
}
