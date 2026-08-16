package ShopEasy.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ShopEasy.dto.PaymentResponse;
import ShopEasy.model.Order;
import ShopEasy.model.Payment;
import ShopEasy.model.PaymentStatus;
import ShopEasy.repository.OrderRepository;
import ShopEasy.repository.PaymentRepository;
import ShopEasy.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository) {

        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentByOrderId(
            Long orderId) {

        Payment payment = paymentRepository
                .findByOrderOrderId(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Payment not found"
                        )
                );

        return mapToResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentByTransactionId(
            String transactionId) {

        Payment payment = paymentRepository
                .findByTransactionId(transactionId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Payment not found"
                        )
                );

        return mapToResponse(payment);
    }

    @Override
    @Transactional
    public PaymentResponse createPayment(
            Long orderId,
            String paymentMethod) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found"
                        )
                );

        if (paymentRepository
                .findByOrderOrderId(orderId)
                .isPresent()) {

            throw new RuntimeException(
                    "Payment already exists for this order"
            );
        }

        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(PaymentStatus.PENDING);

        payment.setTransactionId(
                "TXN-" + System.currentTimeMillis()
        );

        Payment savedPayment =
                paymentRepository.save(payment);

        return mapToResponse(savedPayment);
    }

    private PaymentResponse mapToResponse(
            Payment payment) {

        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getOrder().getOrderId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getTransactionId(),
                payment.getPaymentDate()
        );
    }
}