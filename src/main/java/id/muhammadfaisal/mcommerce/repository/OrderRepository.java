package id.muhammadfaisal.mcommerce.repository;

import id.muhammadfaisal.mcommerce.entity.transaction.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByInvoiceNo(String invoiceNumber);
}
