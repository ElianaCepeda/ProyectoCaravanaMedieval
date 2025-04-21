package co.edu.javeriana.juego_caravana_medieval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.juego_caravana_medieval.model.StockCaravana;
import co.edu.javeriana.juego_caravana_medieval.repository.StockCaravanaRepository;
@Service
public class StockCaravanaService {

    @Autowired
    private StockCaravanaRepository stockCaravanaRepository;

    public void eliminarStockCaravana(Long id) {
        stockCaravanaRepository.deleteById(id);
    }

public void comprarStockCaravana(Long id, int cantidad) {
    StockCaravana stock = stockCaravanaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("El stock con el ID " + id + " no existe."));
    stock.setCantidad(stock.getCantidad() + cantidad); // Incrementar el stock
    stockCaravanaRepository.save(stock); // Guardar los cambios
}

public void venderStockCaravana(Long id, int cantidad) {
    StockCaravana stock = stockCaravanaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("El stock con el ID " + id + " no existe."));
    if (stock.getCantidad() < cantidad) {
        throw new IllegalStateException("No hay suficiente stock para vender.");
    }
    stock.setCantidad(stock.getCantidad() - cantidad); // Reducir el stock
    stockCaravanaRepository.save(stock); // Guardar los cambios
}
    


    
}
