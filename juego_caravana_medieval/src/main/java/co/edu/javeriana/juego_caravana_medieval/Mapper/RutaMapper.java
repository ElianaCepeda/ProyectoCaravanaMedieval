package co.edu.javeriana.juego_caravana_medieval.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.RutaDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Ruta;
import co.edu.javeriana.juego_caravana_medieval.service.CiudadService;

@Component
public class RutaMapper {

    private static CiudadService ciudadService;

    @Autowired
    public RutaMapper(CiudadService ciudadService) {
        RutaMapper.ciudadService = ciudadService;
    }

    public static RutaDTO toDTO(Ruta ruta) {
        RutaDTO rutaDTO = new RutaDTO();
        rutaDTO.setId(ruta.getId());
        rutaDTO.setCantidad_dano(ruta.getCantidad_dano());
        rutaDTO.setDescripcion_dano(ruta.getDescripcion_dano());
        rutaDTO.setCiudadOrigenId(ruta.getCiudad_origen() != null ? ruta.getCiudad_origen().getId() : null);
        rutaDTO.setCiudadDestinoId(ruta.getCiudad_destino() != null ? ruta.getCiudad_destino().getId() : null);
        return rutaDTO;
    }

    public static Ruta toEntity(RutaDTO rutaDTO) {
        Ruta ruta = new Ruta();
        ruta.setId(rutaDTO.getId());
        ruta.setCantidad_dano(rutaDTO.getCantidad_dano());
        ruta.setDescripcion_dano(rutaDTO.getDescripcion_dano());

        ruta.setCiudad_destino(ciudadService.obtenerCiudadPorId(rutaDTO.getCiudadDestinoId()));
        ruta.setCiudad_origen(ciudadService.obtenerCiudadPorId(rutaDTO.getCiudadOrigenId()));
        
        return ruta;
    }
}