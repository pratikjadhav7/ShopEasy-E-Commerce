package ShopEasy.repository;

import ShopEasy.model.Role;
import ShopEasy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhone(String phone);

    boolean existsByPhone(String phone);

    List<User> findByRole(Role role);
    
    List<User> findByNameContainingIgnoreCase(String name);

    List<User> findByRoleAndNameContainingIgnoreCase(Role role, String name);

    long countByRole(Role role);

    long countByRoleAndActiveTrue(Role role);

	boolean existsByEmailIgnoreCase(String email);
}