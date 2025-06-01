package co.edu.javeriana.juego_caravana_medieval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.juego_caravana_medieval.model.Caravana;
import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.model.StockCaravana;
import co.edu.javeriana.juego_caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.StockCaravanaRepository;

@Service
public class StockCaravanaService {

    @Autowired
    private StockCaravanaRepository stockCaravanaRepository;

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Elimina un registro de stock por su ID.
     */
    public void eliminarStockCaravana(Long id) {
        stockCaravanaRepository.deleteById(id);
    }

    /**
     * Incrementa la cantidad de un stock existente.
     * @param id      ID del registro de StockCaravana
     * @param cantidad  Cantidad a sumar
     */
    public void comprarStockCaravana(Long id, int cantidad) {
        StockCaravana stock = stockCaravanaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El stock con el ID " + id + " no existe."));
        stock.setCantidad(stock.getCantidad() + cantidad);
        stockCaravanaRepository.save(stock);
    }

    /**
     * Disminuye la cantidad de un stock existente.
     * @param id       ID del registro de StockCaravana
     * @param cantidad Cantidad a restar
     */
    public void venderStockCaravana(Long id, int cantidad) {
        StockCaravana stock = stockCaravanaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El stock con el ID " + id + " no existe."));
        if (stock.getCantidad() < cantidad) {
            throw new IllegalStateException("No hay suficiente stock para vender.");
        }
        stock.setCantidad(stock.getCantidad() - cantidad);
        stockCaravanaRepository.save(stock);
    }

    /**
     * Devuelve la lista de StockCaravana para una caravana dada.
     * @param caravanaId  ID de la Caravana
     * @return Lista de StockCaravana
     */
    public List<StockCaravana> obtenerStockPorCaravana(Long caravanaId) {
        return stockCaravanaRepository.findByCaravanaId(caravanaId);
    }

    /**
     * Crea una nueva entrada de stock para la caravana y producto especificados.
     * @param caravanaId  ID de la Caravana
     * @param productoId  ID del Producto
     * @param cantidad    Cantidad inicial
     */
    public void crearStockCaravana(Long caravanaId, Long productoId, int cantidad) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new IllegalArgumentException("La caravana con ID " + caravanaId + " no existe."));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("El producto con ID " + productoId + " no existe."));

        StockCaravana nuevoStock = new StockCaravana();
        nuevoStock.setCaravana(caravana);
        nuevoStock.setProducto(producto);
        nuevoStock.setCantidad(cantidad);

        stockCaravanaRepository.save(nuevoStock);
    }
}
