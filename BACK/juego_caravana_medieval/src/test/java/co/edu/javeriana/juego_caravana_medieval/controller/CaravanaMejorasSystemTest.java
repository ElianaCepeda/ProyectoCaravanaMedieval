package co.edu.javeriana.juego_caravana_medieval.controller;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import co.edu.javeriana.juego_caravana_medieval.model.*;
import co.edu.javeriana.juego_caravana_medieval.repository.*;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("system-testing")
public class CaravanaMejorasSystemTest {

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private ServicioRepository servicioRepository;

   @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private WebTestClient webTestClient;
    @Autowired private TestRestTemplate rest;

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    private String BASE_URL = "http://localhost:4200";

    @BeforeEach
    void setup() {  
        Ciudad origen = ciudadRepository.save(new Ciudad("Origen", 100, 5000, 5000));
    
        Caravana caravana = new Caravana("Exploradora", 2, 10, 1000, 70, true);
        caravana.setCiudad(origen);
        caravanaRepository.save(caravana);


        servicioRepository.save(new Servicio("Reparación de Caravana", 100, "Repara la caravana y la deja como nueva."));
        servicioRepository.save(new Servicio("Mejora de Capacidad", 200, "Aumenta la capacidad de carga de la caravana."));
        servicioRepository.save(new Servicio("Mejora de Velocidad", 300, "Aumenta la velocidad de la caravana."));

       userRepository.save(new User("Bob", "Bobson", "bob@bob.com", passwordEncoder.encode("bob123"), Role.CARAVANERO));

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }


@Test
void mejorarCaravana() {
    // === 1. Iniciar desde pantalla de Login ===
    page.navigate(BASE_URL + "/login");

    // === 2. Ingresar credenciales ===
    page.locator("#txtEmail").fill("bob@bob.com");
    page.locator("#txtPassword").fill("bob123");

    // === 3. Hacer login ===
    page.locator("button:has-text('Login')").click();

    // === 4. Esperar carga del mapa ===

    // === 5. Ir a Caravana ===
    page.locator("button:has-text('Caravana')").click();

    // === 6. Esperar visibilidad de estadísticas ===
    Locator saludLocator = page.locator(".stats-box >> text=Salud:");
    Locator velocidadLocator = page.locator(".stats-box >> text=Velocidad:");
    Locator capacidadLocator = page.locator(".stats-box >> text=Capacidad:");

    saludLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));
    velocidadLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));
    capacidadLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));

    PlaywrightAssertions.assertThat(saludLocator).hasText("Salud: 70");
    PlaywrightAssertions.assertThat(velocidadLocator).hasText("Velocidad: 4");
    PlaywrightAssertions.assertThat(capacidadLocator).hasText("Capacidad: 10");

    // === 7. Devolverse al mapa ===
    page.locator("button:has-text('Devolverse')").click();

    // === 8. Capturar dinero antes de comprar ===
    String dineroAntes = page.locator(".money-amount").textContent();
    int dineroInicial = Integer.parseInt(dineroAntes.trim());

    // === 9. Ir a Servicios ===
    page.locator("button:has-text('Servicios')").click();

    // === 10. Comprar servicios ===
    for (String servicio : new String[]{"Reparación de Caravana", "Mejora de Capacidad", "Mejora de Velocidad"}) {
        Locator fila = page.locator("tr:has(td:has-text('" + servicio + "'))");
        fila.locator("button:has-text('Comprar')").click();
        page.waitForTimeout(500); // Por efecto visual
    }

    // === 11. Volver al mapa ===
    page.locator("button:has-text('Devolverse')").click();

    // === 12. Verificar dinero restante ===
    String dineroDespues = page.locator(".money-amount").textContent();
    int dineroFinal = Integer.parseInt(dineroDespues.trim());

    int esperado = dineroInicial - (100 + 200 + 300);
    PlaywrightAssertions.assertThat(page.locator(".money-amount")).hasText(String.valueOf(esperado));

    // === 13. Verificar estadísticas después de mejoras ===
    page.locator("button:has-text('Caravana')").click();
    PlaywrightAssertions.assertThat(page.locator(".stats-box >> text=Salud: 100")).isVisible();
    PlaywrightAssertions.assertThat(page.locator(".stats-box >> text=Velocidad: 5")).isVisible();
    PlaywrightAssertions.assertThat(page.locator(".stats-box >> text=Capacidad: 11")).isVisible();
}

}