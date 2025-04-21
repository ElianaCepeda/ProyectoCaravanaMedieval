package co.edu.javeriana.juego_caravana_medieval.Mapper;

import co.edu.javeriana.juego_caravana_medieval.DTO.ServicioDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Servicio;

public class ServicioMapper {

    public static ServicioDTO toDTO(Servicio servicio) {
        ServicioDTO servicioDTO = new ServicioDTO();
        servicioDTO.setId(servicio.getId());
        servicioDTO.setNombre(servicio.getNombre());
        servicioDTO.setPrecio(servicio.getPrecio());
        servicioDTO.setDescripción(servicio.getDescripción());
        return servicioDTO;
    }

    public static Servicio toEntity(ServicioDTO servicioDTO) {
        Servicio servicio = new Servicio();
        servicio.setId(servicioDTO.getId());
        servicio.setNombre(servicioDTO.getNombre());
        servicio.setPrecio(servicioDTO.getPrecio());
        servicio.setDescripción(servicioDTO.getDescripción());
        return servicio;
    }

    
}
