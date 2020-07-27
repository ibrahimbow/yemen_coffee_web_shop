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

    @OneToMany(mappedBy = "cartItems" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "cartItems" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productList = new ArrayList<>();


    public CartItems(List<Cart> cartList, List<Product> productList) {
        this.cartList = cartList;
        this.productList = productList;
    }

    public CartItems() {
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "id=" + id +
                ", cartList=" + cartList +
                ", productList=" + productList +
                '}';
    }
}
