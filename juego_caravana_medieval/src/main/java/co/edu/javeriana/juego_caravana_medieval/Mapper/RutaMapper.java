package co.edu.javeriana.juego_caravana_medieval.Mapper;

import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.RutaDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.model.Ruta;

public class RutaMapper {
      public static RutaDTO toDTO(Ruta ruta) {
        
        RutaDTO rutaDTO = new RutaDTO();
        rutaDTO.setId(ruta.getId());
        rutaDTO.setCantidad_dano(ruta.getCantidad_dano());
        rutaDTO.setDescripcion_dano(ruta.getDescripcion_dano());

        return rutaDTO;
    }

    public static Ruta toEntity(RutaDTO rutaDTO) {
        Ruta ruta = new Ruta();
        ruta.setId(rutaDTO.getId());
        ruta.setCantidad_dano(rutaDTO.getCantidad_dano());
        ruta.setDescripcion_dano(rutaDTO.getDescripcion_dano());

        return ruta;
      
    }
}
