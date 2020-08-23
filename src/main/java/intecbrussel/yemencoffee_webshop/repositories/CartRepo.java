package intecbrussel.yemencoffee_webshop.repositories;

import intecbrussel.yemencoffee_webshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
}
