package co.edu.javeriana.juego_caravana_medieval.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.javeriana.juego_caravana_medieval.model.Caravana;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.CiudadRepository;
//import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        Caravana caravana = new Caravana("nombre1", 3, 4, 30, 5);

        caravanaRepository.save(caravana);

        ciudadRepository.save(new Ciudad("Burgo de Alba", 8));
        ciudadRepository.save(new Ciudad("Villa del Roble", 10));
        ciudadRepository.save(new Ciudad("Rivendel", 12));
        ciudadRepository.save(new Ciudad("Draconia", 18));
        ciudadRepository.save(new Ciudad("Nimbaria", 10));
        System.out.println("Ciudades iniciales insertadas.");


        productoRepository.save(new Producto("Elixir de vida", 500));
        productoRepository.save(new Producto("Espada de fuego", 1200));
        productoRepository.save(new Producto("Armadura de hierro", 2500));
        productoRepository.save(new Producto("Pan de centeno", 50));
        productoRepository.save(new Producto("Barril de cerveza", 300));
        productoRepository.save(new Producto("Cuero Curtido", 150));
        System.out.println("Productos iniciales insertados.");
        
    }
    
}