package co.edu.javeriana.juego_caravana_medieval.controller;

import co.edu.javeriana.juego_caravana_medieval.DTO.StockCaravanaDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Caravana;
import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.model.StockCaravana;
import co.edu.javeriana.juego_caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.StockCaravanaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("integration-testing")
public class StockCaravanaControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8081/stockcaravana";

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private StockCaravanaRepository stockCaravanaRepository;

    @Autowired
    private WebTestClient webTestClient;

    private Caravana caravana;
    private Producto producto;
    private StockCaravana stock;

    @BeforeEach
    void setup() {
        caravana = caravanaRepository.save(new Caravana("Caravana Test", 10, 20, 1000, 3, true));
        producto = productoRepository.save(new Producto("Espada", 200));
        stock = new StockCaravana();
        stock.setCaravana(caravana);
        stock.setProducto(producto);
        stock.setCantidad(10);
        stock = stockCaravanaRepository.save(stock);
    }

    @Test
    void testObtenerStockCaravana() {
        webTestClient.get()
                .uri(BASE_URL + "/caravana/" + caravana.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(StockCaravanaDTO.class)
                .hasSize(1);
    }

    @Test
    void testComprarStock() {
        webTestClient.post()
                .uri(BASE_URL + "/comprar/" + stock.getId() + "/5")
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri(BASE_URL + "/caravana/" + caravana.getId())
                .exchange()
                .expectBodyList(StockCaravanaDTO.class)
                .value(lista -> assertEquals(15, lista.get(0).getCantidad()));
    }

    @Test
    void testVenderStock() {
        webTestClient.post()
                .uri(BASE_URL + "/vender/" + stock.getId() + "/4")
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri(BASE_URL + "/caravana/" + caravana.getId())
                .exchange()
                .expectBodyList(StockCaravanaDTO.class)
                .value(lista -> assertEquals(6, lista.get(0).getCantidad()));
    }

    @Test
    void testActualizarCantidadStock() {
        Map<String, Integer> body = new HashMap<>();
        body.put("cantidad", 50);

        webTestClient.put()
                .uri(BASE_URL + "/" + stock.getId())
                .bodyValue(body)
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri(BASE_URL + "/caravana/" + caravana.getId())
                .exchange()
                .expectBodyList(StockCaravanaDTO.class)
                .value(lista -> assertEquals(50, lista.get(0).getCantidad()));
    }

    @Test
    void testEliminarStockCaravana() {
        webTestClient.delete()
                .uri(BASE_URL + "/" + stock.getId())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri(BASE_URL + "/caravana/" + caravana.getId())
                .exchange()
                .expectBodyList(StockCaravanaDTO.class)
                .hasSize(0);
    }

    @Test
    void testCrearStockCaravana() {
        StockCaravanaDTO nuevoStock = new StockCaravanaDTO();
        nuevoStock.setCaravanaId(caravana.getId());
        nuevoStock.setProductoId(producto.getId());
        nuevoStock.setCantidad(12);

        webTestClient.post()
                .uri(BASE_URL + "/crear")
                .bodyValue(nuevoStock)
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri(BASE_URL + "/caravana/" + caravana.getId())
                .exchange()
                .expectBodyList(StockCaravanaDTO.class)
                .value(lista -> assertTrue(lista.stream().anyMatch(s -> s.getCantidad() == 12)));
    }
}