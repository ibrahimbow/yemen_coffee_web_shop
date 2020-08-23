package intecbrussel.yemencoffee_webshop.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public class SendEmailInfo {

    private final String username = "smartman402@hotmail.com";
    private final String password = "bbg402_BBG402";
    private final String fromEmail = "smartman402@hotmail.com";

    private String toEmail;
    private List<CartItems> productItemsList;
    private Order order;
    private Customer customer;

    public SendEmailInfo() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFromEmail() {
        return fromEmail;
    }
    
    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public List<CartItems> getProductItemsList() {
        return productItemsList;
    }

    public void setProductItemsList(List<CartItems> productItemsList) {
        this.productItemsList = productItemsList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "SendEmailInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fromEmail='" + fromEmail + '\'' +
                ", toEmail='" + toEmail + '\'' +
                ", productItemsList=" + productItemsList +
                ", order=" + order +
                ", customer=" + customer +
                '}';
    }
}

