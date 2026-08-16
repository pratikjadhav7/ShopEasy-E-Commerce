package ShopEasy.repository;

import ShopEasy.model.Address;
import ShopEasy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUser(User user);

    List<Address> findByUserUserId(Long userId);
}