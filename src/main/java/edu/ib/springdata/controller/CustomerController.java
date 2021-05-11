package edu.ib.springdata.controller;

import edu.ib.springdata.objects.Customer;
import edu.ib.springdata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/all")
    public Iterable<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/customer")
    public Optional<Customer> findById(@RequestParam Long id){
        return customerService.findById(id);
    }

    @PostMapping("/admin/customer")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @PutMapping("/admin/customer")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @PatchMapping(path="/admin/customer/{id}")
    public Customer patchCustomer(@PathVariable Long id,@RequestBody Map<Object,Object> fields){
        Customer customer= customerService.findById(id).get();

        fields.forEach((k,v)->{
            Field field = ReflectionUtils.findField(Customer.class,(String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field,customer,v);
        });
        return customerService.save(customer);
    }

}
