package cloneamazon.Amazon.Clone.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import cloneamazon.Amazon.Clone.Entity.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart_CartId(Long cartId);

    CartItem findByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);

    void deleteByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);	
}
