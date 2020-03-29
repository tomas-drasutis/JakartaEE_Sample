package todr.usecases;

import lombok.Getter;
import todr.mybatis.dao.ProducerMapper;
import todr.mybatis.dao.ProductMapper;
import todr.mybatis.dao.ReceiverMapper;
import todr.mybatis.dao.ReceiverProductMapper;
import todr.mybatis.model.Producer;
import todr.mybatis.model.Product;
import todr.mybatis.model.Receiver;
import todr.mybatis.model.ReceiverProduct;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class MybatisController{
    @Getter
    private Producer producer = new Producer();
    @Getter
    private Product product = new Product();
    @Getter
    private Product productToAssign = new Product();
    @Getter
    private Receiver receiver = new Receiver();
    @Getter
    private ReceiverProduct receiversProduct = new ReceiverProduct();

    @Getter
    private List<Producer> producers;
    @Getter
    private List<Product> products;
    @Getter
    private List<Receiver> receivers;

    @Inject
    private ProducerMapper producerMapper;
    @Inject
    private ProductMapper productMapper;
    @Inject
    private ReceiverMapper receiverMapper;
    @Inject
    private ReceiverProductMapper receiverProductMapper;

    @PostConstruct
    public void init(){
        this.producers = producerMapper.selectAll();
        this.products = productMapper.selectAll();
        this.receivers = receiverMapper.selectAll();
    }

    @Transactional
    public void createNewProduct(){
        product.setProducerId(producer.getId());
        productMapper.insert(product);
    }

    @Transactional
    public void createNewProducer(){
        producerMapper.insert(producer);
    }

    @Transactional
    public void createNewReceiver(){
        receiverMapper.insert(receiver);
    }

    @Transactional
    public Producer getProducerById(Integer id) {
        return producerMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void assignProductToReceiver(){
        receiversProduct.setProductId(productToAssign.getId());
        receiversProduct.setReceiverId(receiver.getId());
        receiverProductMapper.insert(receiversProduct);
    }

    @Transactional
    public List<Receiver> getProductReceivers(Integer productId){
        List<Receiver> receiverList = new ArrayList<Receiver>();
        List<ReceiverProduct> receiverProducts = receiverProductMapper.selectAll();
        for (ReceiverProduct rp : receiverProducts){
            if(rp.getProductId() == productId){
                receiverList.add(receiverMapper.selectByPrimaryKey(rp.getReceiverId()));
            }
        }

        return receiverList;
    }
}