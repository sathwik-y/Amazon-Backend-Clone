package cloneamazon.Amazon.Clone.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import cloneamazon.Amazon.Clone.Entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    List<Order> findAllByUserId(Long userId);
    
}
