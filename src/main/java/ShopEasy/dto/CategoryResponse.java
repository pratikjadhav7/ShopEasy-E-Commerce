package ShopEasy.dto;

public class CategoryResponse {

    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;

    
    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public CategoryResponse() {
    }

    public CategoryResponse(
            Long categoryId,
            String name,
            String description,
            String imageUrl
    ) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageUrl=imageUrl;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}