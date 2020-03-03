package todr.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import todr.entities.Product;
import todr.persistence.ProductsDAO;

@Model
public class Products implements Serializable {
    @Inject
    private ProductsDAO productsDAO;
    @Getter
    private List<Product> allProducts;
    @Getter @Setter
    private Product productToCreate = new Product();

    /*@PostConstruct
    public void init(){
        loadProducts();
    }

    public void loadProducts() {
        this.allProducts = productsDAO.loadAll();
    }*/

    @Transactional
    public String createProduct(){
        this.productsDAO.persist(productToCreate);
        return "success";
    }
}