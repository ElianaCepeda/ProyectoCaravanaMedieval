package co.edu.javeriana.juego_caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.juego_caravana_medieval.DTO.CaravanaDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.service.CaravanaService;

@RestController
@RequestMapping("/caravana")
@CrossOrigin(origins = "http://localhost:4200")
public class CaravanaController {

    @Autowired
    private CaravanaService caravanaService;

    @GetMapping("/list")
    public List<CaravanaDTO> mostrarCaravanas() {
        return caravanaService.searchAll();
    }

    @GetMapping("/{idCaravana}")
    public CaravanaDTO buscarCaravana(@PathVariable("idCaravana") Long idCaravana) {
        return caravanaService.searchById(idCaravana).orElseThrow();
    }

    @GetMapping("/viajar/{rutaId}")
    public void viajarCaravana(@PathVariable("rutaId") Long rutaId) {
        caravanaService.viajarCaravana(rutaId);
    }
    
    @PostMapping
    public CaravanaDTO crearCaravana(@RequestBody CaravanaDTO caravanaDTO) {
        return caravanaService.crearCaravana(caravanaDTO);
    }

    @PutMapping
    public CaravanaDTO actualizarCaravana(@RequestBody CaravanaDTO caravanaDTO) {
        return caravanaService.actualizarCaravana(caravanaDTO);
    }

    @DeleteMapping("{idCaravana}")
    public void eliminarCaravana(@PathVariable Long idCaravana) {
        caravanaService.borrarCaravana(idCaravana);
    }
}
