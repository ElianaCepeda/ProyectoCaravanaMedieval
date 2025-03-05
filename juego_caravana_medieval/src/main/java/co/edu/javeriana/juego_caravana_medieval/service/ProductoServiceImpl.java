package co.edu.javeriana.juego_caravana_medieval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;


@Service
public class ProductoServiceImpl implements ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Producto searchById(Long id){
        return productoRepository.findById(id).get();
    }
    @Override
    public List<Producto> searchAll(){
        return productoRepository.findAll();
    }
}