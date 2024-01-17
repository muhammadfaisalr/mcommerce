package id.muhammadfaisal.mcommerce.service;

import id.muhammadfaisal.mcommerce.entity.master.InventoryEntity;
import id.muhammadfaisal.mcommerce.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryEntity> getInventories(){
        return new ArrayList<>(inventoryRepository.findAllByDeletedAtIsNull());
    }

    public Optional<InventoryEntity> getInventory(Long id){
        return inventoryRepository.findById(id);
    }

    public int updateStock(Long id, Integer quantity){
        return inventoryRepository.updateStock(id, quantity);
    }

    public InventoryEntity save(InventoryEntity inventoryEntity){
        return inventoryRepository.save(inventoryEntity);
    }
}
