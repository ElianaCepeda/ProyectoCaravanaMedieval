package co.edu.javeriana.juego_caravana_medieval.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;

@Entity
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Ciudad ciudad_origen;

    @ManyToOne
    private Ciudad ciudad_destino;


    private int cantidad_dano;
    private String descripcion_dano;

    public Ruta() {
    } 

    public Ruta(int cantidad_dano, String descripcion_dano) {
        this.cantidad_dano = cantidad_dano;
        this.descripcion_dano = descripcion_dano;
    }

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCantidad_dano() {
        return cantidad_dano;
    }
    public void setCantidad_dano(int cantidad_dano) {
        this.cantidad_dano = cantidad_dano;
    }
    public String getDescripcion_dano() {
        return descripcion_dano;
    }
    public void setDescripcion_dano(String descripcion_dano) {
        this.descripcion_dano = descripcion_dano;
    }


    
}
