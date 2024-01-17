package id.muhammadfaisal.mcommerce.model.request;

import lombok.Data;

@Data
public class UpdateInventoryRequest {
    private Long id;
    private String name;
    private Long price;
    private Long stock;
    private String description;
}
