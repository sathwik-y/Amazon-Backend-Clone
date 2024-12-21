package cloneamazon.Amazon.Clone.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloneamazon.Amazon.Clone.Entity.Cart;
import cloneamazon.Amazon.Clone.Entity.CartItem;
import cloneamazon.Amazon.Clone.Entity.Product;
import cloneamazon.Amazon.Clone.Repository.CartItemRepo;

@Service
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
        
        CartItem cartItem = findCartItemByCartAndProduct(cart.getCartId(), productId);            
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        
        return cartItemRepo.save(cartItem);
    }

    public void removeItemFromCart(Long cartId, Long productId) {
        cartItemRepo.deleteByCart_CartIdAndProduct_ProductId(cartId, productId);
    }

    public CartItem updateItemQuantity(Long cartId, Long productId, Integer quantity) {
        CartItem cartItem = findCartItemByCartAndProduct(cartId, productId);
        cartItem.setQuantity(quantity);
        return cartItemRepo.save(cartItem);
    }

    private CartItem findCartItemByCartAndProduct(Long cartId, Long productId) {
        return  cartItemRepo.findByCart_CartIdAndProduct_ProductId(cartId, productId);
    }
}
