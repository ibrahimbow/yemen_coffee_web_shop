package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.Order;
import intecbrussel.yemencoffee_webshop.model.Product;
import intecbrussel.yemencoffee_webshop.repositories.OrderRepo;
import intecbrussel.yemencoffee_webshop.services.OrdersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrdersServices {

    private OrderRepo orderRepo;

    @Autowired
    public void setOrderRepo(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepo.findAll();
    }

    @Override
    public void saveOrder(Order order) {
        this.orderRepo.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> optional = orderRepo.findById(id);
        Order order = null;
        if (optional.isPresent()) {
            order = optional.get();
        } else {
            throw new RuntimeException("Order is not found for id :: " + id);
        }
        return order;
    }

    @Override
    public void deleteOrderById(long id) {

        this.orderRepo.deleteById(id);
    }
}
