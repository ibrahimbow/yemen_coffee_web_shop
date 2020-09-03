package intecbrussel.yemencoffee_webshop.model;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String product_name;


    @Column(name = "image")
    private String image;

    @Column(name = "product_amount")
    private String amount;

    @Column(name = "product_price",precision = 10, scale = 2)
    private double price;

    @Column(name = "rate")
    private int rate;

    @Column(name = "product_description")
    private String product_description;


    //Constructor
    public Product() {
    }

    public Product(String product_name, String image, String amount, double price, int rate, String product_description) {
        this.product_name = product_name;
        this.image = image;
        this.amount = amount;
        this.price = price;
        this.rate = rate;
        this.product_description = product_description;
    }

    //Getter and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }


    @Override
    public String toString() {

        return "Product{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", image='" + image + '\'' +
                ", amount='" + amount + '\'' +
                ", price= " + price +
                ", rate=" + rate +
                ", product_description='" + product_description + '\'' +
                '}';
    }
}
