package id.muhammadfaisal.mcommerce.entity.master;

import id.muhammadfaisal.mcommerce.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_inventory_seq")
    @SequenceGenerator(name = "m_inventory_seq_gen", sequenceName = "m_inventory_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;
}


