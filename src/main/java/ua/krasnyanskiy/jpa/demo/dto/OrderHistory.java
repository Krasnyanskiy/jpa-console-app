package ua.krasnyanskiy.jpa.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "order_table_history")
public class OrderHistory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = SEQUENCE, generator = "order_history_seq_gen")
    @SequenceGenerator(name = "order_history_seq_gen", sequenceName = "order_history_table_id_seq")
    private Integer id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "product_amount")
    private Integer productAmount;

    /**
     * Default constructor.
     */
    public OrderHistory() {
    }


    /**
     * Copy Constructor.
     *
     * @param history old OrderHistory entity to be copied
     */
    public OrderHistory(OrderHistory history) {
        this.date = history.getDate();
        this.user = history.getUser();
        this.order = history.getOrder();
        this.totalPrice = history.getTotalPrice();
        this.productAmount = history.getProductAmount();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
}
