package intecbrussel.yemencoffee_webshop.repositories;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItems,Long> {
}
