package ShopEasy.service;

import ShopEasy.dto.CreateOrderRequest;
import ShopEasy.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(
            Long userId,
            CreateOrderRequest request
    );

    OrderResponse getOrderById(Long orderId);

    List<OrderResponse> getUserOrders(Long userId);

    List<OrderResponse> getAllOrders();

    List<OrderResponse> getOrdersByStatus(String status);

    OrderResponse cancelOrder(
            Long userId,
            Long orderId
    );
    
    OrderResponse updateOrderStatus(
            Long orderId,
            String status
    );
    
    void clearOrderHistory(Long userId);
}