package todr.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "PRODUCER")
@NamedQueries({
        @NamedQuery(name = "Producer.findAll", query = "SELECT p FROM Producer p")
})
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable =  false)
    private String name;

    @OneToMany(mappedBy = "producer", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList();

    public Producer() {

    }
}
