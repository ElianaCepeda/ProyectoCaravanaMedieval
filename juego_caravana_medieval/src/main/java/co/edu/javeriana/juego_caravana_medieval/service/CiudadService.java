package co.edu.javeriana.juego_caravana_medieval.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadRutasDTO;

import co.edu.javeriana.juego_caravana_medieval.Mapper.CiudadMapper;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.model.Ruta;
import co.edu.javeriana.juego_caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;

@Service
public class CiudadService {

      @Autowired
    CiudadRepository ciudadRepository;

    @Autowired
    RutaRepository rutaRepository;

    
    public Optional<CiudadDTO> searchById(Long id){
        return ciudadRepository.findById(id).map(CiudadMapper::toDTO);
    }
    

    public List<CiudadDTO> searchAll(){
        return ciudadRepository.findAll().stream()
                .map(CiudadMapper::toDTO).toList();
    }

    public void save (CiudadDTO ciudadDTO) {
        Ciudad ciudad = CiudadMapper.toEntity(ciudadDTO);
        ciudadRepository.save(ciudad);
    }

    public void borrarCiudad(Long id) {
        ciudadRepository.deleteById(id);
    }


    public Optional<CiudadRutasDTO> getRutasPorCiudad(Long ciudadId) {
        return ciudadRepository.findById(ciudadId)
            .map(ciudad -> new CiudadRutasDTO(
                ciudad.getId(),
                ciudad.getRutas().stream().map(Ruta::getId).toList()
            ));
    }
    

    public void updateCiudadRutas(CiudadRutasDTO ciudadRutasDTO) {
    Ciudad ciudad = ciudadRepository.findById(ciudadRutasDTO.getIdCiudad()).orElseThrow();
    List<Ruta> selectedRutas = rutaRepository.findAllById(ciudadRutasDTO.getRutasID());

    ciudad.getRutas().clear();
    ciudad.getRutas().addAll(selectedRutas);
    ciudadRepository.save(ciudad);

    }

}
