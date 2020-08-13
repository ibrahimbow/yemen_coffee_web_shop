package intecbrussel.yemencoffee_webshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;


    @OneToMany(mappedBy = "cartOrders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> cartItemsList = new ArrayList<>();



    public Cart() {
    }

    public Cart(Customer customer, List<Order> cartItemsList) {
        this.customer = customer;
        this.cartItemsList = cartItemsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Order> getCartItemsList() {
        return cartItemsList;
    }

    public void setCartItemsList(List<Order> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + customer +
                ", cartItemsList=" + cartItemsList +
                '}';
    }
}
