package ShopEasy.service;

import ShopEasy.dto.ReviewRequest;
import ShopEasy.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {

    ReviewResponse addReview(
            Long userId,
            ReviewRequest request
    );

    ReviewResponse updateReview(
            Long userId,
            Long reviewId,
            ReviewRequest request
    );

    void deleteReview(
            Long userId,
            Long reviewId
    );

    List<ReviewResponse> getProductReviews(
            Long productId
    );

    ReviewResponse getUserProductReview(
            Long userId,
            Long productId
    );
}