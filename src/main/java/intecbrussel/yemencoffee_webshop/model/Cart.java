package intecbrussel.yemencoffee_webshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private CartItems cartItems;

    @OneToMany(mappedBy = "cart", orphanRemoval = true)
    private List<CartItems> cartItemsList = new ArrayList<>();


    @ManyToOne
    private Customer customer;


//    @OneToMany
//    private List<Customer> customersList = new ArrayList<>();


    public Cart() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItems> getCartItemsList() {
        return cartItemsList;
    }

    public void setCartItemsList(List<CartItems> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartItemsList=" + cartItemsList +
                ", customer=" + customer +
                '}';
    }
}
