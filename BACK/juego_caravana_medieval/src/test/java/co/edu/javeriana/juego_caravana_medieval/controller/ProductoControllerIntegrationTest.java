package co.edu.javeriana.juego_caravana_medieval.controller;


import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import co.edu.javeriana.juego_caravana_medieval.DTO.JwtAuthenticationResponse;
import co.edu.javeriana.juego_caravana_medieval.DTO.LoginDTO;
import co.edu.javeriana.juego_caravana_medieval.DTO.ProductoDTO;
import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.model.Role;
import co.edu.javeriana.juego_caravana_medieval.model.User;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.UserRepository;

@SpringBootTest (webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("integration-testing")
public class ProductoControllerIntegrationTest {
    
    private static final String BASE_URL = "http://localhost:8081/producto";
    
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private WebTestClient webTestClient;

        @Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	

	@Autowired
	private TestRestTemplate rest;

    @BeforeEach
    void init(){
        productoRepository.save(new Producto("Espada", 200));
        productoRepository.save(new Producto("Manzana", 30));
        productoRepository.save(new Producto("Capa de sombras", 200));

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
void productos_comoComerciante() {
    JwtAuthenticationResponse alice = login("alice@alice.com", "alice123");

    webTestClient.get()
        .uri(BASE_URL + "/list")
        .header("Authorization", "Bearer " + alice.getToken())
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ProductoDTO.class)
        .hasSize(3);
}

}
