package intecbrussel.yemencoffee_webshop.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_number")
    private int order_number;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private double total_price;

    @Column(name = "order_date")
    private LocalDateTime order_date;

    @Column(name = "deliver_date")
    private LocalDateTime deliver_date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cart cart;

    public Order(LocalDateTime order_date, Customer customer, Cart cart) {
        this.order_date = order_date;
        this.customer = customer;
        this.cart = cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

//Getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }

    public LocalDateTime getDeliver_date() {
        return deliver_date;
    }

    public void setDeliver_date(LocalDateTime deliver_date) {
        this.deliver_date = deliver_date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_number=" + order_number +
                ", customer_id=" + customer.getId() +

                ", quantity=" + quantity +
                ", total_price=" + total_price +
                ", order_date=" + order_date +
                ", deliver_date=" + deliver_date +
                '}';
    }
}
