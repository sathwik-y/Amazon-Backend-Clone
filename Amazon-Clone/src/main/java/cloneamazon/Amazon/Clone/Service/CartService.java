package cloneamazon.Amazon.Clone.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cloneamazon.Amazon.Clone.Entity.Cart;
import cloneamazon.Amazon.Clone.Entity.Users;
import cloneamazon.Amazon.Clone.Repository.CartRepo;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;
    
    @Autowired
    private UserService userService;

    public Cart createCart(Long userId) {
        Users user = userService.getUserById(userId);
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepo.save(cart);
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepo.findByUserId(userId);
    }
    
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.getCartItems().clear();
        cartRepo.save(cart);
    }

    public double getCartTotal(Long userId) {
        Cart cart = getCartByUserId(userId);
        return cart.getCartItems().stream()
            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
            .sum();
    }
}
