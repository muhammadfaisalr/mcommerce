package id.muhammadfaisal.mcommerce.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {
    private String name;
    private Long productId;
    private Long quantity;
    private Long amount;
}
