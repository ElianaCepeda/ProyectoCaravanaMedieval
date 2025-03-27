package co.edu.javeriana.juego_caravana_medieval.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.service.CiudadService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/ciudad")
public class CiudadController {
    
    @Autowired
    private CiudadService ciudadService;


    @GetMapping("/list")
    public List<CiudadDTO> mostrarCiudades() {
        return ciudadService.searchAll();
    }

    @GetMapping("{idCiudad}")
    public CiudadDTO buscarCiudad(@PathVariable("idCiudad") Long idCiudad){ 
        return ciudadService.searchById(idCiudad).orElseThrow();
    }

   

    @PostMapping
    public CiudadDTO crearCiudad(@RequestBody CiudadDTO ciudadDTO){
        return ciudadService.crearCiudad(ciudadDTO);
    }

    @PutMapping
    public CiudadDTO actualizarCiudad(@RequestBody CiudadDTO ciudadDTO){
        return ciudadService.actualizarCiudad(ciudadDTO);
    }

    @DeleteMapping("{idCiudad}")
    public void eliminarCiudad(@PathVariable Long idCiudad){
        ciudadService.borrarCiudad(idCiudad);
    }
    
}
