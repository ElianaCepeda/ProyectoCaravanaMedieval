package co.edu.javeriana.juego_caravana_medieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.juego_caravana_medieval.model.StockCiudad;

@Repository
public interface StockCiudadRepository extends JpaRepository <StockCiudad, Long>{
    
}
