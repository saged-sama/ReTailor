package com.javafest.Retailor.Controller;


import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Entity.*;
import com.javafest.Retailor.Enum.OrderStatus;
import com.javafest.Retailor.Service.*;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/collections")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TailorService tailorService;
    @Autowired
    private ProductService productService;

    @PostMapping("/customer/createOrder/{tailorId}/{productId}")
    public ResponseEntity<Orders> saveOrders(@ModelAttribute Orders orders,
                                             @PathVariable Long tailorId,
                                             @PathVariable Long productId,
                                             @RequestHeader("Authorization") String authHeader)throws Exception{
        String email;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            email= jwtService.extractUsername(jwt);
        } else {
            throw new Exception("Invalid Authorization header.");
        }

        Users users= usersService.getByEmail(email);
        Customer customer= customerService.getByUsers(users);
        if(!customer.getAddress().equals(orders.getDestinationAddress())){
            customer.setAddress(orders.getDestinationAddress());
            Customer customer1= customerService.updateCustomer(customer);
        }
        orders.setCustomer(customer);
        orders.setTailor(tailorService.getById(tailorId));
        Product product=productService.getById(productId);
        orders.setProduct(product);
        orders.setOrderStatus(OrderStatus.PENDING);
        orders.setPrice(product.getBasePrice());

        return ResponseEntity.ok(orderService.createOrder(orders));
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<Orders> updateOrder(@ModelAttribute Orders orders){
        return ResponseEntity.ok(orderService.updateOrders(orders));
    }

    @GetMapping("/admin/allOrder")
    public ResponseEntity<List<Orders>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/tailor/pendingOrder/{tailorId}")
    public ResponseEntity<List<Orders>> getAllTailorPendingOrders(@PathVariable Long tailorId){
        return ResponseEntity.ok(orderService.getAllPendingOrdersByTailors(tailorId));
    }

    @GetMapping("/customer/pendingOrder/{customerId}")
    public ResponseEntity<List<Orders>> getAllCustomerPendingOrders(@PathVariable Long customerId){
        return ResponseEntity.ok(orderService.getAllPendingOrdersByCustomer(customerId));
    }

    @GetMapping("/tailor/acceptedOrder/{tailorId}")
    public ResponseEntity<List<Orders>> getAllTailorAcceptedOrders(@PathVariable Long tailorId){
        return ResponseEntity.ok(orderService.getAllAcceptedOrdersByTailors(tailorId));
    }

    @GetMapping("/customer/acceptedOrder/{customerId}")
    public ResponseEntity<List<Orders>> getAllCustomerAcceptedOrders(@PathVariable Long customerId){
        return ResponseEntity.ok(orderService.getAllAcceptedOrdersByCustomer(customerId));
    }

    @GetMapping("/tailor/completedOrder/{offset}/{pageSize}/{tailorId}")
    public ResponseEntity<Page<Orders>> getAllTailorAcceptedOrders(@PathVariable int offset,@PathVariable int pageSize,@PathVariable Long tailorId){
        return ResponseEntity.ok(orderService.getAllCompletedOrdersByTailors(offset, pageSize, tailorId));
    }

    @GetMapping("/customer/completedOrder/{offset}/{pageSize}/{customerId}")
    public ResponseEntity<Page<Orders>> getAllCustomerAcceptedOrders(@PathVariable int offset,@PathVariable int pageSize,@PathVariable Long customerId){
        return ResponseEntity.ok(orderService.getAllCompletedOrdersByCustomer(offset, pageSize, customerId));
    }

    @DeleteMapping("/admin/deleteOrder")
    public ResponseEntity<String > deleteCancelledOrder(){
        return ResponseEntity.ok(orderService.deleteCancelledProduct());
    }
}
