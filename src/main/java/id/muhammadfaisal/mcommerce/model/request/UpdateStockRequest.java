package id.muhammadfaisal.mcommerce.model.request;

import lombok.Data;

@Data
public class UpdateStockRequest {
    private Long id;
    private Long stock;
}
