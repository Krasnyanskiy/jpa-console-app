package ua.krasnyanskiy.jpa.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = SEQUENCE,
            generator = "order_seq_gen")
    @SequenceGenerator(name = "order_seq_gen",
            sequenceName = "order_table_id_seq")
    private Integer id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "product_amount")
    private Integer productAmount;

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "product_order_table",
            joinColumns = {@JoinColumn(name = "order_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id",
                    referencedColumnName = "id")})
    private Collection<Product> products;

    @ManyToOne(cascade = ALL)
    private User user;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (productAmount != null ? !productAmount.equals(order.productAmount) : order.productAmount != null)
            return false;
        if (products != null ? !products.equals(order.products) : order.products != null) return false;
        if (totalPrice != null ? !totalPrice.equals(order.totalPrice) : order.totalPrice != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (productAmount != null ? productAmount.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
    */

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                ", productAmount=" + productAmount +
                //", products=" + products +
                //", user=" + user +
                '}';
    }
}
