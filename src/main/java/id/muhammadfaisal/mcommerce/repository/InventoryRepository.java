package id.muhammadfaisal.mcommerce.repository;

import id.muhammadfaisal.mcommerce.entity.master.InventoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    @Query(value = "SELECT * FROM m_inventory WHERE deleted_at IS NULL", nativeQuery = true)
    List<InventoryEntity> findAllByDeletedAtIsNull();
    @Query(value = "UPDATE m_inventory SET stock = stock - ?2, updated_at = now() WHERE id = ?1", nativeQuery = true)
    @Transactional
    @Modifying
    int updateStock(Long id, Integer quantity);

    @Query(value = "SELECT * FROM m_inventory WHERE id = ?1", nativeQuery = true)
    InventoryEntity getById(Long id);
}
