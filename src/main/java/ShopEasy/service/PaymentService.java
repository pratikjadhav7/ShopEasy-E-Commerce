package ShopEasy.service;

import ShopEasy.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse getPaymentByOrderId(Long orderId);

    PaymentResponse getPaymentByTransactionId(
            String transactionId
    );
    
    PaymentResponse createPayment(
            Long orderId,
            String paymentMethod
    );
}