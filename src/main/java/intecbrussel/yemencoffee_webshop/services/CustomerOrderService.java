package intecbrussel.yemencoffee_webshop.services;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.model.Customer;

import java.util.List;

public interface CustomerOrderService {
    List<CartItems> showCustomerItems(Long id);
}
