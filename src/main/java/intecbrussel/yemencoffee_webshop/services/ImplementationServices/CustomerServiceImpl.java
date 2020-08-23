package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.Customer;
import intecbrussel.yemencoffee_webshop.repositories.CustomerRepo;
import intecbrussel.yemencoffee_webshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    private EntityManagerFactory entityManagerFactory;

    private CustomerRepo customerRepo;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Autowired
    public void setCustomerRepo(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public void addNewCustomer(Customer customer) {
        this.customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> optional = customerRepo.findById(id);
        Customer customer= null;
        if (optional.isPresent()) {
            customer = optional.get();
        } else {
            throw new RuntimeException("Customer is not found for id :: " + id);
        }
        return customer;
    }

    @Override
    public void deleteCustomerById(long id) {
        this.customerRepo.deleteById(id);
    }


    public Customer checkLogin(String email, String password){
        Customer customer = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String sqlQuery = "Select c from Customer as c " +
                "where c.email = :email and c.password = :password";
        TypedQuery<Customer> typedQuery = entityManager.createQuery(sqlQuery, Customer.class);
        typedQuery.setParameter("email", email);
        typedQuery.setParameter("password", password);

        try {
            customer = typedQuery.getSingleResult();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            entityManager.close();
        }
        return customer;


    }
}
