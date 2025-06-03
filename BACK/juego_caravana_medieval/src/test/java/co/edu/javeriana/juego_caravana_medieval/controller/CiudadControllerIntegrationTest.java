package co.edu.javeriana.juego_caravana_medieval.controller;

import co.edu.javeriana.juego_caravana_medieval.DTO.CiudadDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.repository.CiudadRepository;
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
public class CiudadControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8081/ciudad";

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        ciudadRepository.save(new Ciudad("Bogotá", 150, 10, 20));
        ciudadRepository.save(new Ciudad("Medellín", 130, 30, 40));
    }

    @Test
    void testListarCiudades() {
        webTestClient.get()
                .uri(BASE_URL + "/list")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CiudadDTO.class)
                .hasSize(2);
    }

    @Test
    void testBuscarCiudadPorId() {
        Ciudad ciudad = ciudadRepository.save(new Ciudad("Cali", 120, 50, 60));
        webTestClient.get()
                .uri(BASE_URL + "/" + ciudad.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Cali")
                .jsonPath("$.tarifa").isEqualTo(120)
                .jsonPath("$.x").isEqualTo(50)
                .jsonPath("$.y").isEqualTo(60);
    }

    @Test
    void testCrearCiudad() {
        CiudadDTO nuevaCiudad = new CiudadDTO(null, "Cartagena", 100, 70, 80);

        webTestClient.post()
                .uri(BASE_URL)
                .bodyValue(nuevaCiudad)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Cartagena")
                .jsonPath("$.tarifa").isEqualTo(100);
    }

    @Test
    void testActualizarCiudad() {
        Ciudad ciudad = ciudadRepository.save(new Ciudad("Santa Marta", 90, 5, 5));
        CiudadDTO actualizada = new CiudadDTO(ciudad.getId(), "Santa Marta Modificada", 95, 15, 25);

        webTestClient.put()
                .uri(BASE_URL)
                .bodyValue(actualizada)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Santa Marta Modificada")
                .jsonPath("$.tarifa").isEqualTo(95)
                .jsonPath("$.x").isEqualTo(15)
                .jsonPath("$.y").isEqualTo(25);
    }

    @Test
    void testEliminarCiudad() {
        Ciudad ciudad = ciudadRepository.save(new Ciudad("Tunja", 85, 0, 0));

        webTestClient.delete()
                .uri(BASE_URL + "/" + ciudad.getId())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri(BASE_URL + "/" + ciudad.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testActualizarCoordenadas() {
        Ciudad ciudad = ciudadRepository.save(new Ciudad("Neiva", 110, 0, 0));
        CiudadDTO nuevaCoord = new CiudadDTO();
        nuevaCoord.setX(33);
        nuevaCoord.setY(44);

        webTestClient.put()
                .uri(BASE_URL + "/coords/" + ciudad.getId())
                .bodyValue(nuevaCoord)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Coordenadas actualizadas correctamente");
    }
}
