package id.muhammadfaisal.mcommerce.model.response;

import id.muhammadfaisal.mcommerce.entity.master.InventoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse extends BaseResponse {
    private Long id;
    private String name;
    private Long price;
    private Long stock;
    private String description;

    public InventoryResponse mapper(InventoryEntity entity) {
        return InventoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .description(entity.getDescription())
                .build();
    }
}
