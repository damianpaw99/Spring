package edu.ib.springdata;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private ProductRepo productRepo;

    @Autowired
    public Start(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample(){
        Product product=new Product("Mleko",3.20f,true);
        productRepo.save(product);
        product=new Product("Masło",3.99f,true);
        productRepo.save(product);
        product=new Product("Szynka",4.49f,false);
        productRepo.save(product);
        product=new Product("Banany",2.99f,true);
        productRepo.save(product);
        product=new Product("Jabłka",1.99f,true);
        productRepo.save(product);
    }
}
