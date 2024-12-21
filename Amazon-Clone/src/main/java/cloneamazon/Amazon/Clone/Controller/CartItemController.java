package cloneamazon.Amazon.Clone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cloneamazon.Amazon.Clone.Entity.CartItem;
import cloneamazon.Amazon.Clone.Service.CartItemService;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/cartitems")
public class CartItemController {
       @Autowired
    private CartItemService cartItemService;

    @PostMapping("/cart/{userId}/product/{productId}")
    public ResponseEntity<CartItem> addToCart(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartItemService.addItemToCart(userId, productId, quantity));
    }

    @DeleteMapping("/cart/{cartId}/product/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @PathVariable("cartId") Long cartId,
            @PathVariable("productId") Long productId) {
        cartItemService.removeItemFromCart(cartId, productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cart/{cartId}/product/{productId}")
    public ResponseEntity<CartItem> updateQuantity(
        @PathVariable("cartId") Long cartId,
        @PathVariable("productId") Long productId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartItemService.updateItemQuantity(cartId, productId, quantity));
    } 
}
