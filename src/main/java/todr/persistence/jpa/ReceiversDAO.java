package todr.persistence.jpa;

import todr.entities.Receiver;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ReceiversDAO {
    @Inject
    private EntityManager em;

    public void persist(Receiver receiver){this.em.persist(receiver);}

    public Receiver findOne(Integer id){return this.em.find(Receiver.class, id);}

    public List<Receiver> loadAll(){
        return em.createNamedQuery("Receiver.findAll", Receiver.class).getResultList();
    }
}
