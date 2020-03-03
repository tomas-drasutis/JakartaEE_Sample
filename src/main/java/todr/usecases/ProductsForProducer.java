package todr.usecases;

import lombok.Getter;
import lombok.Setter;
import todr.entities.Producer;
import todr.entities.Product;
import todr.persistence.ProducersDAO;
import todr.persistence.ProductsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class ProductsForProducer {
    @Inject
    private ProducersDAO producersDAO;

    @Inject
    private ProductsDAO productsDAO;

    @Getter @Setter
    private Producer producer;

    @Getter @Setter
    private Product productToCreate = new Product();

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer producerId = Integer.parseInt(requestParameters.get("producerId"));
        this.producer = producersDAO.findOne(producerId);
    }

    @Transactional
    public String createProduct(){
        productToCreate.setProducer(this.producer);
        productsDAO.persist(productToCreate);
        return "products?faces-redirect=true&producerId=" + this.producer.getId();
    }
}
