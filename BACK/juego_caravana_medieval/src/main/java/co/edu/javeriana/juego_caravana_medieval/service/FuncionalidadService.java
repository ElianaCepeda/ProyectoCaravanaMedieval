package co.edu.javeriana.juego_caravana_medieval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.juego_caravana_medieval.DTO.ProductoDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.ServicioDTO;
import co.edu.javeriana.juego_caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.ServicioRepository;


@Service
public class FuncionalidadService {
    
    
    @Autowired
    CiudadRepository ciudadRepository;

    @Autowired
    RutaRepository rutaRepository;

    @Autowired 
    ServicioRepository servicioRepository;

    @Autowired 
    ProductoRepository productoRepository;



    public List<ServicioDTO> getServicios() {
        List<ServicioDTO> servicios = servicioRepository.findAll().stream()
                .map(servicio -> new ServicioDTO(servicio.getId(), servicio.getNombre(), servicio.getPrecio(), servicio.getDescripción()))
                .toList();
        return servicios;
    }

    public List<ProductoDTO> getProductos(){
        List<ProductoDTO> productos = productoRepository.findAll().stream()
                .map(producto -> new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio()))
                .toList();
        return productos;
    }




}
