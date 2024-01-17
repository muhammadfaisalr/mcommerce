package id.muhammadfaisal.mcommerce.service;

import id.muhammadfaisal.mcommerce.entity.transaction.OrderEntity;
import id.muhammadfaisal.mcommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity placeOrder(OrderEntity orderEntity){
        orderEntity.setUpdatedAt(Timestamp.from(Instant.now()));
        return orderRepository.save(orderEntity);
    }

    public Optional<OrderEntity> getOrderByInvoiceNumber(String invoiceNumber) {
        return orderRepository.findByInvoiceNo(invoiceNumber);
    }
}
