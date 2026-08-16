package ShopEasy.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ShopEasy.model.Order;
import ShopEasy.model.OrderStatus;
import ShopEasy.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserOrderByOrderDateDesc(User user);

    List<Order> findByUserUserIdOrderByOrderDateDesc(Long userId);

    List<Order> findByStatus(OrderStatus status);
    
    long countByStatus(OrderStatus status);

    @Query("""
            SELECT COALESCE(SUM(o.totalAmount), 0)
            FROM Order o
            WHERE o.status <> ShopEasy.model.OrderStatus.CANCELLED
           """)
    BigDecimal getTotalRevenue();
}