package cloneamazon.Amazon.Clone.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import cloneamazon.Amazon.Clone.Entity.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {

    List<OrderItem> findByOrder_OrderId(Long orderId);
    
}
