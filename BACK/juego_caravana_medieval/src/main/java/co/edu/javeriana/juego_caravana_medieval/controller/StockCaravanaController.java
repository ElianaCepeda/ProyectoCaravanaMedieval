package co.edu.javeriana.juego_caravana_medieval.controller;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.juego_caravana_medieval.service.StockCaravanaService;

public class StockCaravanaController {

    @Autowired
    private StockCaravanaService stockCaravanaService;

    

    public void venderStockCaravana(Long id, int cantidad) {
        stockCaravanaService.venderStockCaravana(id, cantidad);
    }

    public void comprarStockCaravana(Long id, int cantidad) {
        stockCaravanaService.comprarStockCaravana(id, cantidad);
    }
    
    
}
