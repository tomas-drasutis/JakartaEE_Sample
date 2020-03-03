package todr.persistence;

import lombok.Setter;
import todr.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ProductsDAO {
    @PersistenceContext
    @Setter
    private EntityManager em;

    /*public List<Product> loadAll(){
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }*/

    public void persist(Product product){
        this.em.persist(product);
    }
}
