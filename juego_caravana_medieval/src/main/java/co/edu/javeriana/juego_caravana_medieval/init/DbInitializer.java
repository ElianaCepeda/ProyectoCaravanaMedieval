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
        ciudadRepository.save(new Ciudad("Eldoria", 15));  
        ciudadRepository.save(new Ciudad("Montespino", 9));  
        ciudadRepository.save(new Ciudad("Valderroble", 11));  
        ciudadRepository.save(new Ciudad("Castelverde", 14));  
        ciudadRepository.save(new Ciudad("Roca Alta", 13));  
        ciudadRepository.save(new Ciudad("Brumareda", 8));  
        ciudadRepository.save(new Ciudad("Norhavn", 17));  
        ciudadRepository.save(new Ciudad("Isenfort", 16));  
        ciudadRepository.save(new Ciudad("Viladriana", 10));  
        ciudadRepository.save(new Ciudad("Stormhold", 19));  
        ciudadRepository.save(new Ciudad("Drakensberg", 12));  
        ciudadRepository.save(new Ciudad("Alborea", 9));  
        ciudadRepository.save(new Ciudad("Rúnathil", 14));  
        ciudadRepository.save(new Ciudad("Monteluz", 11));  
        ciudadRepository.save(new Ciudad("Thundara", 18));  
        ciudadRepository.save(new Ciudad("Darkhollow", 17));  
        ciudadRepository.save(new Ciudad("Windmere", 15));  
        ciudadRepository.save(new Ciudad("Agremont", 10));  
        ciudadRepository.save(new Ciudad("Zephyria", 13));  
        ciudadRepository.save(new Ciudad("Vallargenta", 12));  
        ciudadRepository.save(new Ciudad("Ravencrest", 14));  
        ciudadRepository.save(new Ciudad("Wintergate", 16));  
        ciudadRepository.save(new Ciudad("Galdorheim", 9));  
        ciudadRepository.save(new Ciudad("Solhaven", 10));  
        ciudadRepository.save(new Ciudad("Erenholm", 8));  
        ciudadRepository.save(new Ciudad("Brisavalle", 15));  
        ciudadRepository.save(new Ciudad("Dunhollow", 18));  
        ciudadRepository.save(new Ciudad("Havenbrook", 12));  
        ciudadRepository.save(new Ciudad("Mirvalis", 11));  
        ciudadRepository.save(new Ciudad("Vallfior", 9));  
        ciudadRepository.save(new Ciudad("Forghast", 17));  
        ciudadRepository.save(new Ciudad("Astervale", 14));  
        ciudadRepository.save(new Ciudad("Dornwich", 16));  
        ciudadRepository.save(new Ciudad("Ivarynth", 19));  
        ciudadRepository.save(new Ciudad("Myrkwell", 12));  
        ciudadRepository.save(new Ciudad("Tarnwell", 10));  
        ciudadRepository.save(new Ciudad("Silverbrook", 13));  
        ciudadRepository.save(new Ciudad("Fjordheim", 15));  
        ciudadRepository.save(new Ciudad("Oblivion", 18));  
        ciudadRepository.save(new Ciudad("Hollowshade", 16));  
        ciudadRepository.save(new Ciudad("Velandria", 11));  
        ciudadRepository.save(new Ciudad("Starhaven", 17));  
        ciudadRepository.save(new Ciudad("Glimmerhold", 12));  
        ciudadRepository.save(new Ciudad("Caerthas", 14));  
        ciudadRepository.save(new Ciudad("Dravenor", 19));  
        ciudadRepository.save(new Ciudad("Flamberg", 13));  
        ciudadRepository.save(new Ciudad("Gorundell", 10));  
        ciudadRepository.save(new Ciudad("Valsturm", 9));  
        ciudadRepository.save(new Ciudad("Daggerfall", 11));  
        ciudadRepository.save(new Ciudad("Castelor", 14));  
        ciudadRepository.save(new Ciudad("Nordhaven", 15));  
        ciudadRepository.save(new Ciudad("Eldwynn", 16));  
        ciudadRepository.save(new Ciudad("Lunaris", 12));  
        ciudadRepository.save(new Ciudad("Hearthglen", 18));  
        ciudadRepository.save(new Ciudad("Ziranthea", 10));  
        ciudadRepository.save(new Ciudad("Malveria", 17));  
        ciudadRepository.save(new Ciudad("Ivoria", 8));  
        ciudadRepository.save(new Ciudad("Brighthaven", 14));  
        ciudadRepository.save(new Ciudad("Stormwall", 13));  
        ciudadRepository.save(new Ciudad("Thalendor", 15));  
        ciudadRepository.save(new Ciudad("Almora", 9));  
        ciudadRepository.save(new Ciudad("Runebridge", 12));  
        ciudadRepository.save(new Ciudad("Briarcrest", 16));  
        ciudadRepository.save(new Ciudad("Falkreath", 10));  
        ciudadRepository.save(new Ciudad("Oakheart", 11));  
        ciudadRepository.save(new Ciudad("Westfall", 14));  
        ciudadRepository.save(new Ciudad("Evermere", 13));  
        ciudadRepository.save(new Ciudad("Shadowfen", 17));  
        ciudadRepository.save(new Ciudad("Winterhelm", 19));  
        ciudadRepository.save(new Ciudad("Stormreach", 18));  
        ciudadRepository.save(new Ciudad("Ebonhold", 12));  
        ciudadRepository.save(new Ciudad("Harrowgate", 15));  
        ciudadRepository.save(new Ciudad("Frostmere", 9));  
        ciudadRepository.save(new Ciudad("Dragonspire", 14));  
        ciudadRepository.save(new Ciudad("Starspire", 13));  
        ciudadRepository.save(new Ciudad("Windhaven", 11));  
        ciudadRepository.save(new Ciudad("Dawnveil", 10));  
        ciudadRepository.save(new Ciudad("Gloomshade", 17));  
        ciudadRepository.save(new Ciudad("Blackstone", 16));  
        ciudadRepository.save(new Ciudad("Ravenholm", 18));  
        ciudadRepository.save(new Ciudad("Ivorywatch", 12));  
        ciudadRepository.save(new Ciudad("Brinehold", 14));  
        ciudadRepository.save(new Ciudad("Myrefrost", 15));  
        ciudadRepository.save(new Ciudad("Evercrest", 13));  
        ciudadRepository.save(new Ciudad("Sylvanvale", 9));  
        ciudadRepository.save(new Ciudad("Ashenport", 10));  
        ciudadRepository.save(new Ciudad("Moonbright", 16));  
        ciudadRepository.save(new Ciudad("Verdenshire", 12));  
        ciudadRepository.save(new Ciudad("Helmsgate", 11));  
        ciudadRepository.save(new Ciudad("Drifthaven", 19));  
        ciudadRepository.save(new Ciudad("Silverkeep", 14));  

        System.out.println("Ciudades iniciales insertadas.");


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
