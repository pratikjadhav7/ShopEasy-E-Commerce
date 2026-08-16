package ShopEasy.service;

import ShopEasy.dto.AddToCartRequest;
import ShopEasy.dto.CartResponse;
import ShopEasy.dto.UpdateCartRequest;

public interface CartService {

    CartResponse getCart(Long userId);

    CartResponse addToCart(
            Long userId,
            AddToCartRequest request
    );

    CartResponse updateCartItem(
            Long userId,
            Long productId,
            UpdateCartRequest request
    );

    void removeFromCart(
            Long userId,
            Long productId
    );

    void clearCart(Long userId);
}