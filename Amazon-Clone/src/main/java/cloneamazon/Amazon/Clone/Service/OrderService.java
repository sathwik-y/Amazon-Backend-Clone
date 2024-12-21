package cloneamazon.Amazon.Clone.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloneamazon.Amazon.Clone.Entity.Cart;
import cloneamazon.Amazon.Clone.Entity.Order;
import cloneamazon.Amazon.Clone.Entity.OrderItem;
import cloneamazon.Amazon.Clone.Repository.OrderRepo;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartService cartService;


    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("PENDING");
        order.setUser(cart.getUser());

        List<OrderItem> orderItems = cart.getCartItems().stream()
        .map(carItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(carItem.getProduct().getPrice());
            orderItem.setQuantity(carItem.getQuantity());
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        cartService.clearCart(userId);
        return orderRepo.save(order);

    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepo.findAllByUser_UserId(userId);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepo.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        return orderRepo.save(order);
    }

    public Order getOrderById(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderById'");
    }

    public Double calculateOrderTotal(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateOrderTotal'");
    }

}
