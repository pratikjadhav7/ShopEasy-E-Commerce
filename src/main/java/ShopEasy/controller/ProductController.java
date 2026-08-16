package ShopEasy.controller;

import ShopEasy.dto.ProductRequest;
import ShopEasy.dto.ProductResponse;
import ShopEasy.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // =====================================================
    // ADD PRODUCT
    // =====================================================

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response =
                productService.addProduct(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // =====================================================
    // GET ALL PRODUCTS
    // =====================================================

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> products =
                productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    // =====================================================
    // GET ALL ACTIVE PRODUCTS
    // =====================================================

    @GetMapping("/active")
    public ResponseEntity<List<ProductResponse>> getAllActiveProducts() {

        List<ProductResponse> products =
                productService.getAllActiveProducts();

        return ResponseEntity.ok(products);
    }

    // =====================================================
    // GET PRODUCT BY ID
    // =====================================================

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long productId) {

        ProductResponse response =
                productService.getProductById(productId);

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // SEARCH PRODUCTS
    // =====================================================

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam String keyword) {

        List<ProductResponse> products =
                productService.searchProducts(keyword);

        return ResponseEntity.ok(products);
    }

    // =====================================================
    // GET PRODUCTS BY CATEGORY
    // =====================================================

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(
            @PathVariable Long categoryId) {

        List<ProductResponse> products =
                productService.getProductsByCategory(categoryId);

        return ResponseEntity.ok(products);
    }

    // =====================================================
    // UPDATE PRODUCT
    // =====================================================

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response =
                productService.updateProduct(productId, request);

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // DELETE PRODUCT
    // =====================================================

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.ok(
                "Product deleted successfully"
        );
    }
}