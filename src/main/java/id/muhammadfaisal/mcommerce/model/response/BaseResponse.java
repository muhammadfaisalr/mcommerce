package id.muhammadfaisal.mcommerce.model.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseResponse {
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
}
