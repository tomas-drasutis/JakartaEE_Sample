package todr.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "select a from Product as a")
})
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max=50)
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Float price;

    @ManyToOne
    @JoinColumn(name = "PRODUCER_ID")
    private Producer producer;

    @ManyToMany(mappedBy = "productList")
    private List<Receiver> receiverList = new ArrayList();

    @Version
    @Column(name="OPT_LOCK_VERSION")
    private Integer version;

    public Product() {
    }

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }
}