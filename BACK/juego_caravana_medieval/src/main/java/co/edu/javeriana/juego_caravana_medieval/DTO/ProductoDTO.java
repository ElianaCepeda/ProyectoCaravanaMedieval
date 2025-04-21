package co.edu.javeriana.juego_caravana_medieval.DTO;

public class ProductoDTO {
    private Long id;
    private String nombre;
    private int precio;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombre, int precio) {
        this.id = id;
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
