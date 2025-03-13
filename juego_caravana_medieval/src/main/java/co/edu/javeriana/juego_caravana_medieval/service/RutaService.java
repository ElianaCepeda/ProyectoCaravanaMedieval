package co.edu.javeriana.juego_caravana_medieval.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.RutaDTO;
import co.edu.javeriana.juego_caravana_medieval.Mapper.CiudadMapper;
import co.edu.javeriana.juego_caravana_medieval.Mapper.RutaMapper;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.model.Ruta;
import co.edu.javeriana.juego_caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;

@Service
public class RutaService {
    
    @Autowired
    RutaRepository rutaRepository;


    @Autowired
    private CiudadService ciudadService;
    
    public Optional<RutaDTO> searchById(Long id){
        return rutaRepository.findById(id).map(RutaMapper::toDTO);
    }
    

    public List<RutaDTO> searchAll() {
        return rutaRepository.findAll().stream()
                .map(ruta -> {
                    RutaDTO dto = RutaMapper.toDTO(ruta);
                    dto.setCiudadOrigenNombre(ciudadService.searchById(dto.getCiudadOrigenId()).orElseThrow().getNombre());
                    dto.setCiudadDestinoNombre(ciudadService.searchById(dto.getCiudadDestinoId()).orElseThrow().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public void save (RutaDTO rutaDTO) {
        Ruta ruta = RutaMapper.toEntity(rutaDTO);
        rutaRepository.save(ruta);
    }

    public void borrarRuta(Long id) {
        rutaRepository.deleteById(id);
    }

    public List<RutaDTO> searchByCiudadId(Long ciudadId) {
    return rutaRepository.findById(ciudadId).stream()
            .map(RutaMapper::toDTO)
            .collect(Collectors.toList());
}

    


}
