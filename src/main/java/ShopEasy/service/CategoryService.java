package ShopEasy.service;

import ShopEasy.dto.CategoryRequest;
import ShopEasy.dto.CategoryResponse;
import java.util.List;

public interface CategoryService {

    CategoryResponse addCategory(CategoryRequest request);

    CategoryResponse getCategoryById(Long categoryId);

    List<CategoryResponse> getAllCategories();

    CategoryResponse updateCategory(
            Long categoryId,
            CategoryRequest request
    );

    void deleteCategory(Long categoryId);

	List<CategoryResponse> searchCategories(String keyword);
}