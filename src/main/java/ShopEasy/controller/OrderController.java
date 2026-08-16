package ShopEasy.controller;

import ShopEasy.dto.CreateOrderRequest;
import ShopEasy.dto.OrderResponse;
import ShopEasy.service.OrderService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // =====================================================
    // PLACE ORDER
    // =====================================================

    @PostMapping("/user/{userId}")
    public ResponseEntity<OrderResponse> placeOrder(
            @PathVariable Long userId,
            @Valid @RequestBody CreateOrderRequest request) {

        OrderResponse response =
                orderService.placeOrder(userId, request);

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // GET ORDER BY ID
    // =====================================================

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                orderService.getOrderById(orderId)
        );
    }

    // =====================================================
    // GET USER ORDERS
    // =====================================================

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getUserOrders(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                orderService.getUserOrders(userId)
        );
    }

    // =====================================================
    // GET ALL ORDERS - ADMIN
    // =====================================================

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

        return ResponseEntity.ok(
                orderService.getAllOrders()
        );
    }

    // =====================================================
    // GET ORDERS BY STATUS - ADMIN
    // =====================================================

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                orderService.getOrdersByStatus(status)
        );
    }

    // =====================================================
    // CANCEL ORDER
    // =====================================================

    @PutMapping("/user/{userId}/{orderId}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(
            @PathVariable Long userId,
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                orderService.cancelOrder(
                        userId,
                        orderId
                )
        );
    }
    
 // =====================================================
 // CLEAR USER ORDER HISTORY
 // =====================================================

 @DeleteMapping("/user/{userId}/clear")
 public ResponseEntity<String> clearOrderHistory(
         @PathVariable Long userId) {

     orderService.clearOrderHistory(userId);

     return ResponseEntity.ok(
             "Order history cleared successfully"
     );
 }
    
 // =====================================================
 // UPDATE ORDER STATUS - ADMIN
 // =====================================================

 @PutMapping("/admin/{orderId}/status")
 public ResponseEntity<OrderResponse> updateOrderStatus(
         @PathVariable Long orderId,
         @RequestParam String status) {

     return ResponseEntity.ok(
             orderService.updateOrderStatus(
                     orderId,
                     status
             )
     );
 }
}