package co.edu.javeriana.juego_caravana_medieval.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.javeriana.juego_caravana_medieval.model.Caravana;
import co.edu.javeriana.juego_caravana_medieval.model.Ciudad;
import co.edu.javeriana.juego_caravana_medieval.model.Producto;
import co.edu.javeriana.juego_caravana_medieval.model.Ruta;
import co.edu.javeriana.juego_caravana_medieval.model.Servicio;
import co.edu.javeriana.juego_caravana_medieval.repository.CaravanaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.ProductoRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;
import co.edu.javeriana.juego_caravana_medieval.repository.ServicioRepository;
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

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Caravana caravana = new Caravana("Destructor de Pueblos", 15, 5, 1200, 200,
        // false);
        // caravanaRepository.save(caravana);

        Caravana tormentaRapida = new Caravana("Tormenta Rápida", 2, 2, 1250, 100, false);
        Caravana caravanaReal = new Caravana("Caravana Real", 1, 1, 2500, 100, false);
        Caravana giganteCarga = new Caravana("Gigante de Carga", 1, 3, 1000, 100, false);

        caravanaRepository.save(tormentaRapida);
        caravanaRepository.save(caravanaReal);
        caravanaRepository.save(giganteCarga);
        // Crear ciudades
        List<Ciudad> ciudades = List.of(
                new Ciudad("Burgo de Alba", 8, generarX(), generarY()),
                new Ciudad("Villa del Roble", 10, generarX(), generarY()),
                new Ciudad("Rivendel", 12, generarX(), generarY()),
                new Ciudad("Draconia", 18, generarX(), generarY()),
                new Ciudad("Nimbaria", 10, generarX(), generarY()),
                new Ciudad("Eldoria", 15, generarX(), generarY()),
                new Ciudad("Montespino", 9, generarX(), generarY()),
                new Ciudad("Valderroble", 11, generarX(), generarY()),
                new Ciudad("Castelverde", 14, generarX(), generarY()),
                new Ciudad("Roca Alta", 13, generarX(), generarY()),
                new Ciudad("Brumareda", 8, generarX(), generarY()),
                new Ciudad("Norhavn", 17, generarX(), generarY()),
                new Ciudad("Isenfort", 16, generarX(), generarY()),
                new Ciudad("Viladriana", 10, generarX(), generarY()),
                new Ciudad("Stormhold", 19, generarX(), generarY()),
                new Ciudad("Drakensberg", 12, generarX(), generarY()),
                new Ciudad("Alborea", 9, generarX(), generarY()),
                new Ciudad("Rúnathil", 14, generarX(), generarY()),
                new Ciudad("Monteluz", 11, generarX(), generarY()),
                new Ciudad("Thundara", 18, generarX(), generarY()),
                new Ciudad("Darkhollow", 17, generarX(), generarY()),
                new Ciudad("Windmere", 15, generarX(), generarY()),
                new Ciudad("Agremont", 10, generarX(), generarY()),
                new Ciudad("Zephyria", 13, generarX(), generarY()),
                new Ciudad("Vallargenta", 12, generarX(), generarY()),
                new Ciudad("Ravencrest", 14, generarX(), generarY()),
                new Ciudad("Wintergate", 16, generarX(), generarY()),
                new Ciudad("Galdorheim", 9, generarX(), generarY()),
                new Ciudad("Solhaven", 10, generarX(), generarY()),
                new Ciudad("Erenholm", 8, generarX(), generarY()),
                new Ciudad("Brisavalle", 15, generarX(), generarY()),
                new Ciudad("Dunhollow", 18, generarX(), generarY()),
                new Ciudad("Havenbrook", 12, generarX(), generarY()),
                new Ciudad("Mirvalis", 11, generarX(), generarY()),
                new Ciudad("Vallfior", 9, generarX(), generarY()),
                new Ciudad("Forghast", 17, generarX(), generarY()),
                new Ciudad("Astervale", 14, generarX(), generarY()),
                new Ciudad("Dornwich", 16, generarX(), generarY()),
                new Ciudad("Ivarynth", 19, generarX(), generarY()),
                new Ciudad("Myrkwell", 12, generarX(), generarY()),
                new Ciudad("Tarnwell", 10, generarX(), generarY()),
                new Ciudad("Silverbrook", 13, generarX(), generarY()),
                new Ciudad("Fjordheim", 15, generarX(), generarY()),
                new Ciudad("Oblivion", 18, generarX(), generarY()),
                new Ciudad("Hollowshade", 16, generarX(), generarY()),
                new Ciudad("Velandria", 11, generarX(), generarY()),
                new Ciudad("Starhaven", 17, generarX(), generarY()),
                new Ciudad("Glimmerhold", 12, generarX(), generarY()),
                new Ciudad("Caerthas", 14, generarX(), generarY()),
                new Ciudad("Dravenor", 19, generarX(), generarY()),
                new Ciudad("Flamberg", 13, generarX(), generarY()),
                new Ciudad("Gorundell", 10, generarX(), generarY()),
                new Ciudad("Valsturm", 9, generarX(), generarY()),
                new Ciudad("Daggerfall", 11, generarX(), generarY()),
                new Ciudad("Castelor", 14, generarX(), generarY()),
                new Ciudad("Nordhaven", 15, generarX(), generarY()),
                new Ciudad("Eldwynn", 16, generarX(), generarY()),
                new Ciudad("Lunaris", 12, generarX(), generarY()),
                new Ciudad("Hearthglen", 18, generarX(), generarY()),
                new Ciudad("Ziranthea", 10, generarX(), generarY()),
                new Ciudad("Malveria", 17, generarX(), generarY()),
                new Ciudad("Ivoria", 8, generarX(), generarY()),
                new Ciudad("Brighthaven", 14, generarX(), generarY()),
                new Ciudad("Stormwall", 13, generarX(), generarY()),
                new Ciudad("Thalendor", 15, generarX(), generarY()),
                new Ciudad("Almora", 9, generarX(), generarY()),
                new Ciudad("Runebridge", 12, generarX(), generarY()),
                new Ciudad("Briarcrest", 16, generarX(), generarY()),
                new Ciudad("Falkreath", 10, generarX(), generarY()),
                new Ciudad("Oakheart", 11, generarX(), generarY()),
                new Ciudad("Westfall", 14, generarX(), generarY()),
                new Ciudad("Evermere", 13, generarX(), generarY()),
                new Ciudad("Shadowfen", 17, generarX(), generarY()),
                new Ciudad("Winterhelm", 19, generarX(), generarY()),
                new Ciudad("Stormreach", 18, generarX(), generarY()),
                new Ciudad("Ebonhold", 12, generarX(), generarY()),
                new Ciudad("Harrowgate", 15, generarX(), generarY()),
                new Ciudad("Frostmere", 9, generarX(), generarY()),
                new Ciudad("Dragonspire", 14, generarX(), generarY()),
                new Ciudad("Starspire", 13, generarX(), generarY()),
                new Ciudad("Windhaven", 11, generarX(), generarY()),
                new Ciudad("Dawnveil", 10, generarX(), generarY()),
                new Ciudad("Gloomshade", 17, generarX(), generarY()),
                new Ciudad("Blackstone", 16, generarX(), generarY()),
                new Ciudad("Ravenholm", 18, generarX(), generarY()),
                new Ciudad("Ivorywatch", 12, generarX(), generarY()),
                new Ciudad("Brinehold", 14, generarX(), generarY()),
                new Ciudad("Myrefrost", 15, generarX(), generarY()),
                new Ciudad("Evercrest", 13, generarX(), generarY()),
                new Ciudad("Sylvanvale", 9, generarX(), generarY()),
                new Ciudad("Ashenport", 10, generarX(), generarY()),
                new Ciudad("Moonbright", 16, generarX(), generarY()),
                new Ciudad("Verdenshire", 12, generarX(), generarY()),
                new Ciudad("Helmsgate", 11, generarX(), generarY()),
                new Ciudad("Drifthaven", 19, generarX(), generarY()),
                new Ciudad("Silverkeep", 14, generarX(), generarY()));

        ciudadRepository.saveAll(ciudades);
        System.out.println("Ciudades iniciales insertadas.");

        // Crear rutas aleatorias
        // Crear rutas aleatorias
        Random random = new Random();
        List<Ruta> rutas = new ArrayList<>();

        for (Ciudad ciudad : ciudades) {
            // Crear una lista de posibles destinos (todas menos la ciudad actual)
            List<Ciudad> posiblesDestinos = new ArrayList<>(ciudades);
            posiblesDestinos.remove(ciudad);

            // Mezclar la lista para obtener destinos aleatorios
            Collections.shuffle(posiblesDestinos, random);

            // Tomar los primeros 5 destinos aleatorios
            List<Ciudad> destinosSeleccionados = posiblesDestinos.stream().limit(5).collect(Collectors.toList());

            for (Ciudad destino : destinosSeleccionados) {
                boolean esSegura = random.nextBoolean();
                String descripcion;
                if (esSegura) {
                    descripcion = "Ruta segura. Es más larga, pero la caravana no sufre daño durante el viaje.";
                } else {
                    descripcion = random.nextBoolean() ? "Ruta insegura. Hay bandidos en el camino."
                            : "Ruta insegura. Hay desastres naturales en el camino.";
                }
                int costoDano = esSegura ? 0 : random.nextInt(100) + 1; // 1 a 100 para inseguras, 0 para seguras
                rutas.add(new Ruta(
                        ciudad,
                        destino,
                        costoDano,
                        descripcion));
            }
        }

        rutaRepository.saveAll(rutas);
        System.out.println("Rutas iniciales insertadas.");
        rutaRepository.saveAll(rutas);
        System.out.println("Rutas iniciales insertadas.");

        // Crear servicios
        servicioRepository
                .save(new Servicio("Reparación de Caravana", 100, "Repara la caravana y la deja como nueva."));
        servicioRepository
                .save(new Servicio("Mejora de Capacidad", 200, "Aumenta la capacidad de carga de la caravana."));
        servicioRepository.save(new Servicio("Mejora de Velocidad", 300, "Aumenta la velocidad de la caravana."));
        servicioRepository
                .save(new Servicio("Contratación de Guardias", 400, "Contrata guardias para proteger la caravana."));

        // Crear productos
        productoRepository.save(new Producto("Elixir de vida", 50));
        productoRepository.save(new Producto("Espada de fuego", 120));
        productoRepository.save(new Producto("Armadura de hierro", 250));
        productoRepository.save(new Producto("Pan de centeno", 5));
        productoRepository.save(new Producto("Barril de cerveza", 30));
        productoRepository.save(new Producto("Cuero Curtido", 15));
        productoRepository.save(new Producto("Poción de maná", 45));
        productoRepository.save(new Producto("Daga encantada", 90));
        productoRepository.save(new Producto("Yelmo de Mithril", 320));
        productoRepository.save(new Producto("Pata de jabalí asada", 80));
        productoRepository.save(new Producto("Bolsa de especias exóticas", 60));
        productoRepository.save(new Producto("Carne de venado", 20));
        productoRepository.save(new Producto("Anillo de invisibilidad", 500));
        productoRepository.save(new Producto("Varita de roble mágico", 150));
        productoRepository.save(new Producto("Piedra de afilar rúnica", 35));
        productoRepository.save(new Producto("Tomo de hechizos antiguos", 220));
        productoRepository.save(new Producto("Escudo de dragón", 280));
        productoRepository.save(new Producto("Barril de hidromiel", 45));
        productoRepository.save(new Producto("Queso curado de cabra", 12));
        productoRepository.save(new Producto("Botas de sigilo", 170));
        productoRepository.save(new Producto("Cristal de energía arcana", 130));
        productoRepository.save(new Producto("Flechas de plata", 70));
        productoRepository.save(new Producto("Guantes de fuerza titánica", 210));
        productoRepository.save(new Producto("Miel de flores salvajes", 18));
        productoRepository.save(new Producto("Pergamino de teletransporte", 80));
        productoRepository.save(new Producto("Manzana dorada", 99));
        productoRepository.save(new Producto("Reloj de arena del tiempo", 350));
        productoRepository.save(new Producto("Cerveza negra enana", 25));
        productoRepository.save(new Producto("Capa de sombras", 200));
        productoRepository.save(new Producto("Espada corta de acero valyrio", 400));
        productoRepository.save(new Producto("Piedra filosofal", 1000));
        productoRepository.save(new Producto("Grimorio de conjuros oscuros", 270));
        productoRepository.save(new Producto("Lanza de trueno", 290));
        productoRepository.save(new Producto("Aceite de antorcha", 75));
        productoRepository.save(new Producto("Pez salado ahumado", 90));
        productoRepository.save(new Producto("Cinturón de gigante", 340));
        productoRepository.save(new Producto("Amuleto de protección sagrada", 150));
        productoRepository.save(new Producto("Botella de vino élfico", 80));
        productoRepository.save(new Producto("Casco de obsidiana", 310));
        productoRepository.save(new Producto("Saco de harina de trigo", 60));
        productoRepository.save(new Producto("Espada bastarda", 170));
        productoRepository.save(new Producto("Lágrima de sirena", 250));
        productoRepository.save(new Producto("Orbe de visión profética", 500));
        productoRepository.save(new Producto("Llama eterna en frasco", 400));
        productoRepository.save(new Producto("Diente de basilisco", 220));
        productoRepository.save(new Producto("Collar de perlas negras", 120));
        productoRepository.save(new Producto("Cetro de reyes", 450));
        productoRepository.save(new Producto("Martillo de los dioses", 600));

        System.out.println("Productos iniciales insertados.");
    }

    private int generarX() {
        // { xMin, xMax }
        int[][] zonas = {
                { 3000, 8000 }, // Oeste
                { 9500, 19500 }, // Centro
                { 19500, 24500 }, // Este
                { 24500, 28500 } // Extremo este
        };
        int[] zona = zonas[new Random().nextInt(zonas.length)];
        return new Random().nextInt(zona[1] - zona[0]) + zona[0];
    }

    private int generarY() {
        // { yMin, yMax }
        int[][] zonas = {
                { 5500, 11000 }, // Oeste
                { 5500, 11000 }, // Centro
                { 3500, 10500 }, // Este
                { 5000, 11000 } // Extremo este
        };
        int[] zona = zonas[new Random().nextInt(zonas.length)];
        return new Random().nextInt(zona[1] - zona[0]) + zona[0];
    }

}