package ShopEasy.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ShopEasy.dto.CategoryRequest;
import ShopEasy.dto.CategoryResponse;
import ShopEasy.model.Category;
import ShopEasy.repository.CategoryRepository;
import ShopEasy.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryResponse addCategory(CategoryRequest request) {

        if (categoryRepository.existsByNameIgnoreCase(
                request.getName())) {

            throw new RuntimeException(
                    "Category already exists"
            );
        }

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setImageUrl(request.getImageUrl());

        return mapToResponse(
                categoryRepository.save(category)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found")
                );

        return mapToResponse(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(
            Long categoryId,
            CategoryRequest request) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found")
                );

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setImageUrl(request.getImageUrl());

        return mapToResponse(
                categoryRepository.save(category)
        );
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {

        if (!categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Category not found");
        }

        categoryRepository.deleteById(categoryId);
    }

    private CategoryResponse mapToResponse(Category category) {

        return new CategoryResponse(
                category.getCategoryId(),
                category.getName(),
                category.getDescription(),
                category.getImageUrl()
        );
    }

    @Override
    public List<CategoryResponse> searchCategories(String keyword) {

        List<Category> categories =
                categoryRepository.findByNameContainingIgnoreCase(keyword);

        return categories.stream()
                .map(this::mapToResponse)
                .toList();
    }
}