package com.javafest.Retailor.Service;

import com.javafest.Retailor.Entity.Orders;
import java.util.List;
import org.springframework.data.domain.Page;

public interface OrderService {
    public Orders createOrder(Orders orders);
    public Orders updateOrders(String id);
    public List<Orders> getAllOrders();
    public List<Orders> getAllPendingOrdersByTailors(String tailorId);
    public List<Orders> getAllAcceptedOrdersByTailors(String tailorId);
    public List<Orders> getAllPendingOrdersByCustomer(String customerId);
    public List<Orders> getAllAcceptedOrdersByCustomer(String customerId);
    public Page<Orders> getAllCompletedOrdersByTailors(int offset,int pageSize,String tailorId);
    public Page<Orders> getAllCompletedOrdersByCustomer(int offset,int pageSize,String customerId);
    public String deleteCancelledProduct(String id);
}