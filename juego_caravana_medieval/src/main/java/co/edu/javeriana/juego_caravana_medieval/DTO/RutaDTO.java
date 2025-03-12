package co.edu.javeriana.juego_caravana_medieval.DTO;

public class RutaDTO {
    private Long id;
    private int cantidad_dano;
    private String descripcion_dano;

    public RutaDTO() {
    }

    public RutaDTO(Long id, int cantidad_dano, String descripcion_dano) {
        this.id = id;
        this.cantidad_dano = cantidad_dano;
        this.descripcion_dano = descripcion_dano;
    }

    public Long getId() {
        return id;
    }

    public int getCantidad_dano() {
        return cantidad_dano;
    }

    public String getDescripcion_dano() {
        return descripcion_dano;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCantidad_dano(int cantidad_dano) {
        this.cantidad_dano = cantidad_dano;
    }

    public void setDescripcion_dano(String descripcion_dano) {
        this.descripcion_dano = descripcion_dano;
    }

    
}
