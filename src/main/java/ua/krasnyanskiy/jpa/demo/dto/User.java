package ua.krasnyanskiy.jpa.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany
    private Collection<Order> orders;

    /**
     ** Secret field : do not persist it!
     **/
    @Transient
    private String secretFiled;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public String getSecretFiled() {
        return secretFiled;
    }

    public void setSecretFiled(String secretFiled) {
        this.secretFiled = secretFiled;
    }
}
