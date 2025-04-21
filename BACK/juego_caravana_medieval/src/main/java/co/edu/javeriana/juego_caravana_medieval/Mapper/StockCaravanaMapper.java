package co.edu.javeriana.juego_caravana_medieval.Mapper;

public class StockCaravanaMapper {
    
    // Convertir de DTO a entidad
    public static co.edu.javeriana.juego_caravana_medieval.model.StockCaravana toEntity(co.edu.javeriana.juego_caravana_medieval.DTO.StockCaravanaDTO stockCaravanaDTO) {
        co.edu.javeriana.juego_caravana_medieval.model.StockCaravana stockCaravana = new co.edu.javeriana.juego_caravana_medieval.model.StockCaravana();
        stockCaravana.setId(stockCaravanaDTO.getId());
 
        stockCaravana.setCantidad(stockCaravanaDTO.getCantidad());
        return stockCaravana;
    }

    // Convertir de entidad a DTO

    public static co.edu.javeriana.juego_caravana_medieval.DTO.StockCaravanaDTO toDTO(co.edu.javeriana.juego_caravana_medieval.model.StockCaravana stockCaravana) {
        co.edu.javeriana.juego_caravana_medieval.DTO.StockCaravanaDTO stockCaravanaDTO = new co.edu.javeriana.juego_caravana_medieval.DTO.StockCaravanaDTO();
        stockCaravanaDTO.setId(stockCaravana.getId());
       
        stockCaravanaDTO.setCantidad(stockCaravana.getCantidad());
        return stockCaravanaDTO;
    }

}
