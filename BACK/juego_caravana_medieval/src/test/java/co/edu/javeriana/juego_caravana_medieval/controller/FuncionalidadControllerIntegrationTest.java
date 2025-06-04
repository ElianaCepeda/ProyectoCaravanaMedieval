
package co.edu.javeriana.juego_caravana_medieval.controller;

import co.edu.javeriana.juego_caravana_medieval.DTO.JwtAuthenticationResponse;
import co.edu.javeriana.juego_caravana_medieval.DTO.LoginDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.ProductoDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.ServicioDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.model.Role;
import co.edu.javeriana.juego_caravana_medieval.model.Servicio;
import co.edu.javeriana.juego_caravana_medieval.model.User;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.ServicioRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        @Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	

	@Autowired
	private TestRestTemplate rest;

    @BeforeEach
    void setup() {
        productoRepository.save(new Producto("Espada", 200));
        productoRepository.save(new Producto("Manzana", 50));

        servicioRepository.save(new Servicio("Curación", 100, "Recupera salud"));
        servicioRepository.save(new Servicio("Protección", 150, "Aumenta defensa"));
   
   	    userRepository.save(
				new User("Alice", "Alisson", "alice@alice.com", passwordEncoder.encode("alice123"), Role.COMERCIANTE));
		userRepository.save(
				new User("Bob", "Bobson", "bob@bob.com", passwordEncoder.encode("bob123"), Role.CARAVANERO));

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
    void testObtenerProductos_comoComerciante() {
        JwtAuthenticationResponse alice = login("alice@alice.com", "alice123");

        webTestClient.get()
                .uri(BASE_URL + "/productos")
                .header("Authorization", "Bearer " + alice.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProductoDTO.class)
                .hasSize(2);
    }

    @Test
    void testObtenerProductos_comoCaravanero() {
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");

        webTestClient.get()
                .uri(BASE_URL + "/productos")
                .header("Authorization", "Bearer " + bob.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProductoDTO.class)
                .hasSize(2);
    }

    @Test
    void testObtenerServicios_comoCaravanero() {
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");

        webTestClient.get()
                .uri(BASE_URL + "/servicios")
                .header("Authorization", "Bearer " + bob.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ServicioDTO.class)
                .hasSize(2);
    }

} 
