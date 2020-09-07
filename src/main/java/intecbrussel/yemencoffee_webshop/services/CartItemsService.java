package intecbrussel.yemencoffee_webshop.services;

import intecbrussel.yemencoffee_webshop.model.CartItems;

import java.util.List;

public interface CartItemsService {
    List<CartItems> getAllCartItems();
    void saveCartItems(CartItems cartItems);
    CartItems getCartItemsById(Long id);
    void deleteCartItemsById(long id);




}
