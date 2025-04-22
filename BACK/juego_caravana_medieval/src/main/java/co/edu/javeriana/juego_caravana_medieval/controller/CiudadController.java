package co.edu.javeriana.juego_caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.service.CiudadService;

@RestController
@RequestMapping("/ciudad")
@CrossOrigin(origins = "http://localhost:4200")
public class CiudadController {
    
    @Autowired
    private CiudadService ciudadService;

    // Obtener todas las ciudades
    @GetMapping("/list")
    public List<CiudadDTO> mostrarCiudades() {
        return ciudadService.searchAll();
    }

    // Buscar una ciudad por su ID
    @GetMapping("{idCiudad}")
    public CiudadDTO buscarCiudad(@PathVariable("idCiudad") Long idCiudad){ 
        return ciudadService.searchById(idCiudad).orElseThrow();
    }

    // Crear una ciudad
    @PostMapping
    public CiudadDTO crearCiudad(@RequestBody CiudadDTO ciudadDTO){
        return ciudadService.crearCiudad(ciudadDTO);
    }

    // Actualizar una ciudad
    @PutMapping
    public CiudadDTO actualizarCiudad(@RequestBody CiudadDTO ciudadDTO){
        return ciudadService.actualizarCiudad(ciudadDTO);
    }

    // Eliminar ciudad
    @DeleteMapping("{idCiudad}")
    public void eliminarCiudad(@PathVariable Long idCiudad){
        ciudadService.borrarCiudad(idCiudad);
    }

    // 🔁 NUEVO: Actualizar solo coordenadas X e Y
    @PutMapping("/coords/{id}")
    public ResponseEntity<?> actualizarCoordenadas(
        @PathVariable Long id,
        @RequestBody CiudadDTO nuevaCoord
    ) {
        return ciudadService.actualizarCoordenadas(id, nuevaCoord);
    }
}
