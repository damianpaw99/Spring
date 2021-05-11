package edu.ib.springdata.controller;

import edu.ib.springdata.objects.Order;
import edu.ib.springdata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/all")
    public Iterable<Order> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/order")
    public Optional<Order> findById(@RequestParam Long id){
        return orderService.findById(id);
    }

    @PostMapping("/admin/order")
    public Order addOrder(@RequestBody Order order){
        return orderService.save(order);
    }

    @PutMapping("/admin/order")
    public Order updateOrder(@RequestBody Order order){
        return orderService.save(order);
    }

    @PatchMapping(path="/admin/order/{id}")
    public Order patchOrder(@PathVariable Long id,@RequestBody Map<Object,Object> fields){
        Order order= orderService.findById(id).get();

        fields.forEach((k,v)->{
            Field field = ReflectionUtils.findField(Order.class,(String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field,order,v);
        });
        return orderService.save(order);
    }

}