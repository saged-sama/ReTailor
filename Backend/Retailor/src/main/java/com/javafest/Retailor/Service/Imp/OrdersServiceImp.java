package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Entity.Orders;
import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Enum.OrderStatus;
import com.javafest.Retailor.Repository.OrderRepo;
import com.javafest.Retailor.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImp implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Override
    public Orders createOrder(Orders orders) {
        return orderRepo.save(orders);
    }

    @Override
    public Orders updateOrders(Orders updatedOrders) {
        Orders orders= orderRepo.findById(updatedOrders.getId()).orElseThrow();
        orders.setOrderStatus(updatedOrders.getOrderStatus());
        orders.setDestinationAddress(updatedOrders.getDestinationAddress());
        orders.setQuantity(updatedOrders.getQuantity());

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
    public String deleteCancelledProduct() {
        orderRepo.deleteByOrderStatus(OrderStatus.CANCELLED);
        return "Successfully Deleted";
    }
}
