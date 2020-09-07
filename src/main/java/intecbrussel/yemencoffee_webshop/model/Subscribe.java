package intecbrussel.yemencoffee_webshop.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subscribers")
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email_subscriber", unique = true)
    private String email;


    public Subscribe() {
    }


    public Subscribe(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscribe subscribe = (Subscribe) o;
        return id == subscribe.id &&
                Objects.equals(email, subscribe.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }


    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
