package co.edu.javeriana.juego_caravana_medieval.DTO;

public class StockCaravanaDTO {
    private Long id;
    private int cantidad;

    // Nuevos campos para exponer producto y caravana
    private Long productoId;
    private String productoNombre;
    private Long caravanaId;

    public StockCaravanaDTO() {
    }

    public StockCaravanaDTO(Long id, int cantidad, Long productoId, String productoNombre, Long caravanaId) {
        this.id = id;
        this.cantidad = cantidad;
        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.caravanaId = caravanaId;
    }

    // Getters y Setters

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

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Long getCaravanaId() {
        return caravanaId;
    }

    public void setCaravanaId(Long caravanaId) {
        this.caravanaId = caravanaId;
    }
}
