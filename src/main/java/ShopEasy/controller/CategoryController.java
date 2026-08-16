package ShopEasy.controller;

import ShopEasy.dto.CategoryRequest;
import ShopEasy.dto.CategoryResponse;
import ShopEasy.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // =====================================================
    // ADD CATEGORY
    // =====================================================

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse response =
                categoryService.addCategory(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // =====================================================
    // GET ALL CATEGORIES
    // =====================================================

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {

        List<CategoryResponse> categories =
                categoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }

    // =====================================================
    // GET CATEGORY BY ID
    // =====================================================

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable Long categoryId) {

        CategoryResponse response =
                categoryService.getCategoryById(categoryId);

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // SEARCH CATEGORY
    // =====================================================

    @GetMapping("/search")
    public ResponseEntity<List<CategoryResponse>> searchCategories(
            @RequestParam String keyword) {

        List<CategoryResponse> categories =
                categoryService.searchCategories(keyword);

        return ResponseEntity.ok(categories);
    }

    // =====================================================
    // UPDATE CATEGORY
    // =====================================================

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse response =
                categoryService.updateCategory(categoryId, request);

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // DELETE CATEGORY
    // =====================================================

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long categoryId) {

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok(
                "Category deleted successfully"
        );
    }
}