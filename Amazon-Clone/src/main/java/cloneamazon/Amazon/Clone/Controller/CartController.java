package cloneamazon.Amazon.Clone.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cloneamazon.Amazon.Clone.Entity.Cart;
import cloneamazon.Amazon.Clone.Service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/user/{userId}")
    public Cart createCart(@PathVariable Long userId) {
        return cartService.createCart(userId);
    }

    @GetMapping("/user/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping("/user/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/total")
    public ResponseEntity<Double> getCartTotal(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartTotal(userId));
    }
}