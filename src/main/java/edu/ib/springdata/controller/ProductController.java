package edu.ib.springdata.controller;

import edu.ib.springdata.objects.Product;
import edu.ib.springdata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/all")
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/product")
    public Optional<Product> findById(@RequestParam Long id){
        return productService.findById(id);
    }

    @PostMapping("/admin/product")
    public Product addProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping("/admin/product")
    public Product updateProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @PatchMapping(path="/admin/product/{id}")
    public Product patchProduct(@PathVariable Long id,@RequestBody Map<Object,Object> fields){
        Product product= productService.findById(id).get();

        fields.forEach((k,v)->{
            Field field = ReflectionUtils.findField(Product.class,(String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field,product,v);
        });
        return productService.save(product);
    }

}