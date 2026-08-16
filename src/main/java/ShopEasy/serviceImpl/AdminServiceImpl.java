package ShopEasy.serviceImpl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ShopEasy.dto.AdminDashboardResponse;
import ShopEasy.model.OrderStatus;
import ShopEasy.model.Role;
import ShopEasy.repository.OrderRepository;
import ShopEasy.repository.ProductRepository;
import ShopEasy.repository.UserRepository;
import ShopEasy.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public AdminServiceImpl(
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository) {

        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AdminDashboardResponse getDashboard() {

        // =========================
        // CUSTOMERS
        // =========================

        long totalCustomers =
                userRepository.countByRole(Role.CUSTOMER);

        long activeCustomers =
                userRepository.countByRoleAndActiveTrue(
                        Role.CUSTOMER
                );


        // =========================
        // PRODUCTS
        // =========================

        long totalProducts =
                productRepository.count();

        long activeProducts =
                productRepository.countByActiveTrue();


        // =========================
        // ORDERS
        // =========================

        long totalOrders =
                orderRepository.count();

        long pendingOrders =
                orderRepository.countByStatus(
                        OrderStatus.PENDING
                );

        long confirmedOrders =
                orderRepository.countByStatus(
                        OrderStatus.CONFIRMED
                );

        long shippedOrders =
                orderRepository.countByStatus(
                        OrderStatus.SHIPPED
                );

        long outForDeliveryOrders =
                orderRepository.countByStatus(
                        OrderStatus.OUT_FOR_DELIVERY
                );

        long deliveredOrders =
                orderRepository.countByStatus(
                        OrderStatus.DELIVERED
                );

        long cancelledOrders =
                orderRepository.countByStatus(
                        OrderStatus.CANCELLED
                );


        // =========================
        // REVENUE
        // =========================

        BigDecimal totalRevenue =
                orderRepository.getTotalRevenue();

        if (totalRevenue == null) {
            totalRevenue = BigDecimal.ZERO;
        }


        // =========================
        // RESPONSE
        // =========================

        return new AdminDashboardResponse(

                totalCustomers,
                activeCustomers,

                totalProducts,
                activeProducts,

                totalOrders,

                pendingOrders,
                confirmedOrders,
                shippedOrders,
                outForDeliveryOrders,
                deliveredOrders,
                cancelledOrders,

                totalRevenue
        );
    }
}