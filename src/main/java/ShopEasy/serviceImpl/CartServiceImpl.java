package ShopEasy.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ShopEasy.dto.AddToCartRequest;
import ShopEasy.dto.CartItemResponse;
import ShopEasy.dto.CartResponse;
import ShopEasy.dto.UpdateCartRequest;
import ShopEasy.model.Cart;
import ShopEasy.model.CartItem;
import ShopEasy.model.Product;
import ShopEasy.model.User;
import ShopEasy.repository.CartItemRepository;
import ShopEasy.repository.CartRepository;
import ShopEasy.repository.ProductRepository;
import ShopEasy.repository.UserRepository;
import ShopEasy.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // =====================================================
    // GET CART
    // =====================================================

    @Override
    @Transactional
    public CartResponse getCart(Long userId) {

        Cart cart = getOrCreateCart(userId);

        return mapToResponse(cart);
    }

    // =====================================================
    // ADD TO CART
    // =====================================================

    @Override
    @Transactional
    public CartResponse addToCart(
            Long userId,
            AddToCartRequest request) {

        Cart cart = getOrCreateCart(userId);

        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found")
                );

        if (!product.isActive()) {
            throw new RuntimeException(
                    "Product is not available"
            );
        }

        if (product.getStockQuantity()
                < request.getQuantity()) {

            throw new RuntimeException(
                    "Insufficient stock"
            );
        }

        var existingItem =
                cartItemRepository
                        .findByCartCartIdAndProductProductId(
                                cart.getCartId(),
                                product.getProductId()
                        );

        if (existingItem.isPresent()) {

            CartItem item = existingItem.get();

            int newQuantity =
                    item.getQuantity()
                            + request.getQuantity();

            if (newQuantity > product.getStockQuantity()) {

                throw new RuntimeException(
                        "Insufficient stock"
                );
            }

            item.setQuantity(newQuantity);

            cartItemRepository.save(item);

        } else {

            CartItem item = new CartItem();

            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(request.getQuantity());

            cartItemRepository.save(item);
        }

        return mapToResponse(cart);
    }

    // =====================================================
    // UPDATE CART ITEM
    // =====================================================

    @Override
    @Transactional
    public CartResponse updateCartItem(
            Long userId,
            Long productId,
            UpdateCartRequest request) {

        Cart cart = getOrCreateCart(userId);

        CartItem item = cartItemRepository
                .findByCartCartIdAndProductProductId(
                        cart.getCartId(),
                        productId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found in cart"
                        )
                );

        Product product = item.getProduct();

        if (!product.isActive()) {
            throw new RuntimeException(
                    "Product is not available"
            );
        }

        if (request.getQuantity()
                > product.getStockQuantity()) {

            throw new RuntimeException(
                    "Insufficient stock"
            );
        }

        item.setQuantity(request.getQuantity());

        cartItemRepository.save(item);

        return mapToResponse(cart);
    }

    // =====================================================
    // REMOVE FROM CART
    // =====================================================

    @Override
    @Transactional
    public void removeFromCart(
            Long userId,
            Long productId) {

        Cart cart = getOrCreateCart(userId);

        CartItem item = cartItemRepository
                .findByCartCartIdAndProductProductId(
                        cart.getCartId(),
                        productId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found in cart"
                        )
                );

        cartItemRepository.delete(item);
    }

    // =====================================================
    // CLEAR CART
    // =====================================================

    @Override
    @Transactional
    public void clearCart(Long userId) {

        Cart cart = getOrCreateCart(userId);

        if (cart.getItems() != null) {

            for (CartItem item :
                    new ArrayList<>(cart.getItems())) {

                cartItemRepository.delete(item);
            }

            cart.getItems().clear();
        }
    }

    // =====================================================
    // GET OR CREATE CART
    // =====================================================

    private Cart getOrCreateCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return cartRepository.findByUser(user)
                .orElseGet(() -> {

                    Cart cart = new Cart();

                    cart.setUser(user);
                    cart.setItems(new ArrayList<>());

                    return cartRepository.save(cart);
                });
    }

    // =====================================================
    // MAP CART TO RESPONSE
    // =====================================================

    private CartResponse mapToResponse(Cart cart) {

        CartResponse response = new CartResponse();

        response.setCartId(cart.getCartId());

        BigDecimal total = BigDecimal.ZERO;

        /*
         * Important:
         * Cart ke in-memory items collection par depend
         * nahi karenge.
         *
         * Database se actual CartItems fetch karenge.
         */
        List<CartItem> cartItems =
                cartItemRepository
                        .findByCartCartId(cart.getCartId());

        for (CartItem item : cartItems) {

            Product product = item.getProduct();

            BigDecimal subtotal =
                    product.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            item.getQuantity()
                                    )
                            );

            CartItemResponse itemResponse =
                    new CartItemResponse(
                            item.getCartItemId(),
                            product.getProductId(),
                            product.getName(),
                            product.getImageUrl(),
                            product.getPrice(),
                            item.getQuantity(),
                            subtotal
                    );

            response.getItems().add(itemResponse);

            total = total.add(subtotal);
        }

        response.setTotalAmount(total);

        return response;
    }
}