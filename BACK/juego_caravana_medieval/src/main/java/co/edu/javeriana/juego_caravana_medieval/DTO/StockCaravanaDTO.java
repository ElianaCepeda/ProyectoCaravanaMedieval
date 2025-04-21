package co.edu.javeriana.juego_caravana_medieval.DTO;

public class StockCaravanaDTO {
    private Long id;
 
    private int cantidad;

    public StockCaravanaDTO() {
    }

    public StockCaravanaDTO(Long id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;

    }



}
