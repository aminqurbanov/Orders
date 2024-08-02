package com.example.ordersproject.mapper;

import com.example.ordersproject.dao.entity.OrderEntity;
import com.example.ordersproject.model.enums.OrderStatus;
import com.example.ordersproject.model.request.CreateOrUpdateOrderRequest;
import com.example.ordersproject.model.response.OrderResponse;

import static com.example.ordersproject.model.enums.OrderStatus.DRAFT;

public enum OrderMapper {
    ORDER_MAPPER;

    public OrderEntity builderOrderEntity(CreateOrUpdateOrderRequest request){
        return OrderEntity.builder()
                .amount(request.getAmount())
                .status(DRAFT)
                .build();
    }
    public OrderResponse mapEntityToResponse(OrderEntity order){
        return OrderResponse.builder()
                .id(order.getId())
                .amount(order.getAmount())
                .status(order.getStatus())
                .build();
    }
}
