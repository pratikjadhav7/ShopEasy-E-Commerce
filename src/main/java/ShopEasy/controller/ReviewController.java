package ShopEasy.controller;

import ShopEasy.dto.ReviewRequest;
import ShopEasy.dto.ReviewResponse;
import ShopEasy.service.ReviewService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // =====================================================
    // ADD REVIEW
    // =====================================================

    @PostMapping("/user/{userId}")
    public ResponseEntity<ReviewResponse> addReview(
            @PathVariable Long userId,
            @Valid @RequestBody ReviewRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        reviewService.addReview(
                                userId,
                                request
                        )
                );
    }

    // =====================================================
    // UPDATE REVIEW
    // =====================================================

    @PutMapping("/user/{userId}/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long userId,
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewRequest request) {

        return ResponseEntity.ok(
                reviewService.updateReview(
                        userId,
                        reviewId,
                        request
                )
        );
    }

    // =====================================================
    // DELETE REVIEW
    // =====================================================

    @DeleteMapping("/user/{userId}/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable Long userId,
            @PathVariable Long reviewId) {

        reviewService.deleteReview(
                userId,
                reviewId
        );

        return ResponseEntity.ok(
                "Review deleted successfully"
        );
    }

    // =====================================================
    // GET ALL REVIEWS OF PRODUCT
    // =====================================================

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponse>> getProductReviews(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                reviewService.getProductReviews(productId)
        );
    }

    // =====================================================
    // GET USER'S REVIEW FOR PRODUCT
    // =====================================================

    @GetMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<ReviewResponse> getUserProductReview(
            @PathVariable Long userId,
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                reviewService.getUserProductReview(
                        userId,
                        productId
                )
        );
    }
}