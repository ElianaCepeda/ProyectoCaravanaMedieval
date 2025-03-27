package co.edu.javeriana.juego_caravana_medieval.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.javeriana.juego_caravana_medieval.DTO.RutaDTO;
import co.edu.javeriana.juego_caravana_medieval.service.RutaService;

@RestController
@RequestMapping("/ruta")
public class RutaController {

    @Autowired
    private RutaService rutaService;

   
    
    @GetMapping("/list")
    public List<RutaDTO> mostrarRutas() {
        return rutaService.searchAll();

    }

    @GetMapping("{idRuta}")
    public RutaDTO buscarRuta(@PathVariable("idRuta") Long idRuta) {
        return rutaService.searchById(idRuta).orElseThrow();
    }

  
    @PostMapping
    public RutaDTO crearRuta(@RequestBody RutaDTO rutaDTO) {
        return rutaService.crearRuta(rutaDTO);
    }

    @PutMapping
    public RutaDTO acutalizarRuta(@RequestBody RutaDTO rutaDTO) {
        return rutaService.acutalizarRuta(rutaDTO);
    }



    @DeleteMapping("{idRuta}")
    public void eliminarCiudad(@PathVariable Long idRuta){
        rutaService.borrarRuta(idRuta);
    }
}
