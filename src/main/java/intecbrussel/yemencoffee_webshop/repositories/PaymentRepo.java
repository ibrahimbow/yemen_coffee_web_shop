package intecbrussel.yemencoffee_webshop.repositories;

import intecbrussel.yemencoffee_webshop.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
