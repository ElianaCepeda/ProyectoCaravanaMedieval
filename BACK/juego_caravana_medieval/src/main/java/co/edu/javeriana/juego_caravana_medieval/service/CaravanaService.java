package co.edu.javeriana.juego_caravana_medieval.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.juego_caravana_medieval.DTO.CaravanaDTO;
import co.edu.javeriana.juego_caravana_medieval.Mapper.CaravanaMapper;
import co.edu.javeriana.juego_caravana_medieval.model.Caravana;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.model.Ruta;
import co.edu.javeriana.juego_caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;

@Service
public class CaravanaService {
    
    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;


    public Optional<CaravanaDTO> searchById(Long id) {
        return caravanaRepository.findById(id).map(CaravanaMapper::toDTO);
    }

    public List<CaravanaDTO> searchAll() {
        return caravanaRepository.findAll().stream()
                .map(CaravanaMapper::toDTO).toList();
    }

    public CaravanaDTO crearCaravana(CaravanaDTO caravanaDTO) {
        caravanaDTO.setId(null);
        return CaravanaMapper.toDTO(caravanaRepository.save(CaravanaMapper.toEntity(caravanaDTO)));
    }

    public void actualizarCaravana(CaravanaDTO caravanaDTO, Long idCaravana) {

        Caravana caravana = caravanaRepository.findById(idCaravana)
                .orElseThrow(() -> new IllegalArgumentException("Caravana no encontrada"));
        
        // Actualizar los campos de la caravana
        caravana.setNombre(caravanaDTO.getNombre());
        caravana.setDinero(caravanaDTO.getDinero());
        caravana.setVelocidad_actual(caravanaDTO.getVelocidad_actual());
        caravana.setVidas(caravanaDTO.getVidas());
        caravana.setCapacidad_actual(caravanaDTO.getCapacidad_actual());
        caravana.setGuardias(caravanaDTO.isGuardias());
       
        // Guardar los cambios en la base de datos
        caravanaRepository.save(caravana);

  
      
      

    }

    public void borrarCaravana(Long id) {
        caravanaRepository.deleteById(id);
    }

    public void viajarCaravana(Long rutaId) {
        // Obtener la última caravana (la de mayor ID)
        Caravana caravana = caravanaRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("No hay caravanas disponibles"));
    
        // Obtener la velocidad de la caravana
        double velocidadCaravana = caravana.getVelocidad_actual();
    
        // Obtener la ruta y calcular tarifa y distancia
        Ruta ruta = rutaRepository.findById(rutaId)
                .orElseThrow(() -> new IllegalArgumentException("Ruta no encontrada"));
        int tarifa = ruta.getCiudad_destino().getTarifa();
        double distancia = ruta.getDistancia();
    
        // Pagar tarifa automáticamente
        if (caravana.getDinero() < tarifa) {
            throw new IllegalStateException("La caravana no tiene suficiente oro para pagar la tarifa.");
        }
        caravana.setDinero(caravana.getDinero() - tarifa);
    
        // Calcular el tiempo de viaje (t = d / v)
        double tiempoDeViaje = distancia / velocidadCaravana;
    
        // Actualizar la posición de la caravana
        Ciudad ciudadDestino = ruta.getCiudad_destino();
        caravana.setCiudad(ciudadDestino);
    
        // Guardar los cambios en la caravana
        caravanaRepository.save(caravana);
    
        // Mostrar información del tiempo de viaje
        System.out.println("Tiempo de viaje calculado: " + tiempoDeViaje + " horas");
    }


    
}
