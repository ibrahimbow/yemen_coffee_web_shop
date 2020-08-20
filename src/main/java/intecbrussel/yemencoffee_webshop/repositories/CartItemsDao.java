package intecbrussel.yemencoffee_webshop.repositories;

import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CartItemsDao {

    boolean createCartItem(CartItems cartItems);
    CartItems  showCartItems(Long id);
    boolean updateCartItem(CartItems cartItems);
    boolean deleteCartItem(CartItems cartItems);
    List<CartItems> getAllCartItems();
    void setAllCartItems(List<CartItems> cartItemsList);
    CartItems checkCartItems(long product_id);

}
