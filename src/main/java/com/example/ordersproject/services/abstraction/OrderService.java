package com.example.ordersproject.services.abstraction;


import com.example.ordersproject.model.request.CreateOrUpdateOrderRequest;
import com.example.ordersproject.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    void createOrder(CreateOrUpdateOrderRequest request);

    OrderResponse getOrder(Long id);

    List<OrderResponse> getOrders();

    void deleteOrder(Long id);

    void updateOrderAmount(Long id,CreateOrUpdateOrderRequest request);
    void deleteAllDraftOrders();


}
