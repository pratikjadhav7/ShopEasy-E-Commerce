package ShopEasy.repository;

import ShopEasy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();

    long countByActiveTrue();
    
    List<Product> findByNameContainingIgnoreCaseAndActiveTrue(String name);

    List<Product> findByCategoryCategoryIdAndActiveTrue(Long categoryId);

    List<Product> findByNameContainingIgnoreCase(String name);
    
    List<Product> findByCategory_CategoryId(Long categoryId);
}