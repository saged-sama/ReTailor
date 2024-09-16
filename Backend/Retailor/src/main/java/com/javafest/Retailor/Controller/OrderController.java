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

    @PutMapping("/updateOrder/{orderId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable String orderId){
        return ResponseEntity.ok(orderService.updateOrders(orderId));
    }
    @GetMapping("/order/filter")
    public ResponseEntity<?> getOrders(
            @RequestParam(required = false) String tailorId, // Filter by tailor ID
            @RequestParam(required = false) String customerId, // Filter by customer ID
            @RequestParam(required = false) String productId, //Filter by product id
            @RequestParam(required = false) String status, // Filter by order status (e.g., "PENDING", "ACCEPTED", "COMPLETED")
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        // Handle product size retrieval
        if (tailorId == null && customerId == null && status == null) {
            return ResponseEntity.ok(productSizeService.getProductSizeByProductId(productId));
        }

        // Handle order retrieval based on status and ID
        if (status != null) {
            if (tailorId != null) {
                switch (status.toUpperCase()) {
                    case "PENDING":
                        return ResponseEntity.ok(orderService.getAllPendingOrdersByTailors(tailorId));
                    case "ACCEPTED":
                        return ResponseEntity.ok(orderService.getAllAcceptedOrdersByTailors(tailorId));
                    case "COMPLETED":
                        return ResponseEntity.ok(orderService.getAllCompletedOrdersByTailors(offset, pageSize, tailorId));
                }
            }
            if (customerId != null) {
                switch (status.toUpperCase()) {
                    case "PENDING":
                        return ResponseEntity.ok(orderService.getAllPendingOrdersByCustomer(customerId));
                    case "ACCEPTED":
                        return ResponseEntity.ok(orderService.getAllAcceptedOrdersByCustomer(customerId));
                    case "COMPLETED":
                        return ResponseEntity.ok(orderService.getAllCompletedOrdersByCustomer(offset, pageSize, customerId));
                }
            }
        }

        // Handle retrieval of all orders
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @DeleteMapping("/admin/deleteOrder/{tailorId}")
    public ResponseEntity<String > deleteCancelledOrder(@PathVariable String tailorId){
        return ResponseEntity.ok(orderService.deleteCancelledProduct(tailorId));
    }
}