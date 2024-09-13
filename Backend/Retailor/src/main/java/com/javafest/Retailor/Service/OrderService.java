package com.javafest.Retailor.Service;

import com.javafest.Retailor.Entity.Orders;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    public Orders createOrder(Orders orders);
    public Orders updateOrders(Orders orders);
    public List<Orders> getAllOrders();
    public List<Orders> getAllPendingOrdersByTailors(Long tailorId);
    public List<Orders> getAllAcceptedOrdersByTailors(Long tailorId);
    public List<Orders> getAllPendingOrdersByCustomer(Long customerId);
    public List<Orders> getAllAcceptedOrdersByCustomer(Long customerId);
    public Page<Orders> getAllCompletedOrdersByTailors(int offset,int pageSize,Long tailorId);
    public Page<Orders> getAllCompletedOrdersByCustomer(int offset,int pageSize,Long customerId);
    public String deleteCancelledProduct();
}
