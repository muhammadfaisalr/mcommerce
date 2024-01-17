package id.muhammadfaisal.mcommerce.controller;

import id.muhammadfaisal.mcommerce.entity.transaction.OrderDetailEntity;
import id.muhammadfaisal.mcommerce.entity.transaction.OrderEntity;
import id.muhammadfaisal.mcommerce.model.request.OrderRequest;
import id.muhammadfaisal.mcommerce.model.response.OrderByInvoiceResponse;
import id.muhammadfaisal.mcommerce.model.response.OrderItemResponse;
import id.muhammadfaisal.mcommerce.model.response.PlaceOrderResponse;
import id.muhammadfaisal.mcommerce.service.InventoryService;
import id.muhammadfaisal.mcommerce.service.OrderDetailService;
import id.muhammadfaisal.mcommerce.service.OrderService;
import id.muhammadfaisal.mcommerce.util.Generate;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("OrderRequest: " + orderRequest.toString());
        log.info("Placing Order...");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCreatedAt(Timestamp.from(Instant.now()));

        //Save order first to get id
        val orderEntityCopy = orderService.placeOrder(orderEntity);
        orderEntityCopy.setInvoiceNo(Generate.invoiceNumber(orderEntityCopy.getId()));

        val placeOrderResponse = new PlaceOrderResponse();
        final AtomicLong totalAmount = new AtomicLong();
        orderRequest.getOrderDetail().forEach(orderDetail -> {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            log.info("OrderDetails: " + orderDetail.toString());
            orderDetailEntity.setCreatedAt(Timestamp.from(Instant.now()));
            orderDetailEntity.setName(orderDetail.getName());
            orderDetailEntity.setProductId(orderDetail.getProductId());
            orderDetailEntity.setQuantity(orderDetail.getQuantity());
            orderDetailEntity.setAmount(orderDetail.getAmount());
            orderDetailEntity.setCreatedAt(Timestamp.from(Instant.now()));
            orderDetailEntity.setTotalAmount(orderDetail.getAmount() * orderDetail.getQuantity());
            orderDetailEntity.setOrderId(orderEntityCopy.getId());

            //Calculate total amount
            totalAmount.addAndGet(orderDetail.getAmount() * orderDetail.getQuantity());

            val inventoryEntity = inventoryService.getInventory(orderRequest.getOrderDetail().get(0).getProductId()).orElse(null);

            //Check if Inventory is null, set response message and status
            if (inventoryEntity != null) {

                //Check if stock is enough
                if (inventoryEntity.getStock() < orderDetail.getQuantity()) {
                    placeOrderResponse.setMessage("Order Placed Failed, Because Stock is not enough");
                    placeOrderResponse.setStatus("FAILED");
                    return;
                }

                int isSuccess = inventoryService.updateStock(orderDetail.getProductId(), Math.toIntExact(orderDetail.getQuantity()));
                log.info("isSuccessUpdate: " + isSuccess);

                //Check if update stock is success
                if (isSuccess == 1) {
                    log.info("OrderDetailEntity: " + orderDetailEntity);

                    //Save order detail
                    orderDetailService.saveOrderDetail(orderDetailEntity);

                    log.info("Order Placed Successfully");
                    placeOrderResponse
                            .getOrderItems()
                            .add(OrderItemResponse
                                    .builder()
                                    .name(orderDetail.getName())
                                    .quantity(orderDetail.getQuantity())
                                    .price(orderDetail.getAmount())
                                    .build()
                            );
                }
            } else {
                //Inventory is null
                log.error("Inventory not found");
                placeOrderResponse.setMessage("Order Placed Failed, Because Items not found");
                placeOrderResponse.setStatus("FAILED");
            }
        });

        //Check if order item is empty, set response message and status
        if (!placeOrderResponse.getOrderItems().isEmpty()) {
            orderEntityCopy.setTotalBill(totalAmount.get());
            val processOrder = orderService.placeOrder(orderEntityCopy);
            log.info("Crucial Process: PROCESS ORDER====================");

            //Check if process order is not null, set response message and status
            if (processOrder != null) {
                log.info("Crucial Process [SUCCESS]: PROCESS ORDER SUCCESS====================");
                placeOrderResponse.setMessage("Order Placed Successfully");
                placeOrderResponse.setStatus("SUCCESS");
                placeOrderResponse.setInvoiceNumber(Generate.invoiceNumber(orderEntity.getId()));
            } else {
                //Process order is null
                log.info("Crucial Process [FAILED]: PROCESS ORDER FAILED====================");
                placeOrderResponse.setMessage("Order Placed Failed");
                placeOrderResponse.setStatus("FAILED");
            }
        }

        log.info("PlaceOrderResponse: " + placeOrderResponse);
        return ResponseEntity.ok(placeOrderResponse);
    }

    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<OrderByInvoiceResponse> getOrderByInvoiceNumber(@PathVariable("invoiceNumber") String invoiceNumber) {
        log.info("Get Order By Invoice Number: " + invoiceNumber);
        OrderEntity orderEntity = orderService.getOrderByInvoiceNumber(invoiceNumber).orElse(null);
        if (orderEntity == null) {
            log.error("Order not found");
            return ResponseEntity.notFound().build();
        }

        val orderByInvoiceResponse = new OrderByInvoiceResponse();
        orderByInvoiceResponse.setId(orderEntity.getId());
        orderByInvoiceResponse.setInvoiceNumber(orderEntity.getInvoiceNo());
        orderByInvoiceResponse.setTotal(orderEntity.getTotalBill());
        orderByInvoiceResponse.setCreatedAt(orderEntity.getCreatedAt());
        orderByInvoiceResponse.setUpdatedAt(orderEntity.getUpdatedAt());
        orderByInvoiceResponse.getOrderItems().addAll(orderDetailService.getOrderDetailsById(orderEntity.getId()));

        log.info("OrderByInvoiceResponse: " + orderByInvoiceResponse);
        return ResponseEntity.ok(orderByInvoiceResponse);
    }
}
