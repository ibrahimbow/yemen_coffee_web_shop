package intecbrussel.yemencoffee_webshop.email;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Table;

public class EmailInfo {

    private final String username = "smartman402@hotmail.com";
    private final String password = "bbg402_BBG402";
    private String fromEmail = "smartman402@hotmail.com";
    private String toEmail = "ibrahim.alolofi@hotmail.com";

    public EmailInfo() {
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

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
}
