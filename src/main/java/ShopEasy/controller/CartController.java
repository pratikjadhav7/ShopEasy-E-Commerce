package ShopEasy.controller;

import ShopEasy.dto.AddToCartRequest;
import ShopEasy.dto.CartResponse;
import ShopEasy.dto.UpdateCartRequest;
import ShopEasy.service.CartService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // =====================================================
    // GET CART
    // =====================================================

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponse> getCart(
            @PathVariable Long userId) {

        CartResponse response =
                cartService.getCart(userId);

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // ADD PRODUCT TO CART
    // =====================================================

    @PostMapping("/{userId}/add")
    public ResponseEntity<CartResponse> addToCart(
            @PathVariable Long userId,
            @Valid @RequestBody AddToCartRequest request) {

        CartResponse response =
                cartService.addToCart(userId, request);

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // UPDATE CART ITEM QUANTITY
    // =====================================================

    @PutMapping("/{userId}/product/{productId}")
    public ResponseEntity<CartResponse> updateCartItem(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @Valid @RequestBody UpdateCartRequest request) {

        CartResponse response =
                cartService.updateCartItem(
                        userId,
                        productId,
                        request
                );

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // REMOVE PRODUCT FROM CART
    // =====================================================

    @DeleteMapping("/{userId}/product/{productId}")
    public ResponseEntity<String> removeFromCart(
            @PathVariable Long userId,
            @PathVariable Long productId) {

        cartService.removeFromCart(userId, productId);

        return ResponseEntity.ok(
                "Product removed from cart successfully"
        );
    }

    // =====================================================
    // CLEAR CART
    // =====================================================

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<String> clearCart(
            @PathVariable Long userId) {

        cartService.clearCart(userId);

        return ResponseEntity.ok(
                "Cart cleared successfully"
        );
    }
}