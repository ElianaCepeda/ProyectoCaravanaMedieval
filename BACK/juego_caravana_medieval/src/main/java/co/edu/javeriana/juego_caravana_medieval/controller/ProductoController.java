package co.edu.javeriana.juego_caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.juego_caravana_medieval.DTO.ProductoDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.model.Role;
import co.edu.javeriana.juego_caravana_medieval.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Secured({Role.Code.CARAVANERO, Role.Code.COMERCIANTE})
    @GetMapping("/list")
    public List<ProductoDTO> mostrarProductos(){
        return productoService.mostrarProductos();
    }
    
   

    
    
}
