package intecbrussel.yemencoffee_webshop.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "shipping_cost")
    private double shippingCost;

    @Column(name="tax")
    private double tax;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Cart cart;


    //constructor
    public Order() {
        generateOrderNumbers();
    }

    public Order(LocalDateTime order_date, Customer customer, Cart cart) {
        generateOrderNumbers();
        this.order_date = order_date;
        this.customer = customer;
        this.cart = cart;
    }


//Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return order_number == order.order_number &&
                Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order_number);
    }

    // generate 4 digits
    private void generateOrderNumbers() {
        int generate = 0;
        Random value = new Random();

        int range = value.nextInt(1000);
        generate += range;
        int count = 0;
        int n = 0;
        for (int i = 0; i < 12; i++) {
            if (count == 4) {
                count = 0;
            } else
                n = value.nextInt(1000);
            generate += n;
            count++;
        }

        setOrder_number(generate);
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_number=" + order_number +
                ", quantity=" + quantity +
                ", total_price=" + total_price +
                ", order_date=" + order_date +
                ", deliver_date=" + deliver_date +
                ", shippingCost=" + shippingCost +
                ", tax=" + tax +
                ", customer=" + customer +
                ", cart=" + cart +
                '}';
    }
}
