package cloneamazon.Amazon.Clone.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cloneamazon.Amazon.Clone.Entity.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(Long cartId);

    Object findCartItemByCartAndProduct(Long cartId, Long productId);

    void deleteByCartIdAndProductId(Long cartId, Long productId);

    Optional<CartItem> findByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);
	
}
