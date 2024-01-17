package id.muhammadfaisal.mcommerce.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderResponse {
    private String message;
    private String status;
    private String invoiceNumber;
    private List<OrderItemResponse> orderItems = new ArrayList<>();
}
