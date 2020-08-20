package intecbrussel.yemencoffee_webshop.services;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.model.Product;

import java.util.List;

public interface CartItemsService {
    List<CartItems> getAllCartItems();
    void saveCartItems(CartItems cartItems);
    void addCartItems(List<CartItems> cartItems);
    CartItems getCartItemsById(Long id);
    void deleteCartItemsById(long id);
    CartItems getCartItemsProductById(Long id);



}
