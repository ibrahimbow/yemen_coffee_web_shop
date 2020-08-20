package intecbrussel.yemencoffee_webshop.services;

import intecbrussel.yemencoffee_webshop.model.Cart;
import intecbrussel.yemencoffee_webshop.model.CartItems;

import java.util.List;

public interface CartService {
    List<Cart> getAllCart();
    void saveCart(Cart cart);
    Cart getCartById(Long id);
    void deleteCartById(long id);

}
