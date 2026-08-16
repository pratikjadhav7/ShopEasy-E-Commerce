package ShopEasy.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ShopEasy.dto.AdminCategoryRequest;
import ShopEasy.dto.CategoryResponse;
import ShopEasy.model.Category;
import ShopEasy.repository.CategoryRepository;
import ShopEasy.service.AdminCategoryService;

@Service
public class AdminCategoryServiceImpl
        implements AdminCategoryService {

    private final CategoryRepository categoryRepository;

    public AdminCategoryServiceImpl(
            CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryResponse addCategory(
            AdminCategoryRequest request) {

        if (categoryRepository
                .existsByNameIgnoreCase(request.getName())) {

            throw new RuntimeException(
                    "Category already exists"
            );
        }

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category saved =
                categoryRepository.save(category);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(
            Long categoryId) {

        Category category =
                categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        )
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
            AdminCategoryRequest request) {

        Category category =
                categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        )
                );

        if (!category.getName()
                .equalsIgnoreCase(request.getName())
                && categoryRepository
                    .existsByNameIgnoreCase(
                            request.getName())) {

            throw new RuntimeException(
                    "Category name already exists"
            );
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return mapToResponse(
                categoryRepository.save(category)
        );
    }

    @Override
    @Transactional
    public void deleteCategory(
            Long categoryId) {

        Category category =
                categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        )
                );

        if (category.getProducts() != null
                && !category.getProducts().isEmpty()) {

            throw new RuntimeException(
                    "Cannot delete category because products exist"
            );
        }

        categoryRepository.delete(category);
    }

    private CategoryResponse mapToResponse(Category category) {

        return new CategoryResponse(
                category.getCategoryId(),
                category.getName(),
                category.getDescription(),
                category.getImageUrl()
        );
    }
}