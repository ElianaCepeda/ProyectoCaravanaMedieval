package co.edu.javeriana.juego_caravana_medieval.init;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.javeriana.juego_caravana_medieval.model.Caravana;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.model.Ruta;
import co.edu.javeriana.juego_caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.CiudadRepository;
//import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        Caravana caravana = new Caravana("nombre1", 3, 4, 30, 5);
        caravanaRepository.save(caravana);
    
        // Crear ciudades
        List<Ciudad> ciudades = List.of(
            new Ciudad("Burgo de Alba", 8),
            new Ciudad("Villa del Roble", 10),
            new Ciudad("Rivendel", 12),
            new Ciudad("Draconia", 18),
            new Ciudad("Nimbaria", 10),
            new Ciudad("Eldoria", 15),
            new Ciudad("Montespino", 9),
            new Ciudad("Valderroble", 11),
            new Ciudad("Castelverde", 14),
            new Ciudad("Roca Alta", 13),
            new Ciudad("Brumareda", 8),
            new Ciudad("Norhavn", 17),
            new Ciudad("Isenfort", 16),
            new Ciudad("Viladriana", 10),
            new Ciudad("Stormhold", 19),
            new Ciudad("Drakensberg", 12),
            new Ciudad("Alborea", 9),
            new Ciudad("Rúnathil", 14),
            new Ciudad("Monteluz", 11),
            new Ciudad("Thundara", 18),
            new Ciudad("Darkhollow", 17),
            new Ciudad("Windmere", 15),
            new Ciudad("Agremont", 10),
            new Ciudad("Zephyria", 13),
            new Ciudad("Vallargenta", 12),
            new Ciudad("Ravencrest", 14),
            new Ciudad("Wintergate", 16),
            new Ciudad("Galdorheim", 9),
            new Ciudad("Solhaven", 10),
            new Ciudad("Erenholm", 8),
            new Ciudad("Brisavalle", 15),
            new Ciudad("Dunhollow", 18),
            new Ciudad("Havenbrook", 12),
            new Ciudad("Mirvalis", 11),
            new Ciudad("Vallfior", 9),
            new Ciudad("Forghast", 17),
            new Ciudad("Astervale", 14),
            new Ciudad("Dornwich", 16),
            new Ciudad("Ivarynth", 19),
            new Ciudad("Myrkwell", 12),
            new Ciudad("Tarnwell", 10),
            new Ciudad("Silverbrook", 13),
            new Ciudad("Fjordheim", 15),
            new Ciudad("Oblivion", 18),
            new Ciudad("Hollowshade", 16),
            new Ciudad("Velandria", 11),
            new Ciudad("Starhaven", 17),
            new Ciudad("Glimmerhold", 12),
            new Ciudad("Caerthas", 14),
            new Ciudad("Dravenor", 19),
            new Ciudad("Flamberg", 13),
            new Ciudad("Gorundell", 10),
            new Ciudad("Valsturm", 9),
            new Ciudad("Daggerfall", 11),
            new Ciudad("Castelor", 14),
            new Ciudad("Nordhaven", 15),
            new Ciudad("Eldwynn", 16),
            new Ciudad("Lunaris", 12),
            new Ciudad("Hearthglen", 18),
            new Ciudad("Ziranthea", 10),
            new Ciudad("Malveria", 17),
            new Ciudad("Ivoria", 8),
            new Ciudad("Brighthaven", 14),
            new Ciudad("Stormwall", 13),
            new Ciudad("Thalendor", 15),
            new Ciudad("Almora", 9),
            new Ciudad("Runebridge", 12),
            new Ciudad("Briarcrest", 16),
            new Ciudad("Falkreath", 10),
            new Ciudad("Oakheart", 11),
            new Ciudad("Westfall", 14),
            new Ciudad("Evermere", 13),
            new Ciudad("Shadowfen", 17),
            new Ciudad("Winterhelm", 19),
            new Ciudad("Stormreach", 18),
            new Ciudad("Ebonhold", 12),
            new Ciudad("Harrowgate", 15),
            new Ciudad("Frostmere", 9),
            new Ciudad("Dragonspire", 14),
            new Ciudad("Starspire", 13),
            new Ciudad("Windhaven", 11),
            new Ciudad("Dawnveil", 10),
            new Ciudad("Gloomshade", 17),
            new Ciudad("Blackstone", 16),
            new Ciudad("Ravenholm", 18),
            new Ciudad("Ivorywatch", 12),
            new Ciudad("Brinehold", 14),
            new Ciudad("Myrefrost", 15),
            new Ciudad("Evercrest", 13),
            new Ciudad("Sylvanvale", 9),
            new Ciudad("Ashenport", 10),
            new Ciudad("Moonbright", 16),
            new Ciudad("Verdenshire", 12),
            new Ciudad("Helmsgate", 11),
            new Ciudad("Drifthaven", 19),
            new Ciudad("Silverkeep", 14)
        );
    
        ciudadRepository.saveAll(ciudades);
        System.out.println("Ciudades iniciales insertadas.");
    
      // Crear rutas aleatorias
Random random = new Random();
List<Ruta> rutas = ciudades.stream()
    .flatMap(ciudad -> ciudades.stream()
        .filter(destino -> !ciudad.equals(destino))
        .limit(3) // Limitar a 3 rutas por ciudad
        .map(destino -> new Ruta(
            ciudad,
            destino,
            random.nextInt(100) + 1,
            "Ruta de " + ciudad.getNombre() + " a " + destino.getNombre() // Costo de daño aleatorio entre 1 y 100
        ))
    )
    .collect(Collectors.toList());

rutaRepository.saveAll(rutas);
System.out.println("Rutas iniciales insertadas.");
    
        // Crear productos
        productoRepository.save(new Producto("Elixir de vida", 500));
        productoRepository.save(new Producto("Espada de fuego", 1200));
        productoRepository.save(new Producto("Armadura de hierro", 2500));
        productoRepository.save(new Producto("Pan de centeno", 50));
        productoRepository.save(new Producto("Barril de cerveza", 300));
        productoRepository.save(new Producto("Cuero Curtido", 150));
        productoRepository.save(new Producto("Poción de maná", 450));
        productoRepository.save(new Producto("Daga encantada", 900));
        productoRepository.save(new Producto("Yelmo de Mithril", 3200));
        productoRepository.save(new Producto("Pata de jabalí asada", 80));
        productoRepository.save(new Producto("Bolsa de especias exóticas", 600));
        productoRepository.save(new Producto("Carne de venado", 200));
        productoRepository.save(new Producto("Anillo de invisibilidad", 5000));
        productoRepository.save(new Producto("Varita de roble mágico", 1500));
        productoRepository.save(new Producto("Piedra de afilar rúnica", 350));
        productoRepository.save(new Producto("Tomo de hechizos antiguos", 2200));
        productoRepository.save(new Producto("Escudo de dragón", 2800));
        productoRepository.save(new Producto("Barril de hidromiel", 450));
        productoRepository.save(new Producto("Queso curado de cabra", 120));
        productoRepository.save(new Producto("Botas de sigilo", 1700));
        productoRepository.save(new Producto("Cristal de energía arcana", 1300));
        productoRepository.save(new Producto("Flechas de plata", 700));
        productoRepository.save(new Producto("Guantes de fuerza titánica", 2100));
        productoRepository.save(new Producto("Miel de flores salvajes", 180));
        productoRepository.save(new Producto("Pergamino de teletransporte", 800));
        productoRepository.save(new Producto("Manzana dorada", 999));
        productoRepository.save(new Producto("Reloj de arena del tiempo", 3500));
        productoRepository.save(new Producto("Cerveza negra enana", 250));
        productoRepository.save(new Producto("Capa de sombras", 2000));
        productoRepository.save(new Producto("Espada corta de acero valyrio", 4000));
        productoRepository.save(new Producto("Piedra filosofal", 10000));
        productoRepository.save(new Producto("Grimorio de conjuros oscuros", 2700));
        productoRepository.save(new Producto("Lanza de trueno", 2900));
        productoRepository.save(new Producto("Aceite de antorcha", 75));
        productoRepository.save(new Producto("Pez salado ahumado", 90));
        productoRepository.save(new Producto("Cinturón de gigante", 3400));
        productoRepository.save(new Producto("Amuleto de protección sagrada", 1500));
        productoRepository.save(new Producto("Botella de vino élfico", 800));
        productoRepository.save(new Producto("Casco de obsidiana", 3100));
        productoRepository.save(new Producto("Saco de harina de trigo", 60));
        productoRepository.save(new Producto("Espada bastarda", 1700));
        productoRepository.save(new Producto("Lágrima de sirena", 2500));
        productoRepository.save(new Producto("Orbe de visión profética", 5000));
        productoRepository.save(new Producto("Llama eterna en frasco", 4000));
        productoRepository.save(new Producto("Diente de basilisco", 2200));
        productoRepository.save(new Producto("Collar de perlas negras", 1200));
        productoRepository.save(new Producto("Cetro de reyes", 4500));
        productoRepository.save(new Producto("Martillo de los dioses", 6000));
    
        System.out.println("Productos iniciales insertados.");
    }

}