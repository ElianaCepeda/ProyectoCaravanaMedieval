package co.edu.javeriana.juego_caravana_medieval.service;

import java.util.List;
import co.edu.javeriana.juego_caravana_medieval.model.Producto;

public interface ProductoService {

    public Producto searchById(Long id);

    public List<Producto> searchAll();
    
}