package co.edu.javeriana.juego_caravana_medieval.Mapper;

import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;

public class CiudadMapper {
    public static CiudadDTO toDTO(Ciudad ciudad) {
        
        CiudadDTO ciudadDTO = new CiudadDTO();
        ciudadDTO.setId(ciudad.getId());
        ciudadDTO.setNombre(ciudad.getNombre());
        ciudadDTO.setTarifa(ciudad.getTarifa());

        return ciudadDTO;
    }

    public static Ciudad toEntity(CiudadDTO ciudadDTO) {
        Ciudad ciudad = new Ciudad();
        ciudad.setId(ciudadDTO.getId());
        ciudad.setNombre(ciudadDTO.getNombre());
        ciudad.setTarifa(ciudadDTO.getTarifa());

        return ciudad;
      
    }
}
