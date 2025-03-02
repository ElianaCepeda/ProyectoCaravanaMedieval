package co.edu.javeriana.juego_caravana_medieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.juego_caravana_medieval.model.StockCaravana;

@Repository
public interface StockCaravanaRepository extends JpaRepository <StockCaravana, Long>{

}
