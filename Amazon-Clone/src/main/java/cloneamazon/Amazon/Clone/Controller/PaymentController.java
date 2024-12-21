package cloneamazon.Amazon.Clone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cloneamazon.Amazon.Clone.Entity.Payments;
import cloneamazon.Amazon.Clone.Service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<Payments> processPayment(
            @RequestParam Long orderId,
            @RequestParam Long userId,
            @RequestParam String paymentMethod) {
        return ResponseEntity.ok(paymentService.processPayment(orderId, userId, paymentMethod));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payments>> getUserPayments(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getUserPayments(userId));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Payments>> getOrderPayments(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getOrderPayments(orderId));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payments> getPaymentDetails(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentDetails(paymentId));
    }

    @PostMapping("/{paymentId}/refund")
    public ResponseEntity<Void> refundPayment(@PathVariable Long paymentId) {
        paymentService.refundPayment(paymentId);
        return ResponseEntity.ok().build();
    }
}
