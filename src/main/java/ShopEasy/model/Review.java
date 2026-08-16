package ShopEasy.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "reviews",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_user_product_review",
            columnNames = {
                "user_id",
                "product_id"
            }
        )
    }
)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "product_id"
    )
    private Product product;


    @Column(nullable = false)
    private Integer rating;


    @Column(length = 1000)
    private String comment;


    @Column(nullable = false, updatable = false)
    private LocalDateTime reviewDate;


    @PrePersist
    protected void onCreate() {

        reviewDate = LocalDateTime.now();
    }


    public Review() {
    }


    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }


	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}

    


}