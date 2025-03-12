package co.edu.javeriana.juego_caravana_medieval.DTO;

public class CiudadDTO {
    
    private Long id;
    private String nombre;
    private int tarifa;

    public CiudadDTO() {
    }

    public CiudadDTO(Long id, String nombre, int tarifa) {
        this.id = id;
        this.nombre = nombre;
        this.tarifa = tarifa;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

}
