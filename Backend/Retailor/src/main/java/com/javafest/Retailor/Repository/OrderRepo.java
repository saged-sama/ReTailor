package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.Orders;
import com.javafest.Retailor.Enum.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {
    public Optional<Orders> findById(Long id);
    public List<Orders> findByTailorIdAndOrderStatus(Long tailorId, OrderStatus orderStatus);
    public List<Orders> findByCustomerIdAndOrderStatus(Long customerId, OrderStatus orderStatus);
    public Page<Orders> findByTailorIdAndOrderStatus(PageRequest pageRequest,Long tailorId, OrderStatus orderStatus);
    public Page<Orders> findByCustomerIdAndOrderStatus(PageRequest pageRequest,Long customerId, OrderStatus orderStatus);
    public void deleteByOrderStatus(OrderStatus orderStatus);
}
