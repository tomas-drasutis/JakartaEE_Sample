package todr.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RECEIVER")
@NamedQueries({
        @NamedQuery(name= "Receiver.findAll", query = "SELECT r FROM Receiver r")
})
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class Receiver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false)
    private Integer id;

    @Column(name="NAME", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name="RECEIVER_PRODUCT",
            joinColumns = {@JoinColumn(name="RECEIVER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name="PRODUCT_ID", referencedColumnName = "ID")})
    private List<Product> productList = new ArrayList();

    public Receiver(){

    }
}
