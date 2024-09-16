package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.Orders;
import com.javafest.Retailor.Enum.OrderStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders, String> {
    @SuppressWarnings("null")
    public Optional<Orders> findById(String id);
    public List<Orders> findByTailorIdAndOrderStatus(String tailorId, OrderStatus orderStatus);
    public List<Orders> findByCustomerIdAndOrderStatus(String customerId, OrderStatus orderStatus);
    public Page<Orders> findByTailorIdAndOrderStatus(PageRequest pageRequest,String tailorId, OrderStatus orderStatus);
    public Page<Orders> findByCustomerIdAndOrderStatus(PageRequest pageRequest,String customerId, OrderStatus orderStatus);
    public void deleteByOrderStatus(OrderStatus orderStatus);
}
