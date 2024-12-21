package cloneamazon.Amazon.Clone.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import cloneamazon.Amazon.Clone.Entity.Payments;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payments,Long> {

    List<Payments> findByUser_UserId(Long userId);

    List<Payments> findByOrder_OrderId(Long orderId);
    
}
