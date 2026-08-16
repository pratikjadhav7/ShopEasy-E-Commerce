package ShopEasy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import ShopEasy.dto.AdminProductRequest;
import ShopEasy.dto.ProductResponse;
import ShopEasy.service.AdminProductService;

@RestController
@RequestMapping("/api/admin/products")
@CrossOrigin(origins = "*")
public class AdminProductController {

    private final AdminProductService adminProductService;

    public AdminProductController(
            AdminProductService adminProductService) {

        this.adminProductService =
                adminProductService;
    }

    // =========================
    // ADD PRODUCT
    // =========================

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(
            @Valid @RequestBody AdminProductRequest request) {

        ProductResponse response =
                adminProductService.addProduct(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // =========================
    // UPDATE PRODUCT
    // =========================

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody AdminProductRequest request) {

        ProductResponse response =
                adminProductService.updateProduct(
                        productId,
                        request
                );

        return ResponseEntity.ok(response);
    }


    // =========================
    // DELETE PRODUCT
    // =========================

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long productId) {

        adminProductService.deleteProduct(productId);

        return ResponseEntity.ok(
                "Product deleted successfully"
        );
    }


    // =========================
    // GET PRODUCT BY ID
    // =========================

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long productId) {

        ProductResponse response =
                adminProductService.getProductById(
                        productId
                );

        return ResponseEntity.ok(response);
    }


    // =========================
    // GET ALL PRODUCTS
    // =========================

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> products =
                adminProductService.getAllProducts();

        return ResponseEntity.ok(products);
    }


    // =========================
    // TOGGLE ACTIVE / INACTIVE
    // =========================

    @PatchMapping("/{productId}/toggle")
    public ResponseEntity<ProductResponse> toggleProductStatus(
            @PathVariable Long productId) {

        ProductResponse response =
                adminProductService.toggleProductStatus(
                        productId
                );

        return ResponseEntity.ok(response);
    }
}