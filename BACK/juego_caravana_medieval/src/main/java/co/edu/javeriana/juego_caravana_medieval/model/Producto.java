package co.edu.javeriana.juego_caravana_medieval.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany (mappedBy = "producto")
    private List<StockCiudad> sotck_ciudades;

    @OneToMany (mappedBy = "producto")
    private List<StockCaravana> stock_caravanas;

    private String nombre;
    private int precio;

    public Producto() {
    }
    
    public Producto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    

    
}
