package co.edu.javeriana.juego_caravana_medieval.Mapper;

import co.edu.javeriana.juego_caravana_medieval.DTO.StockCaravanaDTO;
import co.edu.javeriana.juego_caravana_medieval.model.StockCaravana;

public class StockCaravanaMapper {

    // Convertir de DTO a entidad
    public static StockCaravana toEntity(StockCaravanaDTO dto) {
        StockCaravana entity = new StockCaravana();
        entity.setId(dto.getId());
        entity.setCantidad(dto.getCantidad());
        // Nota: la asignación de caravana y producto debe hacerse en el servicio,
        // porque aquí solo contamos con los IDs en el DTO
        return entity;
    }

    // Convertir de entidad a DTO
    public static StockCaravanaDTO toDTO(StockCaravana entity) {
        StockCaravanaDTO dto = new StockCaravanaDTO();
        dto.setId(entity.getId());
        dto.setCantidad(entity.getCantidad());

        if (entity.getProducto() != null) {
            dto.setProductoId(entity.getProducto().getId());
            dto.setProductoNombre(entity.getProducto().getNombre());
        }

        if (entity.getCaravana() != null) {
            dto.setCaravanaId(entity.getCaravana().getId());
        }

        return dto;
    }
}
