package intecbrussel.yemencoffee_webshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_card")
    private String name_card;

    @Column(name = "credit_card")
    private int credit_card_number;

    @Column(name = "ex_month")
    private int ex_month;

    @Column(name = "ex_year")
    private int ex_year;

    @Column(name = "cvv")
    private int cvv;

    @ManyToOne
    private Customer customer;


    public Payment() {
    }

    public Payment(String name_card, int credit_card_number, int ex_month, int ex_year, int cvv, Customer customer) {
        this.name_card = name_card;
        this.credit_card_number = credit_card_number;
        this.ex_month = ex_month;
        this.ex_year = ex_year;
        this.cvv = cvv;
        this.customer = customer;
    }

    // Getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_card() {
        return name_card;
    }

    public void setName_card(String name_card) {
        this.name_card = name_card;
    }

    public int getCredit_card_number() {
        return credit_card_number;
    }

    public void setCredit_card_number(int credit_card_number) {
        this.credit_card_number = credit_card_number;
    }

    public int getEx_month() {
        return ex_month;
    }

    public void setEx_month(int ex_month) {
        this.ex_month = ex_month;
    }

    public int getEx_year() {
        return ex_year;
    }

    public void setEx_year(int ex_year) {
        this.ex_year = ex_year;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", name_card='" + name_card + '\'' +
                ", credit_card_number=" + credit_card_number +
                ", ex_month=" + ex_month +
                ", ex_year=" + ex_year +
                ", cvv=" + cvv +
                ", customer=" + customer +
                '}';
    }
}
