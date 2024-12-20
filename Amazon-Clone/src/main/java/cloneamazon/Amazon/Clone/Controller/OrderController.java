package cloneamazon.Amazon.Clone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cloneamazon.Amazon.Clone.Entity.Order;
import cloneamazon.Amazon.Clone.Service.OrderService;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/{userId}/cart")
    public Order placeOrderFromCart(@PathVariable("userId") Long userId){
        return orderService.placeOrder(userId);
    }

    @GetMapping("/{userId}")
    public List<Order> getUsersOrders(@PathVariable("userId") Long userId){
        return orderService.getOrdersByUserId(userId);
    }

    @PutMapping("/{orderId}/status")
    public Order updateOrderStatus(@PathVariable("orderId") Long orderId, @RequestParam String status){
        return orderService.updateOrderStatus(orderId,status);
    }
}
