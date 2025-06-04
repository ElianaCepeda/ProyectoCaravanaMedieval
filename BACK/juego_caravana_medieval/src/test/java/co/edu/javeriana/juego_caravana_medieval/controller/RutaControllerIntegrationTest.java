package co.edu.javeriana.juego_caravana_medieval.controller;

import co.edu.javeriana.juego_caravana_medieval.DTO.JwtAuthenticationResponse;
import co.edu.javeriana.juego_caravana_medieval.DTO.LoginDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.RutaDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.model.Role;
import co.edu.javeriana.juego_caravana_medieval.model.Ruta;
import co.edu.javeriana.juego_caravana_medieval.model.User;
import co.edu.javeriana.juego_caravana_medieval.repository.CiudadRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
public class RutaControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8081/ruta";

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TestRestTemplate rest;

    private Ciudad origen;
    private Ciudad destino;
    private JwtAuthenticationResponse token;

    @BeforeEach
    void setup() {
        origen = ciudadRepository.save(new Ciudad("Ciudad A", 100, 10, 10));
        destino = ciudadRepository.save(new Ciudad("Ciudad B", 120, 20, 20));

        rutaRepository.save(new Ruta(origen, destino, 5, 50.0f, "Desierto peligroso"));

        userRepository.save(new User("Alice", "Alisson", "alice@alice.com", passwordEncoder.encode("alice123"), Role.COMERCIANTE));
        userRepository.save(new User("Bob", "Bobson", "bob@bob.com", passwordEncoder.encode("bob123"), Role.CARAVANERO));

        token = login("bob@bob.com", "bob123");
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
    void testListarRutas() {
        webTestClient.get()
                .uri(BASE_URL + "/list")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RutaDTO.class)
                .hasSize(1);
    }

    @Test
    void testBuscarRutaPorId() {
        Ruta ruta = rutaRepository.save(new Ruta(origen, destino, 3, 40.0f, "Zona montañosa"));

        webTestClient.get()
                .uri(BASE_URL + "/" + ruta.getId())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.cantidad_dano").isEqualTo(3)
                .jsonPath("$.distancia").isEqualTo(40.0)
                .jsonPath("$.descripcion_dano").isEqualTo("Zona montañosa");
    }

    @Test
    void testCrearRuta() {
        RutaDTO nuevaRuta = new RutaDTO();
        nuevaRuta.setCiudadOrigenId(origen.getId());
        nuevaRuta.setCiudadDestinoId(destino.getId());
        nuevaRuta.setCantidad_dano(7);
        nuevaRuta.setDistancia(60.5f);
        nuevaRuta.setDescripcion_dano("Bosque tenebroso");

        webTestClient.post()
                .uri(BASE_URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getToken())
                .bodyValue(nuevaRuta)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.descripcion_dano").isEqualTo("Bosque tenebroso")
                .jsonPath("$.cantidad_dano").isEqualTo(7)
                .jsonPath("$.distancia").isEqualTo(60.5);
    }

    @Test
    void testActualizarRuta() {
        Ruta ruta = rutaRepository.save(new Ruta(origen, destino, 2, 30.0f, "Río ancho"));

        RutaDTO actualizada = new RutaDTO();
        actualizada.setId(ruta.getId());
        actualizada.setCiudadOrigenId(origen.getId());
        actualizada.setCiudadDestinoId(destino.getId());
        actualizada.setCantidad_dano(9);
        actualizada.setDistancia(35.0f);
        actualizada.setDescripcion_dano("Zona volcánica");

        webTestClient.put()
                .uri(BASE_URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getToken())
                .bodyValue(actualizada)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.cantidad_dano").isEqualTo(9)
                .jsonPath("$.descripcion_dano").isEqualTo("Zona volcánica")
                .jsonPath("$.distancia").isEqualTo(35.0);
    }

    @Test
    void testEliminarRuta() {
        Ruta ruta = rutaRepository.save(new Ruta(origen, destino, 1, 25.0f, "Llanura"));

        webTestClient.delete()
                .uri(BASE_URL + "/" + ruta.getId())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getToken())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri(BASE_URL + "/" + ruta.getId())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getToken())
                .exchange()
                .expectStatus().isNotFound();
    }
}
