package id.muhammadfaisal.mcommerce.model.response;

import id.muhammadfaisal.mcommerce.entity.transaction.OrderDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderByInvoiceResponse extends BaseResponse {
    private Long id;
    private String invoiceNumber;
    private Long total;
    private List<OrderDetailEntity> orderItems = new ArrayList<>();
}
