package co.edu.javeriana.juego_caravana_medieval.controller;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

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

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }


@Test
void mejorarCaravana() {
    page.navigate(BASE_URL + "/mapCaravania");

    // === Ir a Caravana ===
    page.locator("button:has-text('Caravana')").click();

   
   // Esperar explícitamente que los textos aparezcan
Locator saludLocator = page.locator(".stats-box >> text=Salud:");
Locator velocidadLocator = page.locator(".stats-box >> text=Velocidad:");
Locator capacidadLocator = page.locator(".stats-box >> text=Capacidad:");

saludLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));
velocidadLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));
capacidadLocator.waitFor(new Locator.WaitForOptions().setTimeout(3000));

// Usar PlaywrightAssertions después de la espera
PlaywrightAssertions.assertThat(saludLocator).hasText("Salud: 70");
PlaywrightAssertions.assertThat(velocidadLocator).hasText("Velocidad: 4");
PlaywrightAssertions.assertThat(capacidadLocator).hasText("Capacidad: 10");
    // Devolverse al mapa
    page.locator("button:has-text('Devolverse')").click();

    // === Capturar dinero antes de comprar ===
    String dineroAntes = page.locator(".money-amount").textContent();
    int dineroInicial = Integer.parseInt(dineroAntes.trim());

    // Ir a Servicios
    page.locator("button:has-text('Servicios')").click();

    // Comprar todos los servicios disponibles
    for (String servicio : new String[]{"Reparación de Caravana", "Mejora de Capacidad", "Mejora de Velocidad"}) {
        Locator fila = page.locator("tr:has(td:has-text('" + servicio + "'))");
        fila.locator("button:has-text('Comprar')").click();
        page.waitForTimeout(500); // Espera breve por efecto visual
    }

    // Devolverse al mapa
    page.locator("button:has-text('Devolverse')").click();

    // === Verificar dinero restante ===
    String dineroDespues = page.locator(".money-amount").textContent();
    int dineroFinal = Integer.parseInt(dineroDespues.trim());

    int esperado = dineroInicial - (100 + 200 + 300); // Total gasto
    
    PlaywrightAssertions.assertThat(page.locator(".money-amount")).hasText(String.valueOf(esperado));

    // === Ir de nuevo a Caravana ===
    page.locator("button:has-text('Caravana')").click();

    // Verificar estadísticas actualizadas
    PlaywrightAssertions.assertThat(page.locator(".stats-box >> text=Salud: 100")).isVisible();
    PlaywrightAssertions.assertThat(page.locator(".stats-box >> text=Velocidad: 5")).isVisible();
    PlaywrightAssertions.assertThat(page.locator(".stats-box >> text=Capacidad: 11")).isVisible();
}

}
