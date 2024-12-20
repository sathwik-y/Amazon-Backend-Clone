package cloneamazon.Amazon.Clone.Service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloneamazon.Amazon.Clone.Entity.Order;
import cloneamazon.Amazon.Clone.Entity.Payments;
import cloneamazon.Amazon.Clone.Repository.PaymentRepo;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    
    @Autowired
    private OrderService orderService;

    public Payments processPayment(Long orderId, Long userId, String paymentMethod) {
        Order order = orderService.getOrderById(orderId);
        
        Payments payment = new Payments();
        payment.setOrder(order);
        payment.setUser(order.getUser());
        payment.setAmount(orderService.calculateOrderTotal(orderId));
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus("COMPLETED");
        
        order.setOrderStatus("PAID");
        return paymentRepo.save(payment);
    }

    public List<Payments> getUserPayments(Long userId) {
        return paymentRepo.findByUser_UserId(userId);
    }

    public List<Payments> getOrderPayments(Long orderId) {
        return paymentRepo.findByOrder_OrderId(orderId);
    }

    public Payments getPaymentDetails(Long paymentId) {
        return paymentRepo.findById(paymentId)
            .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public void refundPayment(Long paymentId) {
        Payments payment = getPaymentDetails(paymentId);
        payment.setPaymentStatus("REFUNDED");
        paymentRepo.save(payment);
    }
}
