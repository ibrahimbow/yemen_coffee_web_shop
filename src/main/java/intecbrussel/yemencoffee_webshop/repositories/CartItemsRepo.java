package intecbrussel.yemencoffee_webshop.repositories;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import org.springframework.data.repository.CrudRepository;

public interface CartItemsRepo extends CrudRepository<CartItems, Long> {
}
