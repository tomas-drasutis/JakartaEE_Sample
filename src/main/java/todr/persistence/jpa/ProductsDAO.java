package todr.persistence.jpa;

import lombok.Setter;
import todr.entities.Producer;
import todr.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProductsDAO {
    @Inject
    private EntityManager em;

    public void persist(Product product){
        this.em.persist(product);
    }

    public Product findOne(Integer id){
        return this.em.find(Product.class, id);
    }

    public List<Product> loadAll(){
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    public Product update(Product product){
        return em.merge(product);
    }
}
