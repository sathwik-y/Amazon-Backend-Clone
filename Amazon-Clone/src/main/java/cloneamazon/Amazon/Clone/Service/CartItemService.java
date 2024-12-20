package cloneamazon.Amazon.Clone.Service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import cloneamazon.Amazon.Clone.Entity.Cart;
import cloneamazon.Amazon.Clone.Entity.CartItem;
import cloneamazon.Amazon.Clone.Entity.Product;
import cloneamazon.Amazon.Clone.Repository.CartItemRepo;

public class CartItemService {
    @Autowired
    private CartItemRepo cartItemRepo;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private ProductService productService;

    public CartItem addItemToCart(Long userId, Long productId, Integer quantity) {
        Cart cart = cartService.getCartByUserId(userId);
        Product product = productService.getProductById(productId);
        
        CartItem cartItem = findCartItemByCartAndProduct(cart.getCartId(), productId)
            .orElse(new CartItem());
            
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        
        return cartItemRepo.save(cartItem);
    }

    public void removeItemFromCart(Long cartId, Long productId) {
        cartItemRepo.deleteByCartIdAndProductId(cartId, productId);
    }

    public CartItem updateItemQuantity(Long cartId, Long productId, Integer quantity) {
        CartItem cartItem = findCartItemByCartAndProduct(cartId, productId)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(quantity);
        return cartItemRepo.save(cartItem);
    }

    @SuppressWarnings("unchecked")
    private Optional<CartItem> findCartItemByCartAndProduct(Long cartId, Long productId) {
        return (Optional<CartItem>) cartItemRepo.findCartItemByCartAndProduct(cartId, productId);
    }
}
