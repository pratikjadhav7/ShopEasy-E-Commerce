package ShopEasy.service;

import java.util.List;

import ShopEasy.dto.AdminCategoryRequest;
import ShopEasy.dto.CategoryResponse;

public interface AdminCategoryService {

    CategoryResponse addCategory(
            AdminCategoryRequest request
    );

    CategoryResponse getCategoryById(
            Long categoryId
    );

    List<CategoryResponse> getAllCategories();

    CategoryResponse updateCategory(
            Long categoryId,
            AdminCategoryRequest request
    );

    void deleteCategory(
            Long categoryId
    );
}