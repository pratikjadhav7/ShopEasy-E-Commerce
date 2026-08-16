package ShopEasy.service;

import ShopEasy.dto.ProductRequest;
import ShopEasy.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest request);

    ProductResponse getProductById(Long productId);

    List<ProductResponse> getAllProducts();

    List<ProductResponse> getAllActiveProducts();

    List<ProductResponse> searchProducts(String keyword);

    List<ProductResponse> getProductsByCategory(Long categoryId);

    ProductResponse updateProduct(
            Long productId,
            ProductRequest request
    );

    void deleteProduct(Long productId);
}