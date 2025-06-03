package co.edu.javeriana.juego_caravana_medieval.controller;

import co.edu.javeriana.juego_caravana_medieval.DTO.CaravanaDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Caravana;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.model.Ruta;
import co.edu.javeriana.juego_caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("integration-testing")
public class CaravanaControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8081/caravana";

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private WebTestClient webTestClient;

    private Ciudad ciudadInicial;

    @BeforeEach
    void setup() {
        ciudadInicial = ciudadRepository.save(new Ciudad("Ciudad Origen", 100, 10, 10));
        Caravana caravana = new Caravana("Exploradora", 10, 50, 1000, 3, true);
        caravana.setCiudad(ciudadInicial);
        caravanaRepository.save(caravana);
    }

    @Test
    void testListarCaravanas() {
        webTestClient.get()
                .uri(BASE_URL + "/list")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CaravanaDTO.class)
                .hasSize(1);
    }

    @Test
    void testBuscarCaravanaPorId() {
        Caravana caravana = caravanaRepository.findAll().get(0);

        webTestClient.get()
                .uri(BASE_URL + "/" + caravana.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Exploradora")
                .jsonPath("$.dinero").isEqualTo(1000);
    }

    @Test
    void testCrearCaravana() {
        CaravanaDTO nueva = new CaravanaDTO();
        nueva.setNombre("Caravana Nueva");
        nueva.setVelocidad_actual(10);
        nueva.setCapacidad_actual(25);
        nueva.setDinero(800);
        nueva.setVidas(2);
        nueva.setGuardias(true);

        webTestClient.post()
                .uri(BASE_URL)
                .bodyValue(nueva)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Caravana Nueva")
                .jsonPath("$.dinero").isEqualTo(800);
    }

    @Test
    void testActualizarCaravana() {
        Caravana caravana = caravanaRepository.findAll().get(0);

        CaravanaDTO actualizada = new CaravanaDTO();
        actualizada.setNombre("Modificada");
        actualizada.setVelocidad_actual(12);
        actualizada.setCapacidad_actual(60);
        actualizada.setDinero(1200);
        actualizada.setVidas(4);
        actualizada.setGuardias(false);

        webTestClient.put()
                .uri(BASE_URL + "/update/" + caravana.getId())
                .bodyValue(actualizada)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Modificada")
                .jsonPath("$.dinero").isEqualTo(1200);
    }

    @Test
    void testEliminarCaravana() {
        Caravana caravana = caravanaRepository.findAll().get(0);

        webTestClient.delete()
                .uri(BASE_URL + "/" + caravana.getId())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri(BASE_URL + "/" + caravana.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testViajarCaravana() {
        Ciudad destino = ciudadRepository.save(new Ciudad("Ciudad Destino", 50, 20, 20));
        Ruta ruta = rutaRepository.save(new Ruta(ciudadInicial, destino, 0, 50.0f, "Camino de prueba"));

        webTestClient.get()
                .uri(BASE_URL + "/viajar/" + ruta.getId())
                .exchange()
                .expectStatus().isOk();
    }
} 