package id.muhammadfaisal.mcommerce.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseEntity {
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "deleted_at")
    private Timestamp deletedAt;
}
