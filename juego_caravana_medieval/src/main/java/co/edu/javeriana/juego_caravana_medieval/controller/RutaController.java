package co.edu.javeriana.juego_caravana_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.RutaDTO;
import co.edu.javeriana.juego_caravana_medieval.service.CiudadService;
import co.edu.javeriana.juego_caravana_medieval.service.RutaService;

@Controller
@RequestMapping("/ruta")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    @Autowired
    private CiudadService ciudadService;
    
    @GetMapping("/list")
    public ModelAndView mostrarCiudades() {
        List<RutaDTO> rutas = rutaService.searchAll();
        ModelAndView mv = new ModelAndView("ruta-list");
        mv.addObject("listaRutas", rutas);
        return mv;
    }

    @GetMapping("/view/{idRuta}")
    public ModelAndView buscarCiudad(@PathVariable("idRuta") Long idRuta) {
        RutaDTO ruta = rutaService.searchById(idRuta).orElseThrow();
        ModelAndView mv = new ModelAndView("ruta-view");
        mv.addObject("ruta", ruta);

        String ciudadOrigen = ciudadService.searchById(ruta.getCiudadOrigenId()).orElseThrow().getNombre();
        mv.addObject("ciudadOrigen", ciudadOrigen);

        String ciudadDestino = ciudadService.searchById(ruta.getCiudadDestinoId()).orElseThrow().getNombre();
        mv.addObject("ciudadDestino", ciudadDestino);

        return mv;
    }

    @GetMapping("/create")
    public ModelAndView crearRuta() {
        ModelAndView mv = new ModelAndView("ruta-edit");
        mv.addObject("ruta", new RutaDTO());
        List<CiudadDTO> ciudades = ciudadService.searchAll();
        mv.addObject("ciudades", ciudades);
        return mv;
    }

    @GetMapping("/edit/{id}")
public String editRutaForm(@PathVariable("id") Long id, Model model) {
    RutaDTO ruta = rutaService.searchById(id).orElseThrow();
    List<CiudadDTO> ciudades = ciudadService.searchAll();
    model.addAttribute("ruta", ruta);
    model.addAttribute("ciudades", ciudades);
    return "ruta-edit";
}

    @PostMapping("/save")
    public RedirectView guardarCiudad(@ModelAttribute RutaDTO rutaDTO){
        rutaService.save(rutaDTO);
        return new RedirectView("/ruta/list");
    }

    @GetMapping("delete/{idRuta}")
    public RedirectView eliminarCiudad(@PathVariable Long idRuta){
        rutaService.borrarRuta(idRuta);
        return new RedirectView("/ruta/list");
    }
}
