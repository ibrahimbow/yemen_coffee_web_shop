package intecbrussel.yemencoffee_webshop.repositories;

import intecbrussel.yemencoffee_webshop.model.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemsRepo extends CrudRepository<CartItem, Long> {
}
