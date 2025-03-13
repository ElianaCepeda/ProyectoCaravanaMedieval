package co.edu.javeriana.juego_caravana_medieval.DTO;

public class RutaDTO {
    private Long id;
    private int cantidad_dano;
    private String descripcion_dano;
    private Long ciudadOrigenId;
    private Long ciudadDestinoId;
    private String ciudadOrigenNombre;
    private String ciudadDestinoNombre;

    public RutaDTO() {
    }

    public RutaDTO(Long id, int cantidad_dano, String descripcion_dano, Long ciudadOrigenId, Long ciudadDestinoId, String ciudadOrigenNombre, String ciudadDestinoNombre) {
        this.id = id;
        this.cantidad_dano = cantidad_dano;
        this.descripcion_dano = descripcion_dano;
        this.ciudadOrigenId = ciudadOrigenId;
        this.ciudadDestinoId = ciudadDestinoId;
        this.ciudadOrigenNombre = ciudadOrigenNombre;
        this.ciudadDestinoNombre = ciudadDestinoNombre;
    }

    public RutaDTO (Long id, Long ciudadOrigenId, Long ciudadDestinoId){
        this.id =id;
        this.ciudadDestinoId = ciudadDestinoId;
        this.ciudadOrigenId = ciudadOrigenId;

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

    public Long getCiudadOrigenId() {
        return ciudadOrigenId;
    }

    public Long getCiudadDestinoId() {
        return ciudadDestinoId;
    }

    public void setCiudadOrigenId(Long ciudadOrigenId) {
        this.ciudadOrigenId = ciudadOrigenId;
    }

    public void setCiudadDestinoId(Long ciudadDestinoId) {
        this.ciudadDestinoId = ciudadDestinoId;
    }

    public String getCiudadOrigenNombre() {
        return ciudadOrigenNombre;
    }

    public void setCiudadOrigenNombre(String ciudadOrigenNombre) {
        this.ciudadOrigenNombre = ciudadOrigenNombre;
    }

    public String getCiudadDestinoNombre() {
        return ciudadDestinoNombre;
    }

    public void setCiudadDestinoNombre(String ciudadDestinoNombre) {
        this.ciudadDestinoNombre = ciudadDestinoNombre;
    }



    
}
