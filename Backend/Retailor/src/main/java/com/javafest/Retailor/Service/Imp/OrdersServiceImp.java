package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Entity.Orders;
import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Entity.ProductSize;
import com.javafest.Retailor.Enum.OrderStatus;
import com.javafest.Retailor.Repository.OrderRepo;
import com.javafest.Retailor.Service.OrderService;
import com.javafest.Retailor.Service.ProductService;
import com.javafest.Retailor.Service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrdersServiceImp implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSizeService productSizeService;
    @Override
    public Orders createOrder(Orders orders) {
        return orderRepo.save(orders);
    }

    @Override
    @Transactional
    public Orders updateOrders(Long id) {
        Orders orders= orderRepo.findById(id).orElseThrow();
        orders.setOrderStatus(OrderStatus.COMPLETED);
        int ct= orders.getProduct().getSoldCount();
        Product product=orders.getProduct();
        product.setSoldCount(ct+ orders.getQuantity());
        product.setSoldAt(Date.valueOf(LocalDate.now()));
        product.setTotalCount(product.getTotalCount()-orders.getQuantity());
        productService.save(product);
        return orderRepo.save(orders);
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public List<Orders> getAllPendingOrdersByTailors(Long tailorId) {
        return orderRepo.findByTailorIdAndOrderStatus(tailorId, OrderStatus.PENDING);
    }

    @Override
    public List<Orders> getAllAcceptedOrdersByTailors(Long tailorId) {
        return orderRepo.findByTailorIdAndOrderStatus(tailorId, OrderStatus.ACCEPTED);
    }

    @Override
    public List<Orders> getAllPendingOrdersByCustomer(Long customerId) {
        return orderRepo.findByCustomerIdAndOrderStatus(customerId, OrderStatus.PENDING);
    }

    @Override
    public List<Orders> getAllAcceptedOrdersByCustomer(Long customerId) {
        return orderRepo.findByCustomerIdAndOrderStatus(customerId, OrderStatus.ACCEPTED);
    }

    @Override
    public Page<Orders> getAllCompletedOrdersByTailors(int offset,int pageSize,Long tailorId) {
        return orderRepo.findByTailorIdAndOrderStatus(PageRequest.of(offset,pageSize),tailorId,OrderStatus.COMPLETED);
    }

    @Override
    public Page<Orders> getAllCompletedOrdersByCustomer(int offset,int pageSize,Long customerId) {
        return orderRepo.findByCustomerIdAndOrderStatus(PageRequest.of(offset,pageSize),customerId,OrderStatus.COMPLETED);
    }

    @Override
    public String deleteCancelledProduct(Long id) {
        List<Orders> orders=orderRepo.findByTailorIdAndOrderStatus(id,OrderStatus.CANCELLED);
        for(Orders orders1 : orders){
            ProductSize productSize= productSizeService.getProductSizeByProductIdAndSize(orders1.getProduct().getId(), orders1.getSize());
            productSize.setQuantity(productSize.getQuantity()+ orders1.getQuantity());
            productSizeService.saveProductSize(productSize);
        }
        orderRepo.deleteByOrderStatus(OrderStatus.CANCELLED);
        return "Successfully Deleted";
    }
}
