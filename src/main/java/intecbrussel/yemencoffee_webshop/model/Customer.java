package intecbrussel.yemencoffee_webshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String full_name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private int phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "zipcode", nullable = false)
    private int zipcode;


    @ManyToOne(cascade = CascadeType.ALL)
    private Cart cart;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartList = new ArrayList<>();


    @OneToMany(mappedBy = "customerOrders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orderList = new ArrayList<>();

    public Customer() {
    }

    public Customer(String full_name, String email, String password, int phone, String address, String country, String city, int zipcode, Cart cart, List<Cart> cartList, List<Order> orderList) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.cart = cart;
        this.cartList = cartList;
        this.orderList = orderList;
    }
//Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipcode=" + zipcode +
                ", cart=" + cart +
                ", cartList=" + cartList +
                ", orderList=" + orderList +
                '}';
    }
}
