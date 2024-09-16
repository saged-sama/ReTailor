package com.javafest.Retailor.Controller;


import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Entity.*;
import com.javafest.Retailor.Enum.OrderStatus;
import com.javafest.Retailor.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @Autowired
    private ProductSizeService productSizeService;

    @PostMapping("/order/createOrder/{tailorId}/{productId}")
    @Transactional
    public ResponseEntity<Orders> saveOrders(@ModelAttribute Orders orders,
                                             @PathVariable String tailorId,
                                             @PathVariable String productId,
                                             @RequestHeader("Authorization") String authHeader)throws Exception{
        String email;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            email= jwtService.extractUsername(jwt);
        } else {
            throw new Exception("Invalid Authorization header.");
        }

        Product product=productService.getById(productId);
        ProductSize productSize= productSizeService.getProductSizeByProductIdAndSize(productId, orders.getSize());
        if (productSize.getQuantity() < orders.getQuantity()) {
            throw new RuntimeException("Not enough stock available for size: " + orders.getSize());
        }
        productSize.setQuantity(productSize.getQuantity()-orders.getQuantity());
        productSizeService.saveProductSize(productSize);
        Users users= usersService.getByEmail(email);
        Customer customer= customerService.getByUsers(users);
        if(!customer.getAddress().equals(orders.getDestinationAddress())){
            customer.setAddress(orders.getDestinationAddress());
            Customer customer1= customerService.updateCustomer(customer);
        }
        orders.setCustomer(customer);
        orders.setTailor(tailorService.getById(tailorId));
        orders.setProduct(product);
        orders.setOrderStatus(OrderStatus.ACCEPTED);
        orders.setPrice(product.getBasePrice()*orders.getQuantity());

        return ResponseEntity.ok(orderService.createOrder(orders));
    }

    @GetMapping("/order/productSize/{id}")
    public ResponseEntity<List<ProductSize>> getProductSize(@PathVariable String id){
        return ResponseEntity.ok(productSizeService.getProductSizeByProductId(id));
    }

    @PutMapping("/updateOrder/{orderId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable String orderId){
        return ResponseEntity.ok(orderService.updateOrders(orderId));
    }

    @GetMapping("/admin/allOrder")
    public ResponseEntity<List<Orders>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/tailor/pendingOrder/{tailorId}")
    public ResponseEntity<List<Orders>> getAllTailorPendingOrders(@PathVariable String tailorId){
        return ResponseEntity.ok(orderService.getAllPendingOrdersByTailors(tailorId));
    }

    @GetMapping("/customer/pendingOrder/{customerId}")
    public ResponseEntity<List<Orders>> getAllCustomerPendingOrders(@PathVariable String customerId){
        return ResponseEntity.ok(orderService.getAllPendingOrdersByCustomer(customerId));
    }

    @GetMapping("/tailor/acceptedOrder/{tailorId}")
    public ResponseEntity<List<Orders>> getAllTailorAcceptedOrders(@PathVariable String tailorId){
        return ResponseEntity.ok(orderService.getAllAcceptedOrdersByTailors(tailorId));
    }

    @GetMapping("/customer/acceptedOrder/{customerId}")
    public ResponseEntity<List<Orders>> getAllCustomerAcceptedOrders(@PathVariable String customerId){
        return ResponseEntity.ok(orderService.getAllAcceptedOrdersByCustomer(customerId));
    }

    @GetMapping("/tailor/completedOrder/{offset}/{pageSize}/{tailorId}")
    public ResponseEntity<Page<Orders>> getAllTailorAcceptedOrders(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String tailorId){
        return ResponseEntity.ok(orderService.getAllCompletedOrdersByTailors(offset, pageSize, tailorId));
    }

    @GetMapping("/customer/completedOrder/{offset}/{pageSize}/{customerId}")
    public ResponseEntity<Page<Orders>> getAllCustomerAcceptedOrders(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String customerId){
        return ResponseEntity.ok(orderService.getAllCompletedOrdersByCustomer(offset, pageSize, customerId));
    }

    @DeleteMapping("/admin/deleteOrder/{tailorId}")
    public ResponseEntity<String > deleteCancelledOrder(@PathVariable String tailorId){
        return ResponseEntity.ok(orderService.deleteCancelledProduct(tailorId));
    }
}