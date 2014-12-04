package ua.krasnyanskiy.jpa.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = SEQUENCE,
            generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen",
            sequenceName = "user_table_id_seq")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OrderBy(value = "totalPrice ASC ") // sort orders from smaller price to bigger [2, 4, 1] => [1, 2, 4]
    @OneToMany(mappedBy = "user",
            cascade = ALL)
    private Collection<Order> orders;

    /**
     * Secret field : do not persist it!
     */
//    @Transient
//    private String secretFiled;

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

//    public String getSecretFiled() {
//        return secretFiled;
//    }
//
//    public void setSecretFiled(String secretFiled) {
//        this.secretFiled = secretFiled;
//    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (orders != null ? !orders.equals(user.orders) : user.orders != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", orders=" + orders +
                '}';
    }
    */
}