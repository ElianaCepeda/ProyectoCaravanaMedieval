package co.edu.javeriana.juego_caravana_medieval.DTO;

public class ServicioDTO {
    private Long id;
    private String nombre;
    private int precio;
    private String descripción;

    public ServicioDTO() {
    }

    public ServicioDTO(Long id, String nombre, int precio, String descripción) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripción = descripción;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    
}
