package co.edu.javeriana.juego_caravana_medieval.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.javeriana.juego_caravana_medieval.DTO.StockCaravanaDTO;
import co.edu.javeriana.juego_caravana_medieval.Mapper.StockCaravanaMapper;
import co.edu.javeriana.juego_caravana_medieval.model.StockCaravana;
import co.edu.javeriana.juego_caravana_medieval.service.StockCaravanaService;

@RestController
@RequestMapping("/stockcaravana")
public class StockCaravanaController {

    @Autowired
    private StockCaravanaService stockCaravanaService;

    /**
     * Obtener todo el stock de una caravana determinada.
     * GET http://localhost:8081/stockcaravana/caravana/{caravanaId}
     */
    @GetMapping("/caravana/{caravanaId}")
    public List<StockCaravanaDTO> obtenerStockCaravana(@PathVariable Long caravanaId) {
        List<StockCaravana> stockEntidades = stockCaravanaService.obtenerStockPorCaravana(caravanaId);
        return stockEntidades.stream()
                .map(StockCaravanaMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Método para comprar (aumentar) stock en la caravana.
     * POST http://localhost:8081/stockcaravana/comprar/{id}/{cantidad}
     */
    @PostMapping("/comprar/{id}/{cantidad}")
    public void comprarStockCaravana(@PathVariable Long id, @PathVariable int cantidad) {
        stockCaravanaService.comprarStockCaravana(id, cantidad);
    }

    /**
     * Método para vender (disminuir) stock en la caravana.
     * POST http://localhost:8081/stockcaravana/vender/{id}/{cantidad}
     */
    @PostMapping("/vender/{id}/{cantidad}")
    public void venderStockCaravana(@PathVariable Long id, @PathVariable int cantidad) {
        stockCaravanaService.venderStockCaravana(id, cantidad);
    }

    @PostMapping("/crear")
    public void crearStockCaravana(@RequestBody StockCaravanaDTO dto) {
        stockCaravanaService.crearStockCaravana(dto.getCaravanaId(), dto.getProductoId(), dto.getCantidad());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCantidadStockCaravana(@PathVariable Long id,
            @RequestBody Map<String, Integer> body) {
        Integer cantidad = body.get("cantidad");
        stockCaravanaService.actualizarCantidadStockCaravana(id, cantidad);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
public void eliminarStockCaravana(@PathVariable Long id) {
    stockCaravanaService.eliminarStockCaravana(id);
}
}
