package id.muhammadfaisal.mcommerce.service;

import id.muhammadfaisal.mcommerce.entity.transaction.OrderDetailEntity;
import id.muhammadfaisal.mcommerce.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public void saveOrderDetail(OrderDetailEntity orderDetailEntity) {
        orderDetailRepository.save(orderDetailEntity);
    }

    public List<OrderDetailEntity> getOrderDetailsById(Long id) {
        return new ArrayList<>(orderDetailRepository.findAllByOrderId(id));
    }
}
