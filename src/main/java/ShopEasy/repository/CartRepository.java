package ShopEasy.repository;

import ShopEasy.model.Cart;
import ShopEasy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);

    Optional<Cart> findByUserUserId(Long userId);
}