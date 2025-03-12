package co.edu.javeriana.juego_caravana_medieval.model;

import java.util.List;

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

    @OneToMany (mappedBy = "ciudad_origen")
    private  List<Ruta> rutas_salida;

    @OneToMany (mappedBy = "ciudad_destino")
    private  List<Ruta> rutas_llegada;

    @ManyToMany (mappedBy = "ciudades")
    private List<Servicio> servicios;

    @OneToMany(mappedBy = "ciudad")
    private List<StockCiudad> stock;

    @OneToMany( mappedBy = "ciudad")
    private List<Caravana> caravanas;


    private String nombre;
    private int tarifa;
    
    public Ciudad() {
    }

    public Ciudad(String nombre, int tarifa) {
        this.nombre = nombre;
        this.tarifa = tarifa;
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

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public List<Ruta> getRutas(){
        List<Ruta> rutas = rutas_salida;
        rutas.addAll(rutas_llegada);
        return rutas;
    }

    

}
