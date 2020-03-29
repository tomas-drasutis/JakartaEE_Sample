package todr.persistence.jpa;

import lombok.Setter;
import todr.entities.Producer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProducersDAO {
    @Inject
    private EntityManager em;

    public void persist(Producer producer){
        this.em.persist(producer);
    }

    public List<Producer> loadAll(){
        return em.createNamedQuery("Producer.findAll", Producer.class).getResultList();
    }

    public Producer findOne(Integer id) { return em.find(Producer.class, id); }
}
