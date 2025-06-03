package co.edu.javeriana.juego_caravana_medieval.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;

@SpringBootTest (webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("integration-testing")
public class ProductoControllerIntegrationTest {
    
    private static final String BASE_URL = "http://localhost:8081/producto";
    
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void init(){
        productoRepository.save(new Producto("Espada", 200));
        productoRepository.save(new Producto("Manzana", 30));
        productoRepository.save(new Producto("Capa de sombras", 200));
    }

    @Test
    void productos(){
        webTestClient.get()
            .uri(BASE_URL + "/list")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Producto.class)
            .hasSize(3);
    }

}
