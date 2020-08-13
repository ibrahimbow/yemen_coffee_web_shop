package intecbrussel.yemencoffee_webshop.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "administrators")
public class Administrator {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "admin_username" , nullable = true, unique = true)
    private String admin_username;

    @Column(name = "admin_password" , nullable = true)
    private String admin_password;

    @Column(name = "admin_email" , nullable = true, unique = true)
    private String admin_email;

    @Column(name = "admin_fullname" , nullable = true)
    private String admin_fullName;


    public Administrator() {
    }

    public Administrator(String admin_username, String admin_password, String admin_email, String admin_fullName) {
        this.admin_username = admin_username;
        this.admin_password = admin_password;
        this.admin_email = admin_email;
        this.admin_fullName = admin_fullName;
    }

    public int getId() {
        return id;
    }

    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_fullName() {
        return admin_fullName;
    }

    public void setAdmin_fullName(String admin_fullName) {
        this.admin_fullName = admin_fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return id == that.id &&
                Objects.equals(admin_username, that.admin_username) &&
                Objects.equals(admin_email, that.admin_email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, admin_username, admin_email);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", admin_username='" + admin_username + '\'' +
                ", admin_password='" + admin_password + '\'' +
                ", admin_email='" + admin_email + '\'' +
                ", admin_fullName='" + admin_fullName + '\'' +
                '}';
    }
}
