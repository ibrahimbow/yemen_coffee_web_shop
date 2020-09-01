package intecbrussel.yemencoffee_webshop.repositories;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.model.Customer;

import java.util.List;

public interface CustomerOrdersRepo {

    List<CartItems> showCustomerItems(Long id);
}
