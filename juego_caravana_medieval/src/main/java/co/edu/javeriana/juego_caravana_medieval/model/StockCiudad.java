package co.edu.javeriana.juego_caravana_medieval.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class StockCiudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Ciudad ciudad;

    @ManyToOne
    private Producto producto;

    private int cantidad;
    private int factor_demanada;
    private int factor_oferta;

    public StockCiudad() {
    }

    public StockCiudad(int cantidad, int factor_demanada, int factor_oferta) {
        this.cantidad = cantidad;
        this.factor_demanada = factor_demanada;
        this.factor_oferta = factor_oferta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getFactor_demanada() {
        return factor_demanada;
    }

    public void setFactor_demanada(int factor_demanada) {
        this.factor_demanada = factor_demanada;
    }

    public int getFactor_oferta() {
        return factor_oferta;
    }

    public void setFactor_oferta(int factor_oferta) {
        this.factor_oferta = factor_oferta;
    }

    

    
}
