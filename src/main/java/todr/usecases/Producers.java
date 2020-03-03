package todr.usecases;

import lombok.Getter;
import lombok.Setter;
import todr.entities.Producer;
import todr.persistence.ProducersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Producers {
    @Inject
    private ProducersDAO producersDAO;

    @Getter @Setter
    private Producer producerToCreate = new Producer();

    @Getter
    private List<Producer> allProducers;

    @PostConstruct
    public void init(){
        loadAllProducers();
    }

    @Transactional
    public String createProducer(){
        this.producersDAO.persist(producerToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllProducers(){
        this.allProducers = producersDAO.loadAll();
    }
}
