package id.muhammadfaisal.mcommerce.controller;

import id.muhammadfaisal.mcommerce.entity.master.InventoryEntity;
import id.muhammadfaisal.mcommerce.model.request.UpdateInventoryRequest;
import id.muhammadfaisal.mcommerce.model.request.UpdateStockRequest;
import id.muhammadfaisal.mcommerce.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryEntity> getInventories(){
        return inventoryService.getInventories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryEntity> getInventory(@PathVariable("id") Long id){
        InventoryEntity inventoryEntity = inventoryService.getInventory(id).orElse(null);

        //Check if Inventory is null, return 404 Not Found
        if (inventoryEntity == null){
            log.error("Inventory not found");
            return ResponseEntity.notFound().build();
        }

        log.info("getInventory: " + inventoryEntity);
        return ResponseEntity.ok(inventoryEntity);
    }

    @PostMapping
    public InventoryEntity save(@RequestBody InventoryEntity inventoryEntity){
        log.info("saveInventory: " + inventoryEntity.toString());

        //Update createdAt
        inventoryEntity.setCreatedAt(Timestamp.from(Instant.now()));

        return inventoryService.save(inventoryEntity);
    }

    @PostMapping("/add-stock")
    public ResponseEntity<InventoryEntity> addStock(@RequestBody UpdateStockRequest updateStockRequest){
        log.info("addStock: " + updateStockRequest.toString());
        InventoryEntity inventoryEntity = inventoryService.getInventory(updateStockRequest.getId()).orElse(null);

        //Check if Inventory is null, return 404 Not Found
        if (inventoryEntity == null){
            log.error("Inventory not found");
            return ResponseEntity.notFound().build();
        }

        //Update updatedAt and stock
        inventoryEntity.setUpdatedAt(Timestamp.from(Instant.now()));
        inventoryEntity.setStock(inventoryEntity.getStock() + updateStockRequest.getStock());

        return ResponseEntity.ok(inventoryService.save(inventoryEntity));
    }

    @PutMapping
    public ResponseEntity<InventoryEntity> update(@RequestBody UpdateInventoryRequest updateInventoryRequest){
        log.info("updateInventory: " + updateInventoryRequest.toString());
        InventoryEntity inventoryEntity = inventoryService.getInventory(updateInventoryRequest.getId()).orElse(null);

        //Check if Inventory is null, return 404 Not Found
        if (inventoryEntity == null){
            log.error("Inventory not found");
            return ResponseEntity.notFound().build();
        }

        //Update updatedAt, stock, name, and price
        inventoryEntity.setStock(updateInventoryRequest.getStock());
        inventoryEntity.setName(updateInventoryRequest.getName());
        inventoryEntity.setPrice(updateInventoryRequest.getPrice());
        inventoryEntity.setUpdatedAt(Timestamp.from(Instant.now()));
        log.info("updatedInventory: {}", inventoryEntity);
        return ResponseEntity.ok(inventoryService.save(inventoryEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InventoryEntity> delete(@PathVariable("id") Long id){
        InventoryEntity inventoryEntity = inventoryService.getInventory(id).orElse(null);

        //Check if Inventory is null, return 404 Not Found
        if (inventoryEntity == null){
            log.error("Inventory not found");
            return ResponseEntity.notFound().build();
        }

        //Update deletedAt [SOFT DELETE]
        log.info("deleteInventory: " + inventoryEntity);
        inventoryEntity.setDeletedAt(Timestamp.from(Instant.now()));
        inventoryService.save(inventoryEntity);
        return ResponseEntity.ok(inventoryEntity);
    }
}
