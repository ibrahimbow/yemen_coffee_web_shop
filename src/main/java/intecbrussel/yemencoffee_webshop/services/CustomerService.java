package intecbrussel.yemencoffee_webshop.services;

import intecbrussel.yemencoffee_webshop.model.Customer;
import intecbrussel.yemencoffee_webshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerService {

    void addNewCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    void deleteCustomerById(long id);




}
