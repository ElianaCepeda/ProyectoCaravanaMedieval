
package co.edu.javeriana.juego_caravana_medieval.controller;

import co.edu.javeriana.juego_caravana_medieval.DTO.ProductoDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.ServicioDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.model.Servicio;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.ServicioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("integration-testing")
public class FuncionalidadControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8081/game";

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        productoRepository.save(new Producto("Espada", 200));
        productoRepository.save(new Producto("Manzana", 50));

        servicioRepository.save(new Servicio("Curación", 100, "Recupera salud"));
        servicioRepository.save(new Servicio("Protección", 150, "Aumenta defensa"));
    }

    @Test
    void testObtenerProductos() {
        webTestClient.get()
                .uri(BASE_URL + "/productos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProductoDTO.class)
                .hasSize(2);
    }

    @Test
    void testObtenerServicios() {
        webTestClient.get()
                .uri(BASE_URL + "/servicios")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ServicioDTO.class)
                .hasSize(2);
    }
} 
