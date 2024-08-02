package com.example.ordersproject.dao.repository;

import com.example.ordersproject.dao.entity.OrderEntity;
import com.example.ordersproject.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity,Long> {
    @Override
    List<OrderEntity>findAll();
    // @Query(nativeQuery = true, value = "SELECT * FROM orders WHERE status = :status")
   // List<OrderEntity> getWithStatus(String status);

   // @Query("SELECT o FROM OrderEntity o WHERE o.status =:status")
    //List<OrderEntity> getWithStatus(OrderStatus status);
    List<OrderEntity> findByStatus(OrderStatus status);
}
