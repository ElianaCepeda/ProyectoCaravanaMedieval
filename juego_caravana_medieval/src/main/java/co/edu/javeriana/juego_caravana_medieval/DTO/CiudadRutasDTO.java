package co.edu.javeriana.juego_caravana_medieval.DTO;

import java.util.List;

public class CiudadRutasDTO {
    private long idCiudad;
    private List<Long> rutasID;
    

    public CiudadRutasDTO() {
    }

    public CiudadRutasDTO(long idCiudad, List<Long> rutasID) {
        this.idCiudad = idCiudad;
        this.rutasID = rutasID;
    }

    public long getIdCiudad() {
        return idCiudad;
    }

    public List<Long> getRutasID() {
        return rutasID;
    }

    public void setIdCiudad(long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public void setRutasID(List<Long> rutasID) {
        this.rutasID = rutasID;
    }

    
}