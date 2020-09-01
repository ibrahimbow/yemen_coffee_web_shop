package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.model.Customer;
import intecbrussel.yemencoffee_webshop.repositories.CustomerOrdersRepo;
import intecbrussel.yemencoffee_webshop.services.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrdersServiceImpl implements CustomerOrderService {

    private CustomerOrdersRepo customerOrdersRepo;

    @Autowired
    public void setCustomerOrdersRepo(CustomerOrdersRepo customerOrdersRepo) {
        this.customerOrdersRepo = customerOrdersRepo;
    }

    @Override
    public List<CartItems> showCustomerItems(Long id) {
        return customerOrdersRepo.showCustomerItems(id);
    }
}
