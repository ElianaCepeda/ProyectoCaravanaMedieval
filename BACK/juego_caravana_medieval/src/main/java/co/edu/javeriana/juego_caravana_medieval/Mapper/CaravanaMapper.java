package co.edu.javeriana.juego_caravana_medieval.Mapper;

import co.edu.javeriana.juego_caravana_medieval.DTO.CaravanaDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Caravana;

public class CaravanaMapper {
    public static CaravanaDTO toDTO(Caravana caravana) {
        return new CaravanaDTO(
            caravana.getId(),
            caravana.getNombre(),
            caravana.getVelocidad_actual(),
            caravana.getVelocidad_maxima(),
            caravana.getCapacidad_actual(),
            caravana.getCapacidad_maxima(),
            caravana.getDinero(),
            caravana.getVidas(),
            caravana.isGuardias()

        );
    }

    public static Caravana toEntity(CaravanaDTO caravanaDTO) {
        Caravana caravana = new Caravana();
        caravana.setId(caravanaDTO.getId());
        caravana.setNombre(caravanaDTO.getNombre());
        caravana.setVelocidad_actual(caravanaDTO.getVelocidad_actual());
        caravana.setVelocidad_maxima(caravanaDTO.getVelocidad_maxima());
        caravana.setCapacidad_actual(caravanaDTO.getCapacidad_actual());
        caravana.setCapacidad_maxima(caravanaDTO.getCapacidad_maxima());
        caravana.setDinero(caravanaDTO.getDinero());
        caravana.setVidas(caravanaDTO.getVidas());
        caravana.setGuardias(caravanaDTO.isGuardias());

        return caravana;
  
    
}

}

