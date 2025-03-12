package co.edu.javeriana.juego_caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadRutasDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.RutaDTO;
import co.edu.javeriana.juego_caravana_medieval.service.CiudadService;
import co.edu.javeriana.juego_caravana_medieval.service.RutaService;

@Controller
@RequestMapping("/ciudad/rutas")
public class CiudadRutasController {
    @Autowired
    private RutaService rutaService;

    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/edit/{idCiudad}")
    public ModelAndView editCiudadRutasForm(@PathVariable("idCiudad") Long idCiudad) {
        CiudadRutasDTO ciudadRutasDTO = ciudadService.getRutasPorCiudad(idCiudad).orElseThrow();
        List<RutaDTO> rutas = rutaService.searchAll();
        
        ModelAndView mv = new ModelAndView("ciudad-rutas-edit");
        mv.addObject("ciudadRutas", ciudadRutasDTO);
        mv.addObject("rutas", rutas);
        return mv;
    }

    @PostMapping("/save")
    public RedirectView saveCiudadRutas(@ModelAttribute CiudadRutasDTO ciudadRutasDTO) {
        ciudadService.updateCiudadRutas(ciudadRutasDTO);
        return new RedirectView("/ciudad/list");
    }
    
    
}
