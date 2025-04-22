package co.edu.javeriana.juego_caravana_medieval.DTO;

public class CiudadDTO {
    
    private Long id;
    private String nombre;
    private int tarifa;
    private int x;
    private int y;

    public CiudadDTO() {
    }

    public CiudadDTO(Long id, String nombre, int tarifa, int x, int y) {
        this.id = id;
        this.nombre = nombre;
        this.tarifa = tarifa;
        this.x = x;
        this.y = y;
    }

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
}
