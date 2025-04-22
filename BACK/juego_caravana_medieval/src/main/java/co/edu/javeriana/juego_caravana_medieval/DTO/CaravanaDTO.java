package co.edu.javeriana.juego_caravana_medieval.DTO;

public class CaravanaDTO {
    private Long id;
    private String nombre;
    private int velocidad_actual;
    private int velocidad_maxima;
    private int capacidad_actual;
    private int capacidad_maxima;
    private int dinero;
    private int vidas;
    private boolean guardias=false;

    public CaravanaDTO() {
    }

    public CaravanaDTO(Long id, String nombre, int velocidad_actual, int velocidad_maxima, int capacidad_actual, int capacidad_maxima, int dinero, int vidas, boolean guardias) {
        this.id = id;
        this.nombre = nombre;
        this.velocidad_actual = velocidad_actual;
        this.velocidad_maxima = velocidad_maxima;
        this.capacidad_actual = capacidad_actual;
        this.capacidad_maxima = capacidad_maxima;
        this.dinero = dinero;
        this.vidas = vidas;
        this.guardias = guardias;
    }

    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public int getVelocidad_actual() {
        return velocidad_actual;
    }
    public int getVelocidad_maxima() {
        return velocidad_maxima;
    }
    public int getCapacidad_actual() {
        return capacidad_actual;
    }
    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }
    public int getDinero() {
        return dinero;
    }
    public int getVidas() {
        return vidas;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setVelocidad_actual(int velocidad_actual) {
        this.velocidad_actual = velocidad_actual;
    }
    public void setVelocidad_maxima(int velocidad_maxima) {
        this.velocidad_maxima = velocidad_maxima;
    }
    public void setCapacidad_actual(int capacidad_actual) {
        this.capacidad_actual = capacidad_actual;
    }
    public void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
    }
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public boolean isGuardias() {
        return guardias;
    }
    public void setGuardias(boolean guardias) {
        this.guardias = guardias;

    }

}
