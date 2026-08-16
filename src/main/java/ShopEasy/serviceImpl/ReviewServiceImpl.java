package ShopEasy.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ShopEasy.dto.ReviewRequest;
import ShopEasy.dto.ReviewResponse;
import ShopEasy.model.Product;
import ShopEasy.model.Review;
import ShopEasy.model.User;
import ShopEasy.repository.ProductRepository;
import ShopEasy.repository.ReviewRepository;
import ShopEasy.repository.UserRepository;
import ShopEasy.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewServiceImpl(
            ReviewRepository reviewRepository,
            UserRepository userRepository,
            ProductRepository productRepository) {

        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ReviewResponse addReview(
            Long userId,
            ReviewRequest request) {

        if (reviewRepository
                .existsByUserUserIdAndProductProductId(
                        userId,
                        request.getProductId())) {

            throw new RuntimeException(
                    "You have already reviewed this product"
            );
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found")
                );

        Review review = new Review();

        review.setUser(user);
        review.setProduct(product);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setReviewDate(LocalDateTime.now());

        return mapToResponse(
                reviewRepository.save(review)
        );
    }

    @Override
    @Transactional
    public ReviewResponse updateReview(
            Long userId,
            Long reviewId,
            ReviewRequest request) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException("Review not found")
                );

        if (!review.getUser().getUserId().equals(userId)) {

            throw new RuntimeException(
                    "You cannot update this review"
            );
        }

        if (!review.getProduct().getProductId()
                .equals(request.getProductId())) {

            throw new RuntimeException(
                    "Product cannot be changed"
            );
        }

        review.setRating(request.getRating());
        review.setComment(request.getComment());

        return mapToResponse(
                reviewRepository.save(review)
        );
    }

    @Override
    @Transactional
    public void deleteReview(
            Long userId,
            Long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException("Review not found")
                );

        if (!review.getUser().getUserId().equals(userId)) {

            throw new RuntimeException(
                    "You cannot delete this review"
            );
        }

        reviewRepository.delete(review);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getProductReviews(
            Long productId) {

        return reviewRepository
                .findByProductProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewResponse getUserProductReview(
            Long userId,
            Long productId) {

        Review review = reviewRepository
                .findByUserUserIdAndProductProductId(
                        userId,
                        productId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Review not found"
                        )
                );

        return mapToResponse(review);
    }

    private ReviewResponse mapToResponse(
            Review review) {

        return new ReviewResponse(
                review.getReviewId(),
                review.getUser().getUserId(),
                review.getUser().getName(),
                review.getProduct().getProductId(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate()
        );
    }
}