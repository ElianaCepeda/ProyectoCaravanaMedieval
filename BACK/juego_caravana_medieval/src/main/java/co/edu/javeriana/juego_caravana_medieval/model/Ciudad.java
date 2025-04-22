package co.edu.javeriana.juego_caravana_medieval.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private int tarifa;

    // Coordenadas fijas en el mapa
    private int x;
    private int y;

    @OneToMany(mappedBy = "ciudad_origen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ruta> rutas_salida = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad_destino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ruta> rutas_llegada = new ArrayList<>();

    @ManyToMany(mappedBy = "ciudades")
    private List<Servicio> servicios = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad")
    private List<StockCiudad> stock = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad")
    private List<Caravana> caravanas = new ArrayList<>();

    // Constructor por defecto (requerido por JPA)
    public Ciudad() {
    }

    // Constructor original (sin coordenadas)
    public Ciudad(String nombre, int tarifa) {
        this.nombre = nombre;
        this.tarifa = tarifa;
    }

    // Nuevo constructor con coordenadas
    public Ciudad(String nombre, int tarifa, int x, int y) {
        this.nombre = nombre;
        this.tarifa = tarifa;
        this.x = x;
        this.y = y;
    }

    // Getters / Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTarifa() {
        return tarifa;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Devuelve todas las rutas (salida + llegada)
    public List<Ruta> getRutas() {
        List<Ruta> todas = new ArrayList<>(rutas_salida);
        todas.addAll(rutas_llegada);
        return todas;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    // (Opcional) getters para relaciones si los necesitas
    public List<Ruta> getRutasSalida() {
        return rutas_salida;
    }

    public List<Ruta> getRutasLlegada() {
        return rutas_llegada;
    }

    public List<StockCiudad> getStock() {
        return stock;
    }

    public List<Caravana> getCaravanas() {
        return caravanas;
    }
}
