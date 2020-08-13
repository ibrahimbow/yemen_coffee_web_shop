package intecbrussel.yemencoffee_webshop.model;

import javax.naming.ldap.PagedResultsControl;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_items")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cart cart;



    public CartItems() {
    }

    public CartItems(int quantity, Product product, Cart cart) {
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                ", cart=" + cart +
                '}';
    }
}
