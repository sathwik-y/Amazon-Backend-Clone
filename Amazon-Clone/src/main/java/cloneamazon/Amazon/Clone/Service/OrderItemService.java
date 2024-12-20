package cloneamazon.Amazon.Clone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloneamazon.Amazon.Clone.Entity.OrderItem;
import cloneamazon.Amazon.Clone.Repository.OrderItemRepo;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepo orderItemRepo;

    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }

    public void removeOrderItem(Long orderItemId) {
        orderItemRepo.deleteById(orderItemId);
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepo.findByOrderId(orderId);
    }
}