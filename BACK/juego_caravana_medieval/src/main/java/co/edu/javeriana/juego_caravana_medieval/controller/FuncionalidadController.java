package co.edu.javeriana.juego_caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.juego_caravana_medieval.DTO.ProductoDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.ServicioDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Role;
import co.edu.javeriana.juego_caravana_medieval.service.FuncionalidadService;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:4200")

public class FuncionalidadController {

    @Autowired
    private FuncionalidadService funcionalidadService;

    @Secured({Role.Code.CARAVANERO})
    @GetMapping("/servicios")
    public List<ServicioDTO> getServicios() {
        return funcionalidadService.getServicios();
    }

    @Secured({Role.Code.CARAVANERO, Role.Code.COMERCIANTE})
    @GetMapping("/productos")
    public List<ProductoDTO> getProductos(){
        return funcionalidadService.getProductos();
    }


    
}
