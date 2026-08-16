package ShopEasy.service;

import java.util.List;

import ShopEasy.dto.AdminProductRequest;
import ShopEasy.dto.ProductResponse;

public interface AdminProductService {

    ProductResponse addProduct(
            AdminProductRequest request
    );

    ProductResponse updateProduct(
            Long productId,
            AdminProductRequest request
    );

    void deleteProduct(
            Long productId
    );

    ProductResponse getProductById(
            Long productId
    );

    List<ProductResponse> getAllProducts();

    ProductResponse toggleProductStatus(
            Long productId
    );
}