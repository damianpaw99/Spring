package edu.ib.springdata;

import edu.ib.springdata.objects.Customer;
import edu.ib.springdata.objects.Order;
import edu.ib.springdata.objects.Product;
import edu.ib.springdata.repository.CustomerRepository;
import edu.ib.springdata.repository.OrderRepository;
import edu.ib.springdata.repository.ProductRepository;
import edu.ib.springdata.repository.UserDtoRepository;
import edu.ib.springdata.objects.user.User;
import edu.ib.springdata.objects.user.UserDto;
import edu.ib.springdata.objects.user.UserDtoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbMockData {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private UserDtoRepository userDtoRepository;

    @Autowired
    public DbMockData(ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository,UserDtoRepository userDtoRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.userDtoRepository = userDtoRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fill() {


        Iterable<Product> productIterable=productRepository.findAll();
        if(!productIterable.iterator().hasNext()){
            Product product = new Product("Korek", 2.55f, true);
            Product product1 = new Product("Rura", 5f, true);
            productRepository.save(product);
            productRepository.save(product1);
        }
        Iterable<Customer> customerIterable=customerRepository.findAll();
        if(!customerIterable.iterator().hasNext()){
            Customer customer = new Customer("Jak Kowalski", "Wroclaw");
            customerRepository.save(customer);
        }

        Iterable<Order> orderIterable = orderRepository.findAll();
        if(!orderIterable.iterator().hasNext()){
            Product product = new Product("Blacha", 2.40f, true);
            Product product1 = new Product("Cement", 15f, true);
            productRepository.save(product);
            productRepository.save(product1);
            Customer customer = new Customer("Stefan Kowal", "Walbrzych");
            customerRepository.save(customer);
            Set<Product> products = new HashSet<>() {
                {
                    add(product);
                    add(product1);
                }};
            Order order = new Order(customer, products, LocalDateTime.now(), "in progress");
            orderRepository.save(order);
        }

        Iterable<UserDto> list=userDtoRepository.findAll();
        if(!list.iterator().hasNext()) {
            User user1 = new User("adamnowak", "pass", "ROLE_CUSTOMER");
            User user2 = new User("krzysztofkowalski", "pass123", "ROLE_ADMIN");
            UserDtoBuilder builder = new UserDtoBuilder();
            UserDto userDto1 = builder.build(user1);
            UserDto userDto2 = builder.build(user2);
            userDtoRepository.save(userDto1);
            userDtoRepository.save(userDto2);
        }
    }
}
