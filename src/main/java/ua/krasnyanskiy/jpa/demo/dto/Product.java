package ua.krasnyanskiy.jpa.demo.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "product_table")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = SEQUENCE,
            generator = "product_seq_gen")
    @SequenceGenerator(name = "product_seq_gen",
            sequenceName = "product_table_id_seq")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "product_order_table",
            joinColumns = {@JoinColumn(name = "product_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id",
                    referencedColumnName = "id")})
    private Collection<Order> orders;

    @ManyToOne(cascade = ALL)
    private Category category;

    @Lob
    @Basic(fetch = LAZY)
    @Column(name = "qr_code")
    private byte[] qrCode;

    public Product() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (orders != null ? !orders.equals(product.orders) : product.orders != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (!Arrays.equals(qrCode, product.qrCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (qrCode != null ? Arrays.hashCode(qrCode) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                //", orders=" + orders +
                ", category=" + category +
                ", qrCode=" + Arrays.toString(qrCode) +
                '}';
    }
}
