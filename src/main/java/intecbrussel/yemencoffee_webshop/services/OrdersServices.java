package intecbrussel.yemencoffee_webshop.services;

import intecbrussel.yemencoffee_webshop.model.Order;
import java.util.List;

public interface OrdersServices {
    List<Order> getAllOrders();
    void saveOrder(Order order);
    Order getOrderById(Long id);
    void deleteOrderById(long id);

}
