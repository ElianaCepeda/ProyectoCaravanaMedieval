package co.edu.javeriana.juego_caravana_medieval.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.juego_caravana_medieval.DTO.ProductoDTO;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;
@Service
public class ProductoService {

    @Autowired 
    private ProductoRepository productoRepository;

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public Optional<ProductoDTO> mostrarProducto(Long id) {
        return productoRepository.findById(id).map(producto -> new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio()));
    }
    public List<ProductoDTO> mostrarProductos() {
        return productoRepository.findAll().stream()
                .map(producto -> new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio()))
                .toList();
    }


    
    
}
