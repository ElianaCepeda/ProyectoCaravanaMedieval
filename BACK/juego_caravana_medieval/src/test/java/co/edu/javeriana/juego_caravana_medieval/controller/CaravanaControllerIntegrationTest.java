package co.edu.javeriana.juego_caravana_medieval.controller;

import co.edu.javeriana.juego_caravana_medieval.DTO.CaravanaDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.JwtAuthenticationResponse;
import co.edu.javeriana.juego_caravana_medieval.DTO.LoginDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Caravana;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.model.Role;
import co.edu.javeriana.juego_caravana_medieval.model.Ruta;
import co.edu.javeriana.juego_caravana_medieval.model.User;
import co.edu.javeriana.juego_caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("integration-testing")
public class CaravanaControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8081/caravana";

    @Autowired private CaravanaRepository caravanaRepository;
    @Autowired private CiudadRepository ciudadRepository;
    @Autowired private RutaRepository rutaRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private WebTestClient webTestClient;
    @Autowired private TestRestTemplate rest;

    private Ciudad ciudadInicial;

    @BeforeEach
    void setup() {
        ciudadInicial = ciudadRepository.save(new Ciudad("Ciudad Origen", 100, 10, 10));
        Caravana caravana = new Caravana("Exploradora", 10, 50, 1000, 3, true);
        caravana.setCiudad(ciudadInicial);
        caravanaRepository.save(caravana);

        userRepository.save(new User("Alice", "Alisson", "alice@alice.com", passwordEncoder.encode("alice123"), Role.COMERCIANTE));
        userRepository.save(new User("Bob", "Bobson", "bob@bob.com", passwordEncoder.encode("bob123"), Role.CARAVANERO));
    }

    private JwtAuthenticationResponse login(String email, String password) {
        RequestEntity<LoginDTO> request = RequestEntity.post("http://localhost:8081/auth/login")
            .body(new LoginDTO(email, password));
        ResponseEntity<JwtAuthenticationResponse> jwtResponse = rest.exchange(request, JwtAuthenticationResponse.class);
        JwtAuthenticationResponse body = jwtResponse.getBody();
        assertNotNull(body);
        return body;
    }

    @Test
    void testListarCaravanas() {
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");
        webTestClient.get()
            .uri(BASE_URL + "/list")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(CaravanaDTO.class)
            .hasSize(1);
    }

    @Test
    void testBuscarCaravanaPorId() {
        Caravana caravana = caravanaRepository.findAll().get(0);
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");
        webTestClient.get()
            .uri(BASE_URL + "/" + caravana.getId())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
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

        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");
        webTestClient.post()
            .uri(BASE_URL)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
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
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");

        CaravanaDTO actualizada = new CaravanaDTO();
        actualizada.setNombre("Modificada");
        actualizada.setVelocidad_actual(12);
        actualizada.setCapacidad_actual(60);
        actualizada.setDinero(1200);
        actualizada.setVidas(4);
        actualizada.setGuardias(false);

        webTestClient.put()
            .uri(BASE_URL + "/update/" + caravana.getId())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
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
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");

        webTestClient.delete()
            .uri(BASE_URL + "/" + caravana.getId())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
            .exchange()
            .expectStatus().isOk();

        webTestClient.get()
            .uri(BASE_URL + "/" + caravana.getId())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    void testViajarCaravana() {
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");
        Ciudad destino = ciudadRepository.save(new Ciudad("Ciudad Destino", 50, 20, 20));
        Ruta ruta = rutaRepository.save(new Ruta(ciudadInicial, destino, 0, 50.0f, "Camino de prueba"));

        webTestClient.get()
            .uri(BASE_URL + "/viajar/" + ruta.getId())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
            .exchange()
            .expectStatus().isOk();
    }
}