package ShopEasy.serviceImpl;

import ShopEasy.dto.CreateOrderRequest;
import ShopEasy.dto.OrderItemResponse;
import ShopEasy.dto.OrderResponse;
import ShopEasy.model.*;
import ShopEasy.repository.*;
import ShopEasy.service.OrderService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PaymentRepository paymentRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            UserRepository userRepository,
            AddressRepository addressRepository,
            PaymentRepository paymentRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(
            Long userId,
            CreateOrderRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        Address address = addressRepository
                .findById(request.getShippingAddressId())
                .orElseThrow(() ->
                        new RuntimeException("Address not found")
                );

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Cart is empty")
                );

        if (cart.getItems() == null
                || cart.getItems().isEmpty()) {

            throw new RuntimeException("Cart is empty");
        }

        BigDecimal total = BigDecimal.ZERO;

        // =====================================================
        // VALIDATE CART ITEMS + CALCULATE TOTAL FIRST
        // =====================================================

        for (CartItem cartItem : cart.getItems()) {

            Product product = cartItem.getProduct();

            if (!product.isActive()) {

                throw new RuntimeException(
                        "Product is no longer available: "
                                + product.getName()
                );
            }

            if (product.getStockQuantity()
                    < cartItem.getQuantity()) {

                throw new RuntimeException(
                        "Insufficient stock for: "
                                + product.getName()
                );
            }

            BigDecimal itemTotal =
                    product.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            cartItem.getQuantity()
                                    )
                            );

            total = total.add(itemTotal);
        }

        // =====================================================
        // CREATE ORDER
        // =====================================================

        Order order = new Order();

        order.setUser(user);
        order.setShippingAddress(address);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        // IMPORTANT:
        // totalAmount must be set BEFORE save()
        order.setTotalAmount(total);

        Order savedOrder =
                orderRepository.save(order);



     // =====================================================
     // CREATE PAYMENT
     // =====================================================

     Payment payment = new Payment();

     payment.setOrder(savedOrder);
     payment.setAmount(savedOrder.getTotalAmount());

     // Payment method selected by customer
     payment.setPaymentMethod(
             request.getPaymentMethod()
     );

     payment.setStatus(PaymentStatus.PENDING);

     // Demo transaction/reference ID
     String prefix =
             "COD".equalsIgnoreCase(request.getPaymentMethod())
                     ? "COD-"
                     : "PAY-";

     payment.setTransactionId(
             prefix + System.currentTimeMillis()
     );

     paymentRepository.save(payment);
        List<OrderItem> orderItems =
                new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            Product product = cartItem.getProduct();

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(
                    cartItem.getQuantity()
            );

            // Store current price permanently
            orderItem.setPriceAtPurchase(
                    product.getPrice()
            );

            orderItems.add(orderItem);

            // =================================================
            // REDUCE STOCK
            // =================================================

            product.setStockQuantity(
                    product.getStockQuantity()
                            - cartItem.getQuantity()
            );
        }

        orderItemRepository.saveAll(orderItems);

        // =====================================================
        // CLEAR CART
        // =====================================================

        for (CartItem item :
                new ArrayList<>(cart.getItems())) {

            cartItemRepository.delete(item);
        }

        cart.getItems().clear();

        return mapToResponse(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found")
                );

        return mapToResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getUserOrders(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return orderRepository
                .findByUserOrderByOrderDateDesc(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByStatus(
            String status) {

        OrderStatus orderStatus;

        try {
            orderStatus =
                    OrderStatus.valueOf(
                            status.toUpperCase()
                    );
        } catch (IllegalArgumentException ex) {

            throw new RuntimeException(
                    "Invalid order status"
            );
        }

        return orderRepository
                .findByStatus(orderStatus)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponse cancelOrder(
            Long userId,
            Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found")
                );

        if (!order.getUser().getUserId().equals(userId)) {

            throw new RuntimeException(
                    "You cannot cancel this order"
            );
        }

        if (order.getStatus() != OrderStatus.PENDING) {

            throw new RuntimeException(
                    "Only pending orders can be cancelled"
            );
        }

        order.setStatus(OrderStatus.CANCELLED);

        return mapToResponse(
                orderRepository.save(order)
        );
    }

    private OrderResponse mapToResponse(Order order) {

        OrderResponse response = new OrderResponse();

        // =====================================================
        // ORDER DETAILS
        // =====================================================

        response.setOrderId(order.getOrderId());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus());
        response.setOrderDate(order.getOrderDate());


        // =====================================================
        // CUSTOMER DETAILS
        // =====================================================

        if (order.getUser() != null) {

            response.setUserId(
                    order.getUser().getUserId()
            );

            response.setCustomerName(
                    order.getUser().getName()
            );

            response.setCustomerPhone(
                    order.getUser().getPhone()
            );
        }


        // =====================================================
        // SHIPPING ADDRESS
        // =====================================================

        if (order.getShippingAddress() != null) {

            response.setShippingAddressId(
                    order.getShippingAddress().getAddressId()
            );
        }


        // =====================================================
        // ORDER ITEMS
        // =====================================================

        List<OrderItemResponse> itemResponses =
                new ArrayList<>();

        List<OrderItem> items =
                orderItemRepository
                        .findByOrderOrderId(
                                order.getOrderId()
                        );

        for (OrderItem item : items) {

            Product product = item.getProduct();

            BigDecimal subtotal =
                    item.getPriceAtPurchase()
                            .multiply(
                                    BigDecimal.valueOf(
                                            item.getQuantity()
                                    )
                            );

            itemResponses.add(
                    new OrderItemResponse(
                            item.getOrderItemId(),
                            product.getProductId(),
                            product.getName(),
                            product.getImageUrl(),
                            item.getQuantity(),
                            item.getPriceAtPurchase(),
                            subtotal
                    )
            );
        }

        response.setItems(itemResponses);

        return response;
    }

    @Override
    @Transactional
    public OrderResponse updateOrderStatus(
            Long orderId,
            String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found")
                );

        try {
            OrderStatus newStatus =
                    OrderStatus.valueOf(
                            status.trim().toUpperCase()
                    );

            order.setStatus(newStatus);

        } catch (IllegalArgumentException e) {

            throw new RuntimeException(
                    "Invalid order status: " + status
            );
        }

        Order savedOrder =
                orderRepository.save(order);

        return mapToResponse(savedOrder);
    }

    @Override
    @Transactional
    public void clearOrderHistory(Long userId) {

        List<Order> orders =
                orderRepository.findByUserUserIdOrderByOrderDateDesc(userId);

        if (orders == null || orders.isEmpty()) {
            return;
        }

        orderRepository.deleteAll(orders);
    }
}