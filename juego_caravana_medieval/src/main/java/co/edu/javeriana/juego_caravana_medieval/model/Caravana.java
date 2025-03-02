package co.edu.javeriana.juego_caravana_medieval.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;

@Entity
public class Caravana {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany (mappedBy = "caravana")
    private List<StockCaravana> sotck;

    private String nombre;
    private int velocidad_actual;
    private int velocidad_maxima;
    private int capacidad_actual;
    private int capacidad_maxima;
    private int dinero;
    private int vidas;

    public Caravana() {
    }

    public Caravana(String nombre, int velocidad_actual, int capacidad_actual, int dinero, int vidas) {
        this.nombre = nombre;
        this.velocidad_actual = velocidad_actual*2;
        this.velocidad_maxima = (int) (this.velocidad_actual * 1.5) ;
        this.capacidad_actual = capacidad_actual;
        this.capacidad_maxima = capacidad_actual*4;
        this.dinero = dinero;
        this.vidas = vidas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVelocidad_actual() {
        return velocidad_actual;
    }

    public void setVelocidad_actual(int velocidad_actual) {
        this.velocidad_actual = velocidad_actual;
    }

    public int getVelocidad_maxima() {
        return velocidad_maxima;
    }

    public void setVelocidad_maxima(int velocidad_maxima) {
        this.velocidad_maxima = velocidad_maxima;
    }

    public int getCapacidad_actual() {
        return capacidad_actual;
    }

    public void setCapacidad_actual(int capacidad_actual) {
        this.capacidad_actual = capacidad_actual;
    }

    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }

    public void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    

    

}
