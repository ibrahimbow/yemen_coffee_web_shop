package intecbrussel.yemencoffee_webshop.services;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import java.util.List;

public interface CustomerOrderService {
    List<CartItems> showCustomerItems(Long id);
}
