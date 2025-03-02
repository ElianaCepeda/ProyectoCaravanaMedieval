package co.edu.javeriana.juego_caravana_medieval.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.javeriana.juego_caravana_medieval.model.Caravana;
import co.edu.javeriana.juego_caravana_medieval.repository.CaravanaRepository;
//import co.edu.javeriana.juego_caravana_medieval.repository.RutaRepository;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private CaravanaRepository caravanaRepository;

    // @Autowired
    // private RutaRepository rutaRepository;

    @Override
    public void run(String... args) throws Exception {
        Caravana caravana = new Caravana("nombre1", 3, 4, 30, 5);

        caravanaRepository.save(caravana);
        
    }
    
}
