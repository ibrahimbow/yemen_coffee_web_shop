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

    @Column(name = "cart_Number")
    private String cartUniNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private CartItems cartItems;

    @OneToMany
    private List<Customer> customersList = new ArrayList<>();


    public Cart(CartItems cartItems) {
        this.cartItems = cartItems;
    }

    public Cart() {

    }

    public String getCartUniNumber() {
        return cartUniNumber;
    }

    public void setCartUniNumber(String cartUniNumber) {
        this.cartUniNumber = cartUniNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartItems getCartItems() {
        return cartItems;
    }

    public void setCartItems(CartItems cartItems) {
        this.cartItems = cartItems;
    }

    public List<Customer> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(List<Customer> customersList) {
        this.customersList = customersList;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartUniNumber='" + cartUniNumber + '\'' +
                ", cartItems=" + cartItems +
                ", customersList=" + customersList +
                '}';
    }
}
