package cloneamazon.Amazon.Clone.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import cloneamazon.Amazon.Clone.Entity.OrderItem;

import cloneamazon.Amazon.Clone.Service.OrderItemService;
@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/add")
    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(orderItemService.addOrderItem(orderItem));
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> removeOrderItem(@PathVariable Long orderItemId) {
        orderItemService.removeOrderItem(orderItemId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderItemService.getOrderItemsByOrderId(orderId));
    }
}