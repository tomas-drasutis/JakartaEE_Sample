package todr.persistence;

import lombok.Setter;
import todr.entities.Producer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ProducersDAO {
    @PersistenceContext
    @Setter
    private EntityManager em;

    public void persist(Producer producer){
        this.em.persist(producer);
    }

    public List<Producer> loadAll(){
        return em.createNamedQuery("Producer.findAll", Producer.class).getResultList();
    }

    public Producer findOne(Integer id) { return em.find(Producer.class, id); }
}
