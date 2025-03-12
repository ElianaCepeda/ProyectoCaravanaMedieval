package co.edu.javeriana.juego_caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.service.CiudadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/ciudad")
public class CiudadController {
    
    @Autowired
    private CiudadService ciudadService;


    @GetMapping("/list")
    public ModelAndView mostrarCiudades() {
        List<CiudadDTO> ciudades = ciudadService.searchAll();
        ModelAndView mv = new ModelAndView("ciudad-list");
        mv.addObject("listaCiudades", ciudades);
        return mv;
    }

    @GetMapping("/view/{idCiudad}")
    public ModelAndView buscarCiudad(@PathVariable("idCiudad") Long idCiudad){
      
        CiudadDTO ciudad = ciudadService.searchById(idCiudad).orElseThrow();
        ModelAndView mv = new ModelAndView("ciudad-view");
        mv.addObject("ciudad", ciudad);
        return mv;

    }

    @GetMapping("/create")
    public ModelAndView crearCiudad(){
        ModelAndView mv = new ModelAndView("ciudad-edit");
        mv.addObject("ciudad", new CiudadDTO());
        return mv;
    }

    @GetMapping("/edit/{idCiudad}")
    public ModelAndView formularioEditarCiudad(@PathVariable("idCiudad") Long idCiudad) {
    CiudadDTO ciudad = ciudadService.searchById(idCiudad).orElseThrow();
    ModelAndView mv = new ModelAndView("ciudad-edit");
    mv.addObject("ciudad", ciudad); // Ahora sí pasamos la ciudad obtenida
    return mv;
    }

    @PostMapping("/save")
    public RedirectView guardarCiudad(@ModelAttribute CiudadDTO ciudadDTO){
        ciudadService.save(ciudadDTO);
        return new RedirectView("/ciudad/list");
    }

    @GetMapping("delete/{idCiudad}")
    public RedirectView eliminarCiudad(@PathVariable Long idCiudad){
        ciudadService.borrarCiudad(idCiudad);
        return new RedirectView("/ciudad/list");
    }
    
}
